package net.quenchnetworks.sassybarista.sass.eval;

import java.io.*;
import java.util.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.models.*;
import net.quenchnetworks.sassybarista.sass.value.*;
import net.quenchnetworks.sassybarista.sass.value.op.*;
import net.quenchnetworks.sassybarista.sass.expression.*;

public class SassSheetEvaluator
{
    // If this seems strange to isolate this part it is because it is,
    // but unfortunately this needs to be able to compile in GWT,
    // and GWT doesn't support java.util.regex. This means that the
    // implementation has to be swappable.
    public interface StringInterpolator
    {
        public String applyVariables(String str, ExpressionEvaluator evaluator,
            Map<String, IPropertyValue> scope) throws EvaluationException;
    }

    private static class Extension
    {
        Rule rule;
        List<Selector> selectors;
        List<Rule> matches;
        int matchedBy = 0;

        public Extension(Rule rule, List<Selector> selectors)
        {
            this.rule = rule;
            this.selectors = selectors;
            this.matches = new ArrayList<Rule>();
        }
    }

    private ExpressionEvaluator evaluator;
    private StringInterpolator interpolator;

    private List<MediaBlock> mediaBlocks;
    private Map<String, IFunction> functions;
    private Map<String, IPropertyValue> variables = null;
    private Map<String, Mixin> mixins = null;

    public SassSheetEvaluator(StringInterpolator interpolator)
    {
        this.functions = new HashMap<String, IFunction>();
        this.interpolator = interpolator;
    }

    public void addFunction(String name, IFunction function)
    {
        functions.put(name, function);
    }

    public void evaluate(SassSheet sheet)
    throws EvaluationException
    {
        evaluator = new ExpressionEvaluator(functions);

        Map<String, INode> unprocessedVariables = sheet.getVariables();
        variables = new LinkedHashMap<String, IPropertyValue>();
        for (Map.Entry<String, INode> entry : unprocessedVariables.entrySet()) {
            variables.put(entry.getKey(), evaluator.evaluate(entry.getValue(), variables));
        }

        this.mixins = sheet.getMixins();

        /*for (Rule rule : sheet.getRules()) {
            nestSelectors(rule);
        }*/

        processExtends(sheet);

        List<Rule> ruleList = new ArrayList<Rule>();
        for (Rule rule : sheet.getRules()) {
            processRule(rule, ruleList);
        }

        mediaBlocks = new ArrayList<MediaBlock>();
        for (MediaBlock mediaBlock : sheet.getMediaBlocks()) {
            processMediaBlock(mediaBlock);
            mediaBlocks.add(mediaBlock);
        }

        sheet.setVariables(new HashMap<String, INode>());
        sheet.setMixins(new HashMap<String, Mixin>());
        sheet.setRules(ruleList);
        sheet.setMediaBlocks(mediaBlocks);
    }

    private void interpolateSelectors(List<SelectorChain> chains,
        Map<String, IPropertyValue> scope)
    throws EvaluationException
    {
        for (SelectorChain chain : chains) {
            for (Selector selector : chain.getSelectors()) {
                if (selector.getId() != null) {
                    selector.setId(interpolator.applyVariables(selector.getId(), evaluator, scope));
                }
                if (selector.getElement() != null) {
                    selector.setElement(interpolator.applyVariables(selector.getElement(), evaluator, scope));
                }
                List<String> classes = new ArrayList<String>();
                for (String className : selector.getClassNames()) {
                    classes.add(interpolator.applyVariables(className, evaluator, scope));
                }
                selector.setClassNames(classes);
            }
        }
    }

    private SelectorChain mergeSelectorChains(SelectorChain first, SelectorChain second)
    {
        SelectorChain newChain = new SelectorChain();
        if (first.hasParentRef()) {
            throw new RuntimeException("First chain cannot contain parent references.");
        }

        if (second.hasParentRef()) {
            for (Selector sel : second.getSelectors()) {
                if (sel.isParentRef()) {
                    newChain.addSelectors(first.getSelectors());
                    List<Selector> res = newChain.getSelectors();
                    Selector last = res.get(res.size()-1);
                    if (sel.getCombinator() != Selector.Combinator.DESCENDANT_OF) {
                        last.setCombinator(sel.getCombinator());
                    }
                    if (sel.getId() != null) {
                        last.setId(sel.getId());
                    }
                    for (String className : sel.getClassNames()) {
                        last.addClassName(className);
                    }
                    if (sel.getPseudoClass() != null) {
                        last.setPseudoClass(sel.getPseudoClass());
                    }
                    if (sel.getPseudoClassParameter() != null) {
                        last.setPseudoClassParameter(sel.getPseudoClassParameter());
                    }
                    if (sel.getAttributeSelector() != null) {
                        last.setAttributeSelector(sel.getAttributeSelector());
                    }
                } else {
                    newChain.addSelector(sel);
                }
            }
        }
        else {
            newChain.addSelectors(first.getSelectors());
            newChain.addSelectors(second.getSelectors());
        }

        return newChain;
    }

    private void processExtends(SassSheet sheet)
    {
        // recurse and find all extensions
        List<Extension> extensions = new ArrayList<Extension>();
        for (Rule rule : sheet.getRules()) {
            extensions.addAll(findExtends(rule));
        }

        // build lookup table
        Map<Rule, Extension> lookup = new HashMap<Rule, Extension>();
        for (Extension extension : extensions) {
            lookup.put(extension.rule, extension);
        }

        // find and add matching rules to extension objects
        for (Extension extension : extensions) {

            // only analyze top level rules
            for (Rule rule : sheet.getRules()) {
                boolean match = false;

                // search for a matching selector
                for (SelectorChain chain : rule.getSelectorChains()) {
                    List<Selector> selectors = chain.getSelectors();

                    // only simple rules can be extended
                    if (selectors.size() != 1) {
                        continue;
                    }

                    Selector selector = selectors.get(0);
                    for (Selector cmp : extension.selectors) {
                        if (cmp.equals(selector)) {
                            match = true;
                            break;
                        }
                    }

                    if (match) {
                        break;
                    }
                }

                if (match) {
                    // add match to extension object so that we
                    // can come back later and append our selectors
                    extension.matches.add(rule);

                    // if this rule extends other rules we need
                    // to make all extensions referring to that rule
                    // first. we use a counter to make sure that this
                    // is done.
                    Extension ref = lookup.get(rule);
                    if (ref != null) {
                        ref.matchedBy++;
                    }
                }
            }
        }

        // print state
        /*for (Extension extension : extensions) {
            Rule rule = extension.rule;
            for (SelectorChain chain : rule.getSelectorChains()) {
                System.out.println(chain);
            }
            System.out.println("\textends ");
            for (Selector selector : extension.selectors) {
                System.out.println("\t" + selector);
            }
            System.out.println("\tand matches");
            for (Rule match : extension.matches) {
                for (SelectorChain chain : match.getSelectorChains()) {
                    System.out.println("\t" + chain);
                }
            }
            System.out.println("\tmatched by: " + extension.matchedBy);
            System.out.println();
            System.out.println();
        }*/

        // iterate until all selectors has been added
        while (extensions.size() > 0) {

            // use an iterator so we can remove objects
            // while looping
            Iterator<Extension> it = extensions.iterator();
            while (it.hasNext()) {
                Extension ext = it.next();

                // only process extensions that have been fully
                // extended themselves
                if (ext.matchedBy > 0) {
                    continue;
                }

                Rule rule = ext.rule;

                // add selectors to all matching rules
                for (Rule match : ext.matches) {
                    Extension matchExt = lookup.get(match);
                    if (matchExt != null) {
                        matchExt.matchedBy--;
                    }

                    for (SelectorChain chain : rule.getSelectorChains()) {
                        match.addSelectorChain(chain);
                    }
                }

                it.remove();
            }
        }
    }

    private List<Extension> findExtends(Rule rule)
    {
        List<Extension> matches = new ArrayList<Extension>();

        for (Rule subRule : rule.getSubRules()) {
            matches.addAll(findExtends(subRule));
        }

        if (rule.getExtends().size() > 0) {
            matches.add(new Extension(rule, rule.getExtends()));
            rule.setExtends(new ArrayList<Selector>());
        }

        return matches;
    }

    private void processRule(Rule rule, List<Rule> ruleList)
    throws EvaluationException
    {
        // Fetch all includes and just copy all the subrules
        // and properties of each mixin to this rule.
        for (IncludeDirective include : rule.getIncludes()) {
            Mixin mixin = mixins.get(include.getMixinName());
            if (mixin == null) {
                throw new EvaluationException("Mixin " + include.getMixinName() + " was not found.");
            }

            // copy the mixin to prevent substitutions from affecting
            // later use
            mixin = mixin.copy();

            Map<String, IPropertyValue> params = mixin.getParameterMap(include);

            reduceMixin(mixin, params);

            rule.addSubRules(mixin.getSubRules());
            rule.addProperties(mixin.getProperties());
        }

        // Evaluate control statements
        for (ControlStatement stmt : rule.getControlStatements()) {
            stmt.evaluate(rule, evaluator, variables);
        }

        for (Property property : rule.getProperties()) {
            String key = interpolator.applyVariables(property.getKey(), evaluator, variables);
            property.setKey(key);
            List<INode> newValues = new ArrayList<INode>();
            for (INode value : property.getValues()) {
                newValues.add(evaluator.evaluate(value, variables));
            }
            property.setValues(newValues);
        }

        rule.setIncludes(new ArrayList<IncludeDirective>());

        ruleList.add(rule);

        // Handle interpolations in selector chains
        interpolateSelectors(rule.getSelectorChains(), variables);

        // Render subrules, nest selectors
        List<SelectorChain> selectorChains = rule.getSelectorChains();
        for (Rule subrule : rule.getSubRules()) {
            // Permute all combinations of this level and the next levels selector
            List<SelectorChain> newChains = new ArrayList<SelectorChain>();
            for (SelectorChain selectorChain : selectorChains) {
                for (SelectorChain subSelectorChain : subrule.getSelectorChains()) {
                    newChains.add(mergeSelectorChains(selectorChain.copy(),
                        subSelectorChain.copy()));
                }
            }

            // Replace all the current selectors of the sub rule with the new
            // permutations. This will be done again for each level, which will
            // generate all the needed permutations regardless of the number of
            // lvels
            subrule.setSelectorChains(newChains);

            processRule(subrule, ruleList);
        }
        rule.setSubRules(new ArrayList<Rule>());
    }

    private void reduceMixin(Block block, Map<String, IPropertyValue> params)
    throws EvaluationException
    {
        for (Rule subrule : block.getSubRules()) {
            interpolateSelectors(subrule.getSelectorChains(), params);
        }

        for (ControlStatement stmt : block.getControlStatements()) {
            stmt.evaluate(block, evaluator, params);
        }
        block.setControlStatements(new ArrayList<ControlStatement>());

        for (Property property : block.getProperties()) {
            String key = interpolator.applyVariables(property.getKey(), evaluator, variables);
            property.setKey(key);
            List<INode> newValues = new ArrayList<INode>();
            for (INode value : property.getValues()) {
                newValues.add(evaluator.evaluate(value, params));
            }
            property.setValues(newValues);
        }

        for (Rule subrule : block.getSubRules()) {
            reduceMixin(subrule, params);
        }
    }

    private void processMediaBlock(MediaBlock block)
    throws EvaluationException
    {
        for (MediaQuery qry : block.getMediaQueries()) {
            Map<String, INode> features = qry.getFeatures();
            for (Map.Entry<String, INode> entry : features.entrySet()) {
                INode result = evaluator.evaluate(entry.getValue(), variables);
                features.put(entry.getKey(), result);
            }
        }

        List<Rule> ruleList = new ArrayList<Rule>();
        for (Rule rule : block.getRules()) {
            processRule(rule, ruleList);
        }
        block.setRules(ruleList);
    }
}

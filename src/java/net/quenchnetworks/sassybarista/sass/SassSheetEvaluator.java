package net.quenchnetworks.sassybarista.sass;

import java.io.*;
import java.util.*;

import net.quenchnetworks.sassybarista.sass.models.*;
import net.quenchnetworks.sassybarista.sass.value.*;

public class SassSheetEvaluator
{
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

    private List<Rule> ruleList;
    private Map<String, IPropertyValue> variables = null;
    private Map<String, Mixin> mixins = null;

    public SassSheetEvaluator()
    {
    }
    
    public void evaluate(SassSheet sheet)
    throws ParseException, SerializationException
    {
        this.variables = sheet.getVariables();
        this.mixins = sheet.getMixins();

        for (Rule rule : sheet.getRules()) {
            nestSelectors(rule);
        }
        
        processExtends(sheet);
        
        ruleList = new ArrayList<Rule>();
        for (Rule rule : sheet.getRules()) {
            processRule(rule);
        }
        
        sheet.setVariables(new HashMap<String, IPropertyValue>());
        sheet.setMixins(new HashMap<String, Mixin>());
        sheet.setRules(ruleList);
    }
    
    private void nestSelectors(Rule rule)
    {
        List<SelectorChain> selectorChains = rule.getSelectorChains();
        for (Rule subrule : rule.getSubRules()) {
            // Permute all combinations of this level and the next levels selector
            List<SelectorChain> newChains = new ArrayList<SelectorChain>();
            for (SelectorChain selectorChain : selectorChains) {
                for (SelectorChain subSelectorChain : subrule.getSelectorChains()) {
                    SelectorChain newChain = new SelectorChain();
                    newChain.addSelectors(selectorChain.getSelectors());
                    newChain.addSelectors(subSelectorChain.getSelectors());
                    newChains.add(newChain);
                }
            }
            
            // Replace all the current selectors of the sub rule with the new
            // permutations. This will be done again for each level, which will
            // generate all the needed permutations regardless of the number of
            // lvels
            subrule.setSelectorChains(newChains);
            
            nestSelectors(subrule);
        }
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
    
    private void processRule(Rule rule)
    throws SerializationException
    {
        // Fetch all includes and just copy all the subrules
        // and properties of each mixin to this rule.
        for (IncludeDirective include : rule.getIncludes()) {
            Mixin mixin = mixins.get(include.getMixinName());
            if (mixin == null) {
                throw new SerializationException("Mixin " + include.getMixinName() + " was not found.");
            }
            rule.addSubRules(mixin.getSubRules());

            /*renderProperties(mixin.getProperties(), 
                mixin.getParameterMap(include), 
                indentLevel);*/
            rule.addProperties(mixin.getProperties());
        }
        
        rule.setIncludes(new ArrayList<IncludeDirective>());

        ruleList.add(rule);
        
        // Render subrules
        for (Rule subrule : rule.getSubRules()) {
            processRule(subrule);
        }
    }
}

package net.quenchnetworks.sassybarista.sass;

import java.io.*;
import java.util.*;

import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.models.*;
import net.quenchnetworks.sassybarista.sass.value.*;
import net.quenchnetworks.sassybarista.sass.expression.*;

public class SassSheetSerializer
{
    private PrintStream writer;

    public SassSheetSerializer(PrintStream writer)
    {
        this.writer = writer;
    }

    public void render(SassSheet sheet)
    throws ParseException
    {
        // serialize
        for (Map.Entry<String, INode> entry : sheet.getVariables().entrySet()) {
            writer.print(entry.getKey());
            writer.print(": ");
            writer.print(entry.getValue());
            writer.println(";");
        }

        for (Mixin mixin : sheet.getMixins().values()) {
            renderMixin(mixin);
        }

        for (Rule rule : sheet.getRules()) {
            renderRule(rule, 0);
        }

        for (MediaBlock block : sheet.getMediaBlocks()) {
            renderMediaBlock(block);
        }
    }

    private String generateIndent(int indentLevel)
    {
        StringBuilder buf = new StringBuilder();
        for (int i = 0; i < indentLevel; i++) {
            buf.append("\t");
        }

        return buf.toString();
    }

    private void renderMediaBlock(MediaBlock block)
    {
        writer.println("@media " + block.getMediaQueryString() + " {");
        for (Rule rule : block.getRules()) {
            renderRule(rule, 1);
        }
        writer.println("}");
    }

    private void renderMixin(Mixin mixin)
    {
        writer.print("@mixin ");
        writer.print(mixin.getName());

        List<String> params = mixin.getParameters();
        if (params.size() > 0) {
            writer.print("(");
            String delim = "";
            for (String param : params) {
                writer.print(delim);
                writer.print(param);
                delim = ",";
            }
            writer.print(") ");
        }
        writer.println("{");

        renderProperties(mixin.getProperties(), 0);

        writer.println("}");
    }

    private void renderRule(Rule rule, int indentLevel)
    {
        String indent = generateIndent(indentLevel);

        // If it's an import rule render it and get out:
        if (rule.isImportRule()) {
            writer.print("@import ");

            ImportRule importRule = rule.asImportRule();
            String wrapper = (importRule.getImportRef().startsWith("url")) ? "" : "\"";

            writer.print(wrapper);
            writer.print(importRule.getImportRef());
            writer.print(wrapper);

            if (importRule.getMedia().length() > 0) {
                writer.print(" ");
                writer.print(importRule.getMedia());
            }
            writer.println(";");
            return;
        }



        // Render all selectors
        int i = 0;
        List<SelectorChain> selectorChains = rule.getSelectorChains();
        for (SelectorChain selectorChain : selectorChains) {
            i++;
            writer.print(indent);
            writer.print(selectorChain.toString());
            if (i == selectorChains.size()) {
                writer.println(" {");
            } else {
                writer.println(",");
            }
        }

        // Render extend directives
        for (Selector extend : rule.getExtends()) {
            writer.print(indent);
            writer.print("\t@extend ");
            writer.print(extend);
            writer.println(";");
        }

        // Render include directives
        for (IncludeDirective include : rule.getIncludes()) {
            writer.print(indent);
            writer.print("\t@include ");
            writer.print(include.getMixinName());

            List<IPropertyValue> params = include.getParameters();
            if (params.size() > 0) {
                writer.print("(");
                String delim = "";
                for (IPropertyValue param : params) {
                    writer.print(delim);
                    writer.print(param);
                    delim = ",";
                }
                writer.print(")");
            }

            writer.println(";");
        }

        // Render all properties
        renderProperties(rule.getProperties(), indentLevel);

        // Render subrules
        for (Rule subrule : rule.getSubRules()) {
            renderRule(subrule, indentLevel + 1);
        }

        writer.print(indent);
        writer.println("}");
        writer.println();
    }

    private void renderProperties(List<Property> properties, int indentLevel)
    {
        String indent = generateIndent(indentLevel);

        for (Property property : properties) {
            writer.print(indent);
            writer.print("\t" + property.getKey() + ": ");
            List<INode> values = property.getValues();
            int i = 0;
            for (INode value : values) {
                writer.print(value.toString());
                if (i < values.size() - 1) {
                    writer.print(" ");
                }
                i++;
            }
            writer.println(";");
        }
    }
}

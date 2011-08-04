package net.quenchnetworks.sassybarista.sass;

import java.io.*;
import java.util.*;

import net.quenchnetworks.sassybarista.sass.models.*;
import net.quenchnetworks.sassybarista.sass.value.*;

public class SassSheetSerializer
{
    private PrintStream writer;
    private Map<String, IPropertyValue> variables = null;
    private Map<String, Mixin> mixins = null;

    public SassSheetSerializer(PrintStream writer)
    {
        this.writer = writer;
    }
    
    public void render(SassSheet sheet)
    throws ParseException, SerializationException
    {
        this.variables = sheet.getVariables();
        this.mixins = sheet.getMixins();

        // serialize
        for (Map.Entry<String, IPropertyValue> entry : variables.entrySet()) {
            writer.print(entry.getKey());
            writer.print(": ");
            writer.print(entry.getValue());
            writer.println(";");
        }
        
        for (Mixin mixin : mixins.values()) {
            renderMixin(mixin);
        }
        
        for (Rule rule : sheet.getRules()) {
            renderRule(rule, 0);
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
    
    private void renderMixin(Mixin mixin)
    throws SerializationException
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
        
        renderProperties(mixin.getProperties(), new HashMap<String, IPropertyValue>(), 0);
        
        writer.println("}");
    }
    
    private void renderRule(Rule rule, int indentLevel)
    throws SerializationException
    {
        String indent = generateIndent(indentLevel);
    
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
        renderProperties(rule.getProperties(), variables, indentLevel);

        // Render subrules
        for (Rule subrule : rule.getSubRules()) {
            renderRule(subrule, indentLevel + 1);
        }
        
        writer.print(indent);
        writer.println("}");
        writer.println();
    }
    
    private void renderProperties(List<Property> properties, Map<String, IPropertyValue> scope, int indentLevel) 
    throws SerializationException
    {
        String indent = generateIndent(indentLevel);
    
        for (Property property : properties) {
            writer.print(indent);
            writer.print("\t" + property.getKey() + ": ");
            List<IPropertyValue> values = property.getValues();
            int i = 0;
            for (IPropertyValue value : values) {
                writer.print(value.serialize(scope));
                if (i < values.size() - 1) {
                    writer.print(" ");
                }
                i++;
            }
            writer.println(";");                
        }
    }
}

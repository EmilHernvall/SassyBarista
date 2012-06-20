package net.quenchnetworks.sassybarista.sass.models;

import java.io.Serializable;
import java.util.*;

public class Selector implements Serializable
{
    public enum Combinator implements Serializable
    {
        DESCENDANT_OF(""),
        CHILD_OF(">"),
        DIRECTLY_PRECEDED_BY("+"),
        PRECEDED_BY("~");
        
        private String symbol;        
        private Combinator(String symbol) { this.symbol = symbol; }
        
        public String getCharacter() { return symbol; }
        
        @Override
        public String toString() { return symbol; }
    };
    
    public enum AttributeSelectorType implements Serializable
    {
        WITH_ATTRIBUTE(""),
        EQUAL("="),
        INCLUDES("~="),
        PREFIXMATCH("^="),
        SUFFIXMATCH("$="),
        SUBSTRINGMATCH("*="),
        DASHMATCH("|=");

        private String symbol;        
        private AttributeSelectorType(String symbol) { this.symbol = symbol; }
        
        public String getCharacter() { return symbol; }
        
        @Override
        public String toString() { return symbol; }
    };
    
    public static class AttributeSelector implements Serializable
    {
        private String attribute = null;
        private String value = null;
        private AttributeSelectorType type = AttributeSelectorType.WITH_ATTRIBUTE;
        
        public AttributeSelector()
        {
        }
        
        public void setType(AttributeSelectorType v) { this.type = v; }
        public AttributeSelectorType getType() { return type; }
        
        public void setAttribute(String v) { this.attribute = v; }
        public String getAttribute() { return attribute; }
        
        public void setValue(String v) { this.value = v; }
        public String getValue() { return value; }
        
        public AttributeSelector copy()
        {
            AttributeSelector attrSel = new AttributeSelector();
            attrSel.attribute = attribute;
            attrSel.value = value;
            attrSel.type = type;
            
            return attrSel;
        }
        
        @Override
        public int hashCode()
        {
            int code = 17;
            code = 31 * code + (attribute != null ? attribute.hashCode() : 0);
            code = 31 * code + (value != null ? value.hashCode() : 0);
            code = 31 * code + (type != null ? type.hashCode() : 0);
            
            return code;
        }
        
        @Override
        public boolean equals(Object b)
        {
            if (!(b instanceof AttributeSelector)) {
                return false;
            }
        
            return hashCode() == b.hashCode();
        }
        
        @Override
        public String toString()
        {
            if (type != AttributeSelectorType.WITH_ATTRIBUTE) {
                return attribute + type + "\"" + value + "\"";
            } else {
                return attribute;
            }
        }
    }

    private boolean parentRef = false;
    private Combinator combinator = Combinator.DESCENDANT_OF;
    private String element = null;
    private String id = null;
    private List<String> classNames = null;
    private String pseudoClass = null;
    private String pseudoClassParam = null;
    private AttributeSelector attributeSelector = null;

    public Selector()
    {
        this.classNames = new ArrayList<String>();
    }

    public void setParentRef(boolean v) { this.parentRef = v; }
    public boolean isParentRef() { return parentRef; }
    
    public void setCombinator(Combinator v) { this.combinator = v; }
    public Combinator getCombinator() { return combinator; }
    
    public void setElement(String v) { this.element = v; }
    public String getElement() { return element; }
    
    public void setId(String v) { this.id = v; }
    public String getId() { return id; }
    
    public void setClassNames(List<String> v) { this.classNames = v; }
    public void addClassName(String v) { this.classNames.add(v); }
    public List<String> getClassNames() { return classNames; }
    
    public void setPseudoClass(String v) { this.pseudoClass = v; }
    public String getPseudoClass() { return pseudoClass; }
    
    public void setPseudoClassParameter(String v) { this.pseudoClassParam = v; }
    public String getPseudoClassParameter() { return pseudoClassParam; }
    
    public void setAttributeSelector(AttributeSelector v) { this.attributeSelector = v; }
    public AttributeSelector getAttributeSelector() { return attributeSelector; }
    
    public boolean matches(Selector b)
    {
        if (element != null && !element.equals(b.getElement())) {
            return false;
        }
        
        if (id != null && !id.equals(b.getId())) {
            return false;
        }
        
        List<String> cmpClassNames = b.getClassNames();
        for (String className : classNames) {
            if (!cmpClassNames.contains(className)) {
                return false;
            }
        }
        
        if (pseudoClass != null && !pseudoClass.equals(b.getPseudoClass())) {
            return false;
        }
        
        if (pseudoClassParam != null && !pseudoClassParam.equals(b.getPseudoClassParameter())) {
            return false;
        }
        
        if (attributeSelector != null && !attributeSelector.equals(b.getAttributeSelector())) {
            return false;
        }
        
        return true;
    }
    
    public Selector copy()
    {
        Selector sel = new Selector();
        sel.combinator = combinator;
        sel.parentRef = parentRef;
        sel.element = element;
        sel.id = id;
        sel.classNames = new ArrayList<String>(classNames);
        sel.pseudoClass = pseudoClass;
        sel.pseudoClassParam = pseudoClassParam;
        sel.attributeSelector = attributeSelector != null ? attributeSelector.copy() : null;
        
        return sel;
    }
    
    @Override
    public int hashCode()
    {
        int code = 17;
        code = 31 * code + (parentRef ? 1 : 0);
        code = 31 * code + combinator.hashCode();
        code = 31 * code + (element != null ? element.hashCode() : 0);
        code = 31 * code + (id != null ? id.hashCode() : 0);
        for (String className : classNames) {
            code = 31 * code + className.hashCode();
        }
        code = 31 * code + (pseudoClass != null ? pseudoClass.hashCode() : 0);
        code = 31 * code + (pseudoClassParam != null ? pseudoClassParam.hashCode() : 0);
        code = 31 * code + (attributeSelector != null ? attributeSelector.hashCode() : 0);
        
        return code;
    }
    
    @Override
    public boolean equals(Object b)
    {
        if (!(b instanceof Selector)) {
            return false;
        }
        
        return hashCode() == b.hashCode();
    }
    
    @Override
    public String toString()
    {
        StringBuilder buffer = new StringBuilder();
        if (combinator != null && combinator != Combinator.DESCENDANT_OF) {
            buffer.append(combinator.toString());
            buffer.append(" ");
        }
        if (parentRef) {
            buffer.append("&");
        }
        if (element != null) {
            buffer.append(element);
        }
        if (id != null) {
            buffer.append("#");
            buffer.append(id);
        }
        for (String className : classNames) {
            buffer.append(".");
            buffer.append(className);
        }
        if (pseudoClass != null) {
            buffer.append(":");
            buffer.append(pseudoClass);
            if (pseudoClassParam != null) {
                buffer.append("(");
                buffer.append(pseudoClassParam);
                buffer.append(")");
            }
        }
        if (attributeSelector != null) {
            buffer.append("[");
            buffer.append(attributeSelector.toString());
            buffer.append("]");
        }
        
        return buffer.toString();
    }
}

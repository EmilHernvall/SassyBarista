package net.quenchnetworks.sassybarista.sass;

import java.util.*;

public class Selector
{
	public enum Combinator {
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
	
	public enum AttributeSelectorType
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
	
	public static class AttributeSelector
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
				return String.format("%s%s\"%s\"", attribute, type, value);
			} else {
				return attribute;
			}
		}
	}

	private Combinator combinator = Combinator.DESCENDANT_OF;
	private String element = null;
	private String id = null;
	private String className = null;
	private String pseudoClass = null;
	private String pseudoClassParam = null;
	private AttributeSelector attributeSelector = null;

	public Selector()
	{
	}
	
	public void setCombinator(Combinator v) { this.combinator = v; }
	public Combinator getCombinator() { return combinator; }
	
	public void setElement(String v) { this.element = v; }
	public String getElement() { return element; }
	
	public void setId(String v) { this.id = v; }
	public String getId() { return id; }
	
	public void setClassName(String v) { this.className = v; }
	public String getClassName() { return className; }
	
	public void setPseudoClass(String v) { this.pseudoClass = v; }
	public String getPseudoClass() { return pseudoClass; }
	
	public void setPseudoClassParameter(String v) { this.pseudoClassParam = v; }
	public String getPseudoClassParameter() { return pseudoClassParam; }
	
	public void setAttributeSelector(AttributeSelector v) { this.attributeSelector = v; }
	public AttributeSelector getAttributeSelector() { return attributeSelector; }
    
    @Override
    public int hashCode()
    {
        int code = 17;
        code = 31 * code + combinator.hashCode();
        code = 31 * code + (element != null ? element.hashCode() : 0);
        code = 31 * code + (id != null ? id.hashCode() : 0);
        code = 31 * code + (className != null ? className.hashCode() : 0);
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
		if (element != null) {
			buffer.append(element);
		}
		if (id != null) {
			buffer.append("#");
			buffer.append(id);
		}
		if (className != null) {
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

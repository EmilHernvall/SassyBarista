package net.quenchnetworks.sassybarista.sass;

import java.io.*;

import org.junit.Test;
import static org.junit.Assert.*;

import net.quenchnetworks.sassybarista.sass.models.*;

public class TestRendering
{
    public String render(String in)
    throws ParseException, EvaluationException, UnsupportedEncodingException
    {
        SassParser parser = new SassParser(new StringReader(in));
        SassSheet sheet = parser.parse();
    
        SassSheetEvaluator evaluator = new SassSheetEvaluator();
        evaluator.evaluate(sheet);
    
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        SassSheetSerializer serializer = new SassSheetSerializer(new PrintStream(os));
        
        serializer.render(sheet);
        
        String result = os.toString("UTF-8");
        result = result.trim();
        result = result.replace("\r\n","\n");
        
        return result;
    }
    
    @Test
    public void nesting()
    {
        try {
            String basicNestingScss = 
                "div {\n" +
                    "\tfont-family: Verdana;\n" +
                    "\tspan {\n" +
                        "\t\tfont-weight: bold;\n" +
                    "\t}\n" +
                "}";
                
            String basicNestingRendered =
                "div {\n" +
                    "\tfont-family: Verdana;\n" +
                "}\n\n" +
                "div span {\n" +
                    "\tfont-weight: bold;\n" +
                "}";
                
            assertEquals("Basic nesting test failed.", basicNestingRendered, render(basicNestingScss));
        }
        catch (ParseException e) {
            fail("Caught ParseException.");
        }
        catch (EvaluationException e) {
            fail("Caught EvaluationException.");
        }
        catch (UnsupportedEncodingException e) {
            fail("Caught UnsupportedEncodingException.");
        }
            
        try {
            String permutationNestingScss = 
                "div.a,\n" +
                "div.b {\n" +
                    "\tfont-family: Verdana;\n" +
                    "\tspan {\n" +
                        "\t\tfont-weight: bold;\n" +
                    "\t}\n" +
                "}";
                
            String permutationNestingRendered =
                "div.a,\n" +
                "div.b {\n" +
                    "\tfont-family: Verdana;\n" +
                "}\n\n" +
                "div.a span,\n" +
                "div.b span {\n" +
                    "\tfont-weight: bold;\n" +
                "}";
                
            assertEquals("Permutation nesting test failed.", permutationNestingRendered, render(permutationNestingScss));
        }
        catch (ParseException e) {
            e.printStackTrace();
            fail("Caught ParseException.");
        }
        catch (EvaluationException e) {
            fail("Caught EvaluationException.");
        }
        catch (UnsupportedEncodingException e) {
            fail("Caught UnsupportedEncodingException.");
        }
    }
    
    @Test
    public void mixin()
    {
        try {
            
            {
                String mixinScss = 
                    "@mixin warning {\n" +
                        "\tpadding: 20px;\n" +
                        "\ttext-align: center;\n" +
                        "\tfont-weight: bold;\n" +
                    "}\n" +
                    "div.somewarning {\n" +
                        "\tcolor: red;\n" +
                        "\t@include warning;\n" +
                    "}\n";
                    
                String mixinRendered = 
                    "div.somewarning {\n" +
                        "\tcolor: red;\n" +
                        "\tpadding: 20px;\n" +
                        "\ttext-align: center;\n" +
                        "\tfont-weight: bold;\n" +
                    "}";
                
                assertEquals("Mixin test failed.", mixinRendered, render(mixinScss));
            }
            
            {
                String parameterMixinScss = 
                    "@mixin box($width, $color, $radius) {\n" +
                        "\tborder: $width $color solid;\n" +
                        "\t-webkit-border-radius: $radius;\n" +
                        "\t-moz-border-radius: $radius;\n" +
                        "\t-ms-border-radius: $radius;\n" +
                        "\tborder-radius: $radius;\n" +
                    "}\n" +

                    "div#box {\n" +
                        "\tpadding: 20px;\n" +
                        "\twidth: 600px;\n" +
                        "\t@include box(1px, #000, 30px);\n" +
                    "}\n";
                    
                String parameterMixinRendered = 
                    "div#box {\n" +
                        "\tpadding: 20px;\n" +
                        "\twidth: 600px;\n" +
                        "\tborder: 1px #000 solid;\n" +
                        "\t-webkit-border-radius: 30px;\n" +
                        "\t-moz-border-radius: 30px;\n" +
                        "\t-ms-border-radius: 30px;\n" +
                        "\tborder-radius: 30px;\n" +
                    "}";
                    
                    assertEquals("Parameter mixin test failed.", parameterMixinRendered, render(parameterMixinScss));
            }
        }
        catch (ParseException e) {
            fail("Caught ParseException.");
        }
        catch (EvaluationException e) {
            fail("Caught EvaluationException.");
        }
        catch (UnsupportedEncodingException e) {
            fail("Caught UnsupportedEncodingException.");
        }
    }
    
    @Test
    public void variables()
    {
        try {
        
            {
                String variableScss = 
                    "$font: verdana;\n" +
                    "$color: #f00;\n" +
                    "div.warning {\n" +
                        "\tfont-family: $font;\n" +
                        "\tcolor: $color;\n" +
                    "}\n";
                    
                String variableRendered = 
                    "div.warning {\n" +
                        "\tfont-family: verdana;\n" +
                        "\tcolor: #f00;\n" +
                    "}";
                    
                assertEquals("Variable substitution test failed.", variableRendered, render(variableScss));
            }
        }
        catch (ParseException e) {
            fail("Caught ParseException.");
        }
        catch (EvaluationException e) {
            fail("Caught EvaluationException.");
        }
        catch (UnsupportedEncodingException e) {
            fail("Caught UnsupportedEncodingException.");
        }
    }

    @Test
    public void selectors()
    {
        try {
        
            {
                String elementSelector = 
                    "div {\n" +
                        "\tfont-family: Verdana;\n" +
                    "}";
                
                assertEquals("Parsing of element selector failed.", elementSelector, render(elementSelector));
            }
            
            {
                String idSelector = 
                    "#wrapper {\n" +
                        "\tfont-family: Verdana;\n" +
                    "}";
                
                assertEquals("Parsing of id selector failed.", idSelector, render(idSelector));
            }
            
            {
                String classSelector = 
                    ".normal {\n" +
                        "\tfont-family: Verdana;\n" +
                    "}";
                
                assertEquals("Parsing of class selector failed.", classSelector, render(classSelector));
            }
            
            {
                String elementIdSelector = 
                    "div#wrapper {\n" +
                        "\tfont-family: Verdana;\n" +
                    "}";
                
                assertEquals("Parsing of element selector and id selector combination failed.", 
                    elementIdSelector, render(elementIdSelector));
            }
            
            {
                String elementClassSelector = 
                    "div.normal {\n" +
                        "\tfont-family: Verdana;\n" +
                    "}";
                
                assertEquals("Parsing of element selector and class selector combination failed.", 
                    elementClassSelector, render(elementClassSelector));
            }
            
            {
                String elementIdClassSelector = 
                    "div#wrapper.normal {\n" +
                        "\tfont-family: Verdana;\n" +
                    "}";
                
                assertEquals("Parsing of element selector, id selector and class selector combination failed.", 
                    elementIdClassSelector, render(elementIdClassSelector));
            }
            
            {
                String pseudoClassSelector = 
                    "a:hover {\n" +
                        "\tfont-family: Verdana;\n" +
                    "}";
                
                assertEquals("Parsing of element selector failed.", pseudoClassSelector, render(pseudoClassSelector));
            }
            
            {
                String withAttributeSelector = 
                    "a[href] {\n" +
                        "\tfont-family: Verdana;\n" +
                    "}";
                
                assertEquals("Parsing of with attribute selector failed.", withAttributeSelector, render(withAttributeSelector));
            }
            
            {
                String equalAttributeSelector = 
                    "input[type=\"text\"] {\n" +
                        "\tfont-family: Verdana;\n" +
                    "}";
                
                assertEquals("Parsing of equal attribute selector failed.", equalAttributeSelector, render(equalAttributeSelector));
            }
            
            {
                String includesAttributeSelector = 
                    "a[class~=\"text\"] {\n" +
                        "\tfont-family: Verdana;\n" +
                    "}";
                
                assertEquals("Parsing of includes attribute selector failed.", includesAttributeSelector, render(includesAttributeSelector));
            }
            
            {
                String prefixAttributeSelector = 
                    "a[class^=\"text\"] {\n" +
                        "\tfont-family: Verdana;\n" +
                    "}";
                
                assertEquals("Parsing of prefix attribute selector failed.", prefixAttributeSelector, render(prefixAttributeSelector));
            }
            
            {
                String suffixAttributeSelector = 
                    "a[class$=\"text\"] {\n" +
                        "\tfont-family: Verdana;\n" +
                    "}";
                
                assertEquals("Parsing of suffix attribute selector failed.", suffixAttributeSelector, render(suffixAttributeSelector));
            }
            
            {
                String substringAttributeSelector = 
                    "a[class*=\"text\"] {\n" +
                        "\tfont-family: Verdana;\n" +
                    "}";
                
                assertEquals("Parsing of substring attribute selector failed.", substringAttributeSelector, render(substringAttributeSelector));
            }
            
            {
                String dashAttributeSelector = 
                    "a[class|=\"text\"] {\n" +
                        "\tfont-family: Verdana;\n" +
                    "}";
                
                assertEquals("Parsing of substring attribute selector failed.", dashAttributeSelector, render(dashAttributeSelector));
            }
            
            {
                String childOfCombinator = 
                    "div > a {\n" +
                        "\tfont-family: Verdana;\n" +
                    "}";
                
                assertEquals("Parsing of child combinator failed.", childOfCombinator, render(childOfCombinator));
            }
            
            {
                String directlyPrecededByCombinator = 
                    "div + a {\n" +
                        "\tfont-family: Verdana;\n" +
                    "}";
                
                assertEquals("Parsing of directly preceded by combinator failed.", directlyPrecededByCombinator, render(directlyPrecededByCombinator));
            }
            
            {
                String precededByCombinator = 
                    "div ~ a {\n" +
                        "\tfont-family: Verdana;\n" +
                    "}";
                
                assertEquals("Parsing of preceded by combinator failed.", precededByCombinator, render(precededByCombinator));
            }
        }
        catch (ParseException e) {
            fail("Caught ParseException.");
        }
        catch (EvaluationException e) {
            fail("Caught EvaluationException.");
        }
        catch (UnsupportedEncodingException e) {
            fail("Caught UnsupportedEncodingException.");
        }
    }
}

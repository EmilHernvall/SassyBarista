package net.quenchnetworks.sassybarista;

import java.io.*;

import org.junit.Test;
import static org.junit.Assert.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.models.*;

public class TestProcessing
{
    public void processTest(String testCase)
    {
        try {
            // load reference text
            String cssFile = "testcases/" + testCase + ".css";
           
            BufferedReader reader = new BufferedReader(new FileReader(cssFile));
            StringBuilder refTextBuffer = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                refTextBuffer.append(line);
                refTextBuffer.append("\n");
            }
            
            reader.close();
            
            String refText = refTextBuffer.toString();
            refText = refText.trim();
            refText = refText.replace("\r\n","\n");
            refText = refText.replace("\t","");
            refText = refText.replace("    ","");
        
            // load and parse scss
            String scssFile = "testcases/" + testCase + ".scss";
        
            SassParser parser = new SassParser(new FileReader(scssFile));
            SassSheet sheet = parser.parse();
        
            sheet = sheet.copy();
        
            SassSheetEvaluator evaluator = new SassSheetEvaluator(new JavaStringInterpolator());
            evaluator.evaluate(sheet);
        
            ByteArrayOutputStream os = new ByteArrayOutputStream();
            SassSheetSerializer serializer = new SassSheetSerializer(new PrintStream(os));
            
            serializer.render(sheet);
            
            String processedText = os.toString("UTF-8");
            processedText = processedText.trim();
            processedText = processedText.replace("\r\n","\n");
            processedText = processedText.replace("\t","");
            processedText = processedText.replace("    ","");
            
            if (!refText.equals(processedText)) {
                System.out.println(testCase + " failed.");
                System.out.println("reference text:");
                System.out.print(refText);
                System.out.println("\n");
                
                System.out.println("processed text:");
                System.out.print(processedText);
                System.out.println("\n");
            
                fail();
            } else {
                assertTrue(true);
            }
        }
        catch (ParseException e) {
            System.out.println("ParseException occured for " + testCase);
            e.printStackTrace();
            fail("Caught ParseException.");
        }
        catch (EvaluationException e) {
            e.printStackTrace();
            fail("Caught EvaluationException.");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            fail("Caught UnsupportedEncodingException.");
        }
        catch (IOException e) {
            e.printStackTrace();
            fail("Caught IOException.");
        }
    }

    @Test
    public void imports()
    {
        processTest("import_css");
    }
    
    @Test
    public void nesting()
    {
        processTest("nesting_basic");
        processTest("nesting_permutation");
    }
    
    @Test
    public void mixins()
    {
        processTest("mixin");
        processTest("mixin_parameter");
    }
    
    @Test
    public void variables()
    {
        processTest("variable");
    }

    @Test
    public void selectors()
    {
        processTest("selectors");
    }
    
    @Test 
    public void expressions()
    {
        processTest("expression_arithmetic");
    }
    
    @Test 
    public void inheritance()
    {
        processTest("extend_basic");
        processTest("extend_multiple");
        processTest("extend_chain");
        processTest("extend_complex");
    }
    
    @Test 
    public void controlstructures()
    {
        processTest("controlstructure_if");
    }

    @Test 
    public void parentReferences()
    {
        processTest("parentref");
    }

    @Test 
    public void interpolation()
    {
        processTest("interpolation");
    }
}

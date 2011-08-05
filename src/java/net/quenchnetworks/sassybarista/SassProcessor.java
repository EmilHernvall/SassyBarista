package net.quenchnetworks.sassybarista;

import java.util.*;
import java.io.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.value.*;
import net.quenchnetworks.sassybarista.sass.models.*;

public class SassProcessor
{
    public static void main(String[] args)
    throws Exception
    {
        if (args.length == 0) {
            System.out.println("No input specified.");
            return;
        }
        
        File file = new File(args[0]);
        if (!file.exists()) {
            System.out.println("File not found.");
            return;
        }
        
        SassParser parser = new SassParser(new FileInputStream(file));
        SassSheet sheet = parser.parse();
        
        SassSheet copy = sheet.copy();
        
        SassSheetEvaluator evaluator = new SassSheetEvaluator();
        evaluator.addFunction("formatProperty", new IFunction() {
            public IPropertyValue evaluate(List<IPropertyValue> params)
            throws EvaluationException {
                return params.get(0);
            }
        });
        
        evaluator.evaluate(copy);
        
        SassSheetSerializer serializer = new SassSheetSerializer(System.out);
        serializer.render(copy);
    }
}

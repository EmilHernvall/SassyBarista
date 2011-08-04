package net.quenchnetworks.sassybarista;

import java.io.*;

import net.quenchnetworks.sassybarista.sass.*;
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
        
        SassSheetEvaluator evaluator = new SassSheetEvaluator();
        evaluator.evaluate(sheet);
        
        SassSheetSerializer serializer = new SassSheetSerializer(System.out);
        serializer.render(sheet);
    }
}

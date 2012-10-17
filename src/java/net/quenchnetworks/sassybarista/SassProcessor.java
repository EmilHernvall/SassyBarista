package net.quenchnetworks.sassybarista;

import java.util.*;
import java.io.*;
import java.math.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.value.*;
import net.quenchnetworks.sassybarista.sass.models.*;
import net.quenchnetworks.sassybarista.sass.expression.*;

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

        Map<String, INode> variables = sheet.getVariables();
        Map<String, INode> newVariables = new LinkedHashMap<String, INode>();
        //newVariables.put("$id", new DefaultPropertyValue("0"));
        newVariables.putAll(variables);

        /*for (Map.Entry<String, INode> entry : newVariables.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }*/

        sheet.setVariables(newVariables);

        SassSheet copy = sheet.copy();

        SassSheetEvaluator evaluator =
            new SassSheetEvaluator(new JavaStringInterpolator());
        evaluator.evaluate(copy);

        SassSheetSerializer serializer = new SassSheetSerializer(System.out);
        serializer.render(copy);
    }
}

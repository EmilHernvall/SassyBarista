package net.quenchnetworks.sassybarista.sass;

import java.util.*;
import java.util.regex.*;

import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.value.*;
import net.quenchnetworks.sassybarista.sass.expression.*;

public class JavaStringInterpolator implements SassSheetEvaluator.StringInterpolator
{
    public JavaStringInterpolator()
    {
    }

    public String applyVariables(String str, ExpressionEvaluator evaluator, Map<String, IPropertyValue> scope)
    throws EvaluationException
    {
        Pattern pattern = Pattern.compile("#\\{(\\$\\w+)\\}");
        Matcher matcher = pattern.matcher(str);

        while (matcher.find()) {
            String var = matcher.group(1);
            IPropertyValue val = scope.get(var);
            if (val == null) {
                continue;
            }
            IPropertyValue flat = evaluator.evaluate(val, scope);
            str = str.replace("#{" + var + "}", flat.toString());
        }

        return str;
    }
}


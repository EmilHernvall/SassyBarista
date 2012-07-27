package net.quenchnetworks.sassybarista;

import net.quenchnetworks.sassybarista.sass.JavaStringInterpolator;
import net.quenchnetworks.sassybarista.sass.SassParser;
import net.quenchnetworks.sassybarista.sass.SassSheetSerializer;
import net.quenchnetworks.sassybarista.sass.eval.EvaluationException;
import net.quenchnetworks.sassybarista.sass.eval.IFunction;
import net.quenchnetworks.sassybarista.sass.eval.SassSheetEvaluator;
import net.quenchnetworks.sassybarista.sass.models.SassSheet;
import net.quenchnetworks.sassybarista.sass.value.DefaultPropertyValue;
import net.quenchnetworks.sassybarista.sass.value.IPropertyValue;
import net.quenchnetworks.sassybarista.sass.value.NumberPropertyValue;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.io.Reader;
import java.io.StringReader;
import java.text.DecimalFormat;
import java.util.List;

import static net.quenchnetworks.sassybarista.IoStreamHandling.closeQuietly;
import static net.quenchnetworks.sassybarista.IoStreamHandling.readFileAsString;

public class SassFileProcessor {
    public String parseSomeSassFrom(File file) {
        if (!file.exists()) {
            throw new RuntimeException("Could not find my test sass - looking in " + file.getAbsolutePath());
        }

        Reader in = null;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        String withImports = "Not yet parsed";

        try {
            in = new FileReader(file);
            //withImports = preProcessImports(in, file);
            withImports = readFileAsString(file, "UTF8");

            SassParser parser = new SassParser(new StringReader(withImports));
            SassSheet sheet = parser.parse(file.getParentFile());


            SassSheetEvaluator evaluator = new SassSheetEvaluator(new JavaStringInterpolator());
            evaluator.addFunction("formatProperty", new IFunction() {
                public IPropertyValue evaluate(List<IPropertyValue> params)
                        throws EvaluationException {
                    if ("test".equals(((DefaultPropertyValue) params.get(0)).getValue())) {
                        return new NumberPropertyValue("3");
                    } else if ("title".equals(((DefaultPropertyValue) params.get(0)).getValue())) {
                        return new DefaultPropertyValue("2");
                    } else {
                        return new DefaultPropertyValue("1");
                    }
                }
            });

            evaluator.evaluate(sheet);

            SassSheetSerializer serializer = new SassSheetSerializer(new PrintStream(out));
            serializer.render(sheet);

            return new String(out.toByteArray());
        } catch (Throwable t) {
            System.out.println(debugSource(withImports));
            throw new RuntimeException(t);
        } finally {
            closeQuietly(in);
            closeQuietly(out);
        }
    }

    public String debugSource(String withImports) {
        BufferedReader in = new BufferedReader(new StringReader(withImports));

        DecimalFormat df = new DecimalFormat("0000");

        StringBuilder out = new StringBuilder();
        int lineNo = 1;
        try {
            while (true) {
                String line = in.readLine();
                if (line == null) {
                    break;
                }

                out.append(df.format(lineNo++)).append("   ").append(line).append("\n");

            }

            return out.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String preProcessImports(Reader in, File sourceFile) {
        StringBuilder out = new StringBuilder();

        BufferedReader bin = new BufferedReader(in);
        try {

            while (true) {
                String line = bin.readLine();
                if (line == null) {
                    break;
                }

                if (line.trim().startsWith("@import")) {
                    out.append(importFileFrom(line, sourceFile));
                } else {
                    out.append(line);
                }
            }

            return out.toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String importFileFrom(String line, File sourceFile) {
        File parent = sourceFile.getParentFile();
        String[] splits = line.split("\"");

        String importFileName = importFileNameFrom(splits[1]);


        return "/* from " + importFileName + "*/\n\n"
                + readFileAsString(new File(parent, importFileName), "UTF8");
    }

    public String importFileNameFrom(String importKey) {
        int posToInsertUnderscore = importKey.lastIndexOf("/");


        if (posToInsertUnderscore == -1) {
            return new StringBuilder()
                    .append("_")
                    .append(importKey)
                    .append(".scss")
                    .toString();
        }

        String pre = importKey.substring(0, posToInsertUnderscore);
        String post = importKey.substring(posToInsertUnderscore + 1, importKey.length());


        StringBuilder sb = new StringBuilder();
        sb.append(pre).append("/_").append(post).append(".scss");

        return sb.toString();
    }
}
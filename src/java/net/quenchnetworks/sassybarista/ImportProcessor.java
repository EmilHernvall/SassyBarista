package net.quenchnetworks.sassybarista;

import net.quenchnetworks.sassybarista.sass.SassParser;
import net.quenchnetworks.sassybarista.sass.models.ImportRule;
import net.quenchnetworks.sassybarista.sass.models.SassSheet;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class ImportProcessor {

    public ImportProcessor() {
    }

    public void importSassFrom(ImportRule importRule, SassParser parent, File... importPaths) {

        if (importPaths.length > 1) {
            throw new RuntimeException("Currently we only support a single import path!");
        }

        File scssFile = new File(importPaths[0], importRule.asScssFile());

        FileReader in = null;

        try {
            System.out.println("Importing: " + scssFile.getAbsolutePath());
            in = new FileReader(scssFile);
            SassParser parser = new SassParser(in);
            SassSheet sheet = parser.parse();

            parent.addFrom(sheet);

        } catch (Exception e) {
            throw new RuntimeException(String.format("Could not import file [%s]", scssFile.getAbsolutePath()), e);
        } finally {
            tryToClose(in);
        }

    }

    private static void tryToClose(Reader in) {
        if (in == null) {
            return;
        }

        try {
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
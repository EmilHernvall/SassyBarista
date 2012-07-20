package net.quenchnetworks.sassybarista;

import net.quenchnetworks.sassybarista.sass.SassParser;
import net.quenchnetworks.sassybarista.sass.models.ImportRule;

public class ImportProcessor {

    public ImportProcessor() {
    }

    public void importSassFrom(ImportRule importRule, SassParser parent) {
        System.out.println("Hey I'm going to try and import this: " + importRule.getImportRef());
    }
}
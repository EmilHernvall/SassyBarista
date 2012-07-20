package net.quenchnetworks.sassybarista;

import net.quenchnetworks.sassybarista.sass.SassParser;
import net.quenchnetworks.sassybarista.sass.models.ImportRule;

public class ImportProcessor {

    private final SassParser parent;

    public ImportProcessor(SassParser parent) {
        this.parent = parent;
    }

    public void importSassFrom(ImportRule importRule) {

    }
}
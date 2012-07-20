package net.quenchnetworks.sassybarista.sass.models;

public class ImportRule {

    private final String importRef;
    private final String media;

    public ImportRule(String importRef, String media) {
        this.importRef = importRef;
        this.media = media;
    }

    public String getImportRef() {
        return importRef;
    }

    public String getMedia() {
        return media;
    }
}
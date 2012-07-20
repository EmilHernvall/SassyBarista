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


    public boolean isSassImport() {
        return !isCssImport();
    }

    private boolean isCssImport() {
        return (importRef.startsWith("url")
                || importRef.startsWith("http://")
                || importRef.endsWith(".css")
                || media.length() > 0);
    }
}
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

    public String asScssFile() {
        return importFileNameFrom(importRef);

    }

    private static String importFileNameFrom(String importKey) {
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
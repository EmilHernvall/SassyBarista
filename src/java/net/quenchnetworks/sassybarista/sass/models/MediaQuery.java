package net.quenchnetworks.sassybarista.sass.models;

import java.io.Serializable;
import java.util.*;
import net.quenchnetworks.sassybarista.sass.expression.INode;

public class MediaQuery implements Serializable
{
    private String mediaType = null;
    private boolean only = false;
    private boolean not = false;
    private Map<String, INode> features;

    public MediaQuery()
    {
        features = new HashMap<String, INode>();
    }

    public void setMediaType(String v) { this.mediaType = v; }
    public String getMediaType() { return mediaType; }

    public void setOnlyFlag(boolean v) { this.only = v; }
    public boolean getOnlyFlag() { return only; }

    public void setNotFlag(boolean v) { this.not = v; }
    public boolean getNotFlag() { return not; }

    public void setFeature(String k, INode v)
    {
        features.put(k, v);
    }

    public MediaQuery copy()
    {
        MediaQuery copyTo = new MediaQuery();
        copyTo.mediaType = mediaType;
        copyTo.only = only;
        copyTo.not = not;

        for (Map.Entry<String, INode> entry : features.entrySet()) {
            copyTo.setFeature(entry.getKey(), entry.getValue().copy());
        }

        return copyTo;
    }

    @Override
    public String toString()
    {
        StringBuilder buf = new StringBuilder();

        String delim = "";
        if (mediaType != null) {
            if (only) {
                buf.append("only ");
            } else if (not) {
                buf.append("not ");
            }

            buf.append(mediaType);

            delim = " and ";
        }

        for (Map.Entry<String, INode> entry : features.entrySet()) {
            buf.append(delim);
            buf.append("(");
            buf.append(entry.getKey());
            buf.append(": ");
            buf.append(entry.getValue().toString());
            buf.append(")");

            delim = " and ";
        }

        return buf.toString();
    }
}

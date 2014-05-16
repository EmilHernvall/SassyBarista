package net.quenchnetworks.sassybarista.sass.models;

import java.io.Serializable;
import java.util.*;

public class MediaBlock implements Serializable
{
    private List<MediaQuery> mediaQueries;
    private List<Rule> rules;

    public MediaBlock()
    {
        this.rules = new ArrayList<Rule>();
        this.mediaQueries = new ArrayList<MediaQuery>();
    }

    public void setRules(List<Rule> v)
    {
        this.rules = v;
    }

    public void addRule(Rule rule)
    {
        rules.add(rule);
    }

    public void addRules(List<Rule> newRules)
    {
        rules.addAll(newRules);
    }

    public List<Rule> getRules()
    {
        return rules;
    }

    public void addMediaQuery(MediaQuery qry)
    {
        mediaQueries.add(qry);
    }

    public String getMediaQueryString()
    {
        StringBuilder buf = new StringBuilder();

        String delim = "";
        for (MediaQuery qry : mediaQueries) {
            buf.append(delim);
            buf.append(qry.toString());
            delim = ", ";
        }

        return buf.toString();
    }

    public MediaBlock copy()
    {
        MediaBlock copyTo = new MediaBlock();
        for (Rule rule : rules) {
            copyTo.addRule(rule.copy());
        }
        for (MediaQuery mediaQuery : mediaQueries) {
            // TODO: fix actual copying
            copyTo.addMediaQuery(mediaQuery);
        }

        return copyTo;
    }

    @Override
    public int hashCode()
    {
        int code = 17;
        for (Rule rule : rules) {
            code = 31 * code + rule.hashCode();
        }

        return code;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (!(obj instanceof MediaBlock)) {
            return false;
        }

        return hashCode() == obj.hashCode();
    }
}

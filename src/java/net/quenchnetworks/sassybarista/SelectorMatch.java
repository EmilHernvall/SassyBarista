package net.quenchnetworks.sassybarista;

import java.util.*;
import java.io.*;
import java.math.*;

import net.quenchnetworks.sassybarista.sass.*;
import net.quenchnetworks.sassybarista.sass.eval.*;
import net.quenchnetworks.sassybarista.sass.value.*;
import net.quenchnetworks.sassybarista.sass.models.*;
import net.quenchnetworks.sassybarista.sass.expression.*;

public class SelectorMatch
{
    private static SelectorChain getRuleChain()
    {
        SelectorChain chain = new SelectorChain();

        {
            Selector sel = new Selector();
            sel.setId("_quenchGrid");
            chain.addSelector(sel);
        }

        {
            Selector sel = new Selector();
            sel.setElement("table");
            sel.addClassName("priceTable");
            chain.addSelector(sel);
        }

        {
            Selector sel = new Selector();
            sel.addClassName("storeColumn");
            chain.addSelector(sel);
        }

        {
            Selector sel = new Selector();
            sel.setElement("a");
            chain.addSelector(sel);
        }

        return chain;
    }

    private static List<Selector> getPath()
    {
        List<Selector> path = new ArrayList<Selector>();

        {
            Selector sel = new Selector();
            sel.setElement("div");
            sel.setId("_quenchGrid");
            path.add(sel);
        }

        {
            Selector sel = new Selector();
            sel.setElement("table");
            sel.addClassName("grid");
            path.add(sel);
        }

        {
            Selector sel = new Selector();
            sel.setElement("tbody");
            path.add(sel);
        }

        {
            Selector sel = new Selector();
            sel.setElement("tr");
            path.add(sel);
        }

        {
            Selector sel = new Selector();
            sel.setElement("td");
            sel.addClassName("productCell");
            path.add(sel);
        }

        {
            Selector sel = new Selector();
            sel.setElement("div");
            sel.addClassName("productBox");
            path.add(sel);
        }

        {
            Selector sel = new Selector();
            sel.setElement("table");
            sel.setId("quench_stores_12306024");
            sel.addClassName("priceTable");
            path.add(sel);
        }

        {
            Selector sel = new Selector();
            sel.setElement("tbody");
            path.add(sel);
        }

        {
            Selector sel = new Selector();
            sel.setElement("tr");
            path.add(sel);
        }

        {
            Selector sel = new Selector();
            sel.setElement("td");
            sel.addClassName("storeColumn");
            path.add(sel);
        }

        {
            Selector sel = new Selector();
            sel.setElement("a");
            path.add(sel);
        }

        return path;
    }

    public static void main(String[] args)
    throws Exception
    {
        SelectorChain ruleChain = getRuleChain();
        System.out.println(ruleChain);

        List<Selector> path = getPath();
        for (Selector sel : path) {
            System.out.print(sel + " ");
        }
        System.out.println();

        System.out.println(ruleChain.matchesPath(path));
    }
}

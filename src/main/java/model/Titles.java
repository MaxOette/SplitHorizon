package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The class Titles defines a mutable list of common scientific titles.
 */
public class Titles {

    /**
     * mutable list containing common scientific titles.
     */
    public static List<String> titlesList = new ArrayList<>() {{
        add("Dr\\.\\s*rer\\.\\s*nat\\.");
        add("Dr\\.\\s*h\\.\\s*c\\.\\s*mult\\.");
        add("Dr\\.\\s*habil\\.");
        add("Dr\\.\\s*med\\.");
        add("Dr\\.\\s*phil\\.");
        add("Dr\\.");
        add("Professor");
        add("Prof\\.");
        add("Dr\\.\\s*-\\s*Ing\\.");
        add("Dipl\\.\\s*Ing\\.");
    }};
}

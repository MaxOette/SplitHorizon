package model;

import java.util.ArrayList;
import java.util.List;

/**
 * The class NobleTitles defines a mutable list of nobility-titles.
 */
public class NobleTitles {

    /**
     * mutable list of nobility-titles.
     */
    public static List<String> titlesList = new ArrayList<>() {{
        add("Baron\\s*von");
        add("Freiherr\\s*von");
        add("Freiherr\\s*vom");
        add("von\\s*und\\s*zu");
        add("von");
        add("zu");
    }};


}

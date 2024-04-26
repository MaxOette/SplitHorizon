package model;

import java.util.ArrayList;
import java.util.List;

public class NobleTitles {

    public static List<String> titlesList = new ArrayList<String>() {{
        add("Baron\\s*von");
        add("Freiherr\\s*von");
        add("Freiherr\\s*vom");
        add("von");
        add("zu");
    }};


}

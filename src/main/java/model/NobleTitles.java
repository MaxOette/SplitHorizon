package model;

import java.util.ArrayList;
import java.util.List;

public class NobleTitles {

    public static List<String> titlesList = new ArrayList<String>() {{
        add("Baron von");
        add("Freiherr (von|vom)");
        add("von");
        add("zu");
    }};


}

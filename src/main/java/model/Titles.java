package model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class Titles defines a mutable list of common scientific titles.
 */
public class Titles {

    /**
     * mutable list containing common scientific titles.
     */
    public static List<String> titlesList = initialTitlesList();

    /**
     * Adds a title to list if not present
     *
     * @param title The title that should be added
     */
    public static void addTitle (String title) {
        Pattern titlePattern = Pattern.compile(String.join("|", titlesList));

        Matcher matcher = titlePattern.matcher(title);
        if (!matcher.matches() && !title.trim().isEmpty()) {
            titlesList.add(title.replaceAll(" ", "\\\\" + "s*").replaceAll("\\.", "\\\\" + "."));
        }
    }

    /**
     * initial list containing common scientific titles.
     */
    private static List<String> initialTitlesList() {
        return new ArrayList<>() {{
            add("Dr\\.\\s*rer\\.\\s*nat\\.");
            add("Dr\\.\\s*h\\.\\s*c\\.\\s*mult\\.");
            add("Dr\\.\\s*habil\\.");
            add("Dr\\.\\s*med\\.");
            add("Dr\\.\\s*phil\\.");
            add("Dr\\.");
            add("Professorin");
            add("Professor");
            add("Prof\\.");
            add("Dr\\.\\s*-\\s*Ing\\.");
            add("Dipl\\.\\s*Ing\\.");
        }};
    }

    /**
     * Resets the titlelist to the initial values
     */
    public static void resetTitlesList() {
        titlesList = initialTitlesList();
    }
}

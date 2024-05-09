package model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The class NobleTitles defines a mutable list of nobility-titles.
 */
public class NobleTitles {

    /**
     * mutable list of nobility-titles.
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
     * initial list of nobility-titles.
     */
    private static List<String> initialTitlesList() {
        return new ArrayList<>() {{
            add("Baron\\s*von");
            add("Baronin\\s*von");
            add("Freiherr\\s*von");
            add("Freiherrin\\s*von");
            add("Freiherr\\s*vom");
            add("Freiherrin\\s*vom");
            add("Fürst\\s*von");
            add("Fürstin\\s*von");
            add("Fürst\\s*zu");
            add("Fürstin\\s*zu");
            add("Prinz\\s*von");
            add("Prinzessin\\s*von");
            add("Prinz\\s*zu");
            add("Prinzessin\\s*zu");
            add("von\\s*und\\s*zu");
            add("von");
            add("zu");
        }};
    }

    /**
     * Resets the titlelist to the initial values
     */
    public static void resetTitlesList() {
        titlesList = initialTitlesList();
    }
}

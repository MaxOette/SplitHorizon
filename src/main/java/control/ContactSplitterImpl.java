package control;

import model.Contact;
import model.Gender;
import model.NobleTitles;
import model.Titles;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * The class ContactSplitterImpl implements the ContactSplitter interface. Its provides functionality for splitting a
 * name into its individual parts.
 */
public class ContactSplitterImpl implements ContactSplitter {

    /**
     * Regex-pattern containing common scientific titles.
     */
    private Pattern titlePattern;

    /**
     * Regex-pattern containing common titles of nobility.
     */
    private Pattern nobiliaryPattern;

    /**
     * Regex-pattern containing the usual german salutation strings.
     */
    private final Pattern salutationPattern;

    /**
     * Constructor initializes patterns for recognizing salutations.
     */
    public ContactSplitterImpl() {
        salutationPattern = Pattern.compile("Herr|Frau|Hr\\.|Fr\\.");
        initPatterns();
    }

    /**
     * Method initializes patterns for recognizing titles, and noble titles.
     */
    private void initPatterns() {
        titlePattern = compilePatternFromList(Titles.titlesList);
        nobiliaryPattern = compilePatternFromList(NobleTitles.titlesList);
    }

    private Pattern compilePatternFromList(java.util.List<String> titlesList) {
        titlesList.sort(Collections.reverseOrder());
        List<String> annotatedTitlesList = titlesList.stream().map(x->"\\" + "s+" + x + "\\" + "s+").toList();
        return Pattern.compile(String.join("|", annotatedTitlesList));
    }

    /**
     * Splits the input into the individual parts of a name and saves them into a Contact object.
     *
     * @param input - string containing the full name input.
     * @return Contact - object for storing information about the individual parts of a name.
     */
    @Override
    public Contact parseContactString(String input) {
        initPatterns();
        Contact contact = new Contact();
        input = parseSalutation(input, contact);
        input = parseTitles(input, contact);
        input = parseNobleTitles(input, contact);
        parseName(input, contact);

        return contact;
    }

    /**
     * Parses the salutation from the input string and sets the corresponding gender in the contact.
     *
     * @param input   The input string containing the name.
     * @param contact The Contact object to populate.
     * @return The remaining part of the input string after removing the salutation.
     */
    String parseSalutation(String input, Contact contact) {
        Matcher matcher = salutationPattern.matcher(input);
        if (matcher.find()) {
            String salutation = matcher.group();
            contact.setSalutation(salutation);
            contact.setGender(determineGender(salutation));
            String subString = input.substring(matcher.start(), matcher.end()).trim();
            input = input.replace(subString, "");
        }
        return input.trim().replaceAll("\\s+", " ");
    }

    private Gender determineGender(String salutation) {
        return "Herr".equals(salutation) || "Hr.".equals(salutation) ? Gender.M : Gender.F;
    }

    /**
     * Parses academic or professional titles from the input string.
     *
     * @param input   The input string containing the name and titles.
     * @param contact The Contact object to populate.
     * @return The remaining part of the input string after extracting titles.
     */
    String parseTitles(String input, Contact contact) {
        Matcher matcher;
        for (int i = 0; i < 2; i++) {
            input = " " + input + " ";
            matcher = titlePattern.matcher(input);
            if (matcher.find()) {
                String title = extractTitle(matcher.group().split(" "));
                if (i == 0) contact.setTitle1(title);
                if (i == 1) contact.setTitle2(title);
                input = matcher.replaceFirst(" ");
            }
        }
        return input.trim().replaceAll("\\s+", " ");
    }

    private String extractTitle(String[] titleParts) {
        return Arrays.stream(titleParts).filter(x -> !x.isEmpty()).map(String::trim).collect(Collectors.joining(" "));

    }

    /**
     * Parses noble titles from the input string.
     *
     * @param input   The input string containing the name and noble titles.
     * @param contact The Contact object to populate.
     * @return The remaining part of the input string after extracting noble titles.
     */
    String parseNobleTitles(String input, Contact contact) {
        input = " " + input + " ";
        Matcher matcher = nobiliaryPattern.matcher(input);
        if (matcher.find()) {
            String nobleTitle = extractTitle(matcher.group().split(" "));
            contact.setNobleTitle(nobleTitle);
            String subString = input.substring(matcher.start(), matcher.end()).trim();

            input = adjustInputPostNobleTitle(input, subString);
        }
        return input;
    }

    private String adjustInputPostNobleTitle(String input, String inputPostNobleTitle) {
        if (input.contains(",")) {
            input = input.replace(inputPostNobleTitle, "");
            input = input.replaceAll("\\s*,\\s*", ",");
        } else {
            String[] nameParts = input.split(inputPostNobleTitle);
            input = nameParts[1].trim();
            if (!nameParts[0].trim().isEmpty()) {
                input = input + "," + nameParts[0].trim();
            }
        }
        return input.trim().replaceAll("\\s+", " ");
    }

    /**
     * Parses the input string to extract first name, second name, and last name, updating the Contact object.
     *
     * @param input   The string from which the name parts are to be extracted.
     * @param contact The Contact object to populate.
     */
    void parseName(String input, Contact contact) {
        input = input.trim();
        if (input.contains(",")) {
            parseCommaSeparatedName(input, contact);
        } else {
            parseUnseparatedName(input, contact);
        }
    }

    private void parseCommaSeparatedName(String input, Contact contact) {
        String[] nameParts = input.split(",");
        String[] lastNames = nameParts[0].trim().split("\\s+");
        if (lastNames.length == 1) {
            contact.setLastName(lastNames[0]);
        } else {
            contact.setLastName(String.join("-", Arrays.copyOfRange(nameParts[0].split("\\s+"), 0, nameParts.length)));
        }

        String[] firstNames = nameParts[1].trim().split("\\s+");

        contact.setFirstName(firstNames[0]);
        if (firstNames.length > 1) {
            contact.setSecondName(firstNames[1]);
        }
    }

    private void parseUnseparatedName(String input, Contact contact) {
        String[] nameParts = input.split("\\s+");
        if (nameParts.length == 1) {
            contact.setLastName(nameParts[0]);
        }
        if (nameParts.length == 2) {
            contact.setFirstName(nameParts[0]);
            contact.setLastName(nameParts[nameParts.length - 1]);
        }
        if (nameParts.length == 3) {
            contact.setFirstName(nameParts[0]);
            contact.setSecondName(nameParts[1]);
            contact.setLastName(nameParts[2]);

        }
        if (nameParts.length > 3) {
            contact.setFirstName(nameParts[0]);
            contact.setSecondName(nameParts[1]);
            contact.setLastName(String.join("-", Arrays.copyOfRange(nameParts, 2, nameParts.length)));
        }
    }
}
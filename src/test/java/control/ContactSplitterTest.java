package control;

import model.Contact;
import model.Gender;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Class for testing functionality of ContactSplitter.
 */
class ContactSplitterTest {


    /**
     * Provides test cases for testing the parseSalutation method.
     * Each case includes an input string and the expected results for salutation, gender, and the remainder of the string after the salutation has been parsed.
     *
     * @return Stream of arguments including test input and expected results.
     */
    private static Stream<Arguments> provideSalutationTestCases() {
        return Stream.of(
                Arguments.of("", "", Gender.X, ""),
                Arguments.of("Herr Max Mustermann", "Herr", Gender.M, "Max Mustermann"),
                Arguments.of("Frau Erika Musterfrau", "Frau", Gender.F, "Erika Musterfrau"),
                Arguments.of("Max Mustermann", "", Gender.X, "Max Mustermann")
        );
    }

    /**
     * Tests the parseSalutation method of ContactSplitterImpl to ensure it correctly identifies and parses the salutation part of the input string and determines the appropriate gender.
     *
     * @param input The full name input string.
     * @param expectedSalutation The expected salutation extracted from the input.
     * @param expectedGender The expected gender corresponding to the salutation.
     * @param expectedRestOfString The remainder of the input string after the salutation has been removed.
     */
    @ParameterizedTest
    @MethodSource("provideSalutationTestCases")
    void testParseSalutation(String input, String expectedSalutation, Gender expectedGender, String expectedRestOfString) {
        ContactSplitterImpl splitter = new ContactSplitterImpl();
        Contact contact = new Contact();
        String restOfString = splitter.parseSalutation(input, contact);

        assertEquals(expectedSalutation, contact.getSalutation());
        assertEquals(expectedGender, contact.getGender());
        assertEquals(expectedRestOfString, restOfString);
    }

    /**
     * Provides test cases for testing the parsing of academic or professional titles from the full name string.
     *
     * @return Stream of arguments including test input and expected results.
     */
    private static Stream<Arguments> provideTitleTestCases() {
        return Stream.of(
                Arguments.of("Max Mustermann", "", "", "Max Mustermann"),
                Arguments.of("Dr. Max Mustermann", "Dr.", "", "Max Mustermann"),
                Arguments.of("Prof. Dr. Max Mustermann", "Prof.", "Dr.", "Max Mustermann"),
                Arguments.of("Max Mustermann Prof. Dr.", "Prof.", "Dr.", "Max Mustermann"),
                Arguments.of("Prof. Dr. rer. nat. Max Mustermann", "Prof.", "Dr. rer. nat.", "Max Mustermann"),
                Arguments.of("Max Prof. Dr. Mustermann", "Prof.", "Dr.", "Max Mustermann"),
                Arguments.of("Dr. rer. nat. Max Prof. Dr. Mustermann", "Dr. rer. nat.", "Prof.", "Max Dr. Mustermann"),
                Arguments.of("Max Mustermann Dr.", "Dr.", "", "Max Mustermann")
        );
    }

    /**
     * Tests the parsing of titles within the ContactSplitterImpl to ensure that titles are correctly identified and extracted.
     *
     * @param input The full name input string.
     * @param expectedTitle1 The first title expected to be extracted, if any.
     * @param expectedTitle2 The second title expected to be extracted, if any.
     * @param expectedRestOfString The remainder of the input string after titles have been removed.
     */
    @ParameterizedTest
    @MethodSource("provideTitleTestCases")
    void testParseTitles(String input, String expectedTitle1, String expectedTitle2, String expectedRestOfString) {
        ContactSplitterImpl splitter = new ContactSplitterImpl();
        Contact contact = new Contact();
        String restOfString = splitter.parseTitles(input, contact);

        assertEquals(expectedTitle1, contact.getTitle1());
        assertEquals(expectedTitle2, contact.getTitle2());
        assertEquals(expectedRestOfString, restOfString);
    }

    /**
     * Provides test cases for testing the parsing of noble titles from the full name string.
     *
     * @return Stream of arguments including test input and expected results.
     */
    private static Stream<Arguments> provideNobleTitleTestCases() {
        return Stream.of(
                Arguments.of("Max Mustermann", "", "Max Mustermann"),
                Arguments.of("Freiherr Max von Mustermann", "von", "Mustermann,Freiherr Max"),
                Arguments.of("Max Freiherr von Mustermann", "Freiherr von", "Mustermann,Max"),
                Arguments.of("Max Erika Freiherr von Mustermann Musterfrau", "Freiherr von", "Mustermann Musterfrau,Max Erika"),
                Arguments.of("Freiherr von Mustermann, Max", "Freiherr von", "Mustermann,Max"),
                Arguments.of(" Freiherr  von   Mustermann    Musterfrau    ,     Max    ", "Freiherr von", "Mustermann Musterfrau,Max")
        );
    }

    /**
     * Tests the parseNobleTitles method of ContactSplitterImpl to ensure that noble titles are correctly identified and parsed from the input string.
     *
     * @param input The full name input string.
     * @param expectedNobleTitle The noble title expected to be extracted, if any.
     * @param expectedRestOfString The remainder of the input string after the noble title has been removed.
     */
    @ParameterizedTest
    @MethodSource("provideNobleTitleTestCases")
    void testParseNobleTitles(String input, String expectedNobleTitle, String expectedRestOfString) {
        ContactSplitterImpl splitter = new ContactSplitterImpl();
        Contact contact = new Contact();
        String restOfString = splitter.parseNobleTitles(input, contact);

        assertEquals(expectedNobleTitle, contact.getNobleTitle());
        assertEquals(expectedRestOfString, restOfString.trim());
    }

    /**
     * Provides test cases for testing the parsing of names into first name, second name, and last name components.
     *
     * @return Stream of arguments including test input and expected results for the first name, second name, and last name.
     */
    private static Stream<Arguments> provideNameTestCases() {
        return Stream.of(
                Arguments.of("Max", "", "", "Max"),
                Arguments.of("Max Mustermann", "Max", "", "Mustermann"),
                Arguments.of("Max J. Mustermann", "Max", "J.", "Mustermann"),
                Arguments.of("Max Johann Mustermann", "Max", "Johann", "Mustermann"),
                Arguments.of("Max Johann Mustermann Mf.", "Max", "Johann", "Mustermann-Mf."),
                Arguments.of("Max-Erika Johann-Dieter Mustermann", "Max-Erika", "Johann-Dieter", "Mustermann"),
                Arguments.of("Mustermann, Max", "Max", "", "Mustermann"),
                Arguments.of("Mustermann, Max Erika", "Max", "Erika", "Mustermann"),
                Arguments.of("Mustermann, Max Erika-Johann", "Max", "Erika-Johann", "Mustermann"),
                Arguments.of("Mustermann, Max Erika Johann", "Max", "Erika", "Mustermann"),
                Arguments.of("Mustermann, Max-Erika Johann", "Max-Erika", "Johann", "Mustermann")
        );
    }

    /**
     * Tests the parseName method of ContactSplitterImpl to ensure that first names, second names, and last names are correctly parsed and assigned within the Contact object.
     *
     * @param input The full name input string.
     * @param expectedFirstName The expected first name extracted from the input.
     * @param expectedSecondName The expected second name extracted from the input.
     * @param expectedLastName The expected last name extracted from the input.
     */
    @ParameterizedTest
    @MethodSource("provideNameTestCases")
    void testParseName(String input, String expectedFirstName, String expectedSecondName, String expectedLastName) {
        ContactSplitterImpl splitter = new ContactSplitterImpl();
        Contact contact = new Contact();
        splitter.parseName(input, contact);

        assertEquals(expectedFirstName, contact.getFirstName());
        assertEquals(expectedSecondName, contact.getSecondName());
        assertEquals(expectedLastName, contact.getLastName());
    }

    /**
     * Provides comprehensive test cases that integrate all parsing functionalities: salutations, titles, noble titles, and names.
     *
     * @return Stream of Arguments including full name input and expected outputs for each part of the name.
     */
    private static Stream<Arguments> provideCompleteTestCases() {
        return Stream.of(
                Arguments.of("Mustermann", "", Gender.X, "", "", "", "", "", "Mustermann"),
                Arguments.of("Max Mustermann", "", Gender.X, "", "", "Max", "", "", "Mustermann"),
                Arguments.of("Herr Max Mustermann", "Herr", Gender.M, "", "", "Max", "", "", "Mustermann"),
                Arguments.of("Herr Mustermann", "Herr", Gender.M, "", "", "", "", "", "Mustermann"),
                Arguments.of("Herr Mustermann, Max", "Herr", Gender.M, "", "", "Max", "", "", "Mustermann"),
                Arguments.of("Herr Dr. Max Mustermann", "Herr", Gender.M, "Dr.", "", "Max", "", "", "Mustermann"),
                Arguments.of("Herr Dr. Mustermann", "Herr", Gender.M, "Dr.", "", "", "", "", "Mustermann"),
                Arguments.of("Herr Dr. Dr. Mustermann", "Herr", Gender.M, "Dr.", "Dr.", "", "", "", "Mustermann"),
                Arguments.of("Herr Prof. Dr. rer. nat. Mustermann", "Herr", Gender.M, "Prof.", "Dr. rer. nat.", "", "", "", "Mustermann"),
                Arguments.of("Herr Prof. Dr. rer. nat. Max Mustermann", "Herr", Gender.M, "Prof.", "Dr. rer. nat.", "Max", "", "", "Mustermann"),
                Arguments.of("Herr Prof. Dr. rer. nat. Max Mustermann-Musterfrau", "Herr", Gender.M, "Prof.", "Dr. rer. nat.", "Max", "", "", "Mustermann-Musterfrau"),
                Arguments.of("Herr Prof. Dr. rer. nat. Max von Mustermann-Musterfrau", "Herr", Gender.M, "Prof.", "Dr. rer. nat.", "Max", "", "von", "Mustermann-Musterfrau"),
                Arguments.of("Herr Prof. Dr. rer. nat. Max Erika Mustermann Musterfrau", "Herr", Gender.M, "Prof.", "Dr. rer. nat.", "Max", "Erika", "", "Mustermann-Musterfrau"),
                Arguments.of("Herr Prof. Dr. rer. nat. Max Erika Freiherr von Mustermann Musterfrau", "Herr", Gender.M, "Prof.", "Dr. rer. nat.", "Max", "Erika", "Freiherr von", "Mustermann-Musterfrau"),
                Arguments.of("Herr Max Erika Mustermann", "Herr", Gender.M, "", "", "Max", "Erika", "", "Mustermann"),
                Arguments.of("Herr Max Erika Mustermann Musterfrau", "Herr", Gender.M, "", "", "Max", "Erika", "", "Mustermann-Musterfrau"),
                Arguments.of("Prof. Max Freiherr von Mustermann", "", Gender.X, "Prof.", "", "Max", "", "Freiherr von", "Mustermann"),
                Arguments.of("Prof. Max Freiherr von Mustermann-Musterfrau", "", Gender.X, "Prof.", "", "Max", "", "Freiherr von", "Mustermann-Musterfrau"),
                Arguments.of("Prof. Max Freiherr von Mustermann Musterfrau", "", Gender.X, "Prof.", "", "Max", "", "Freiherr von", "Mustermann-Musterfrau"),
                Arguments.of("Freiherr von Mustermann Musterfrau, Prof. Max", "", Gender.X, "Prof.", "", "Max", "", "Freiherr von", "Mustermann-Musterfrau"),
                Arguments.of("Freiherr von Mustermann", "", Gender.X, "", "", "", "", "Freiherr von", "Mustermann"),
                Arguments.of("Prof. Dr. Freiherr von Mustermann", "", Gender.X, "Prof.", "Dr.", "", "", "Freiherr von", "Mustermann"),
                Arguments.of("Mustermann, Max", "", Gender.X, "", "", "Max", "", "", "Mustermann"),
                Arguments.of("Mustermann, Max Erika", "", Gender.X, "", "", "Max", "Erika", "", "Mustermann"),
                Arguments.of("Mustermann, Prof. Dr. rer. nat. Max", "", Gender.X, "Prof.", "Dr. rer. nat.", "Max", "", "", "Mustermann"),
                Arguments.of("Freiherr von Mustermann, Prof. Dr. rer. nat. Max", "", Gender.X, "Prof.", "Dr. rer. nat.", "Max", "", "Freiherr von", "Mustermann"),
                Arguments.of(" Freiherr  von   Mustermann    Musterfrau    ,    Prof.     Max    ", "", Gender.X, "Prof.", "", "Max", "", "Freiherr von", "Mustermann-Musterfrau"),
                Arguments.of("      Herr     Prof.       Dr.     rer.      nat.       Max       Erika      Freiherr      von      Mustermann       Musterfrau      ", "Herr", Gender.M, "Prof.", "Dr. rer. nat.", "Max", "Erika", "Freiherr von", "Mustermann-Musterfrau"),
                Arguments.of("Hr. Mustermann", "Hr.", Gender.M, "", "", "", "", "", "Mustermann"),
                Arguments.of("Fr. Erika Musterfrau", "Fr.", Gender.F, "", "", "Erika", "", "", "Musterfrau"),
                Arguments.of("Hr. Prof. Dr. rer. nat. Max Erika von Mustermann Musterfrau", "Hr.", Gender.M, "Prof.", "Dr. rer. nat.", "Max", "Erika", "von", "Mustermann-Musterfrau"),
                Arguments.of("Hr. Dr. Mustermann, Max Erika", "Hr.", Gender.M, "Dr.", "", "Max", "Erika", "", "Mustermann")
                );
    }

    /**
     * Tests the complete parsing functionality of ContactSplitterImpl by verifying that all components of the name (salutation, titles, noble titles, and names) are parsed correctly and the Contact object is appropriately populated.
     *
     * @param input The full name input string.
     * @param expectedSalutation The expected salutation.
     * @param expectedGender The gender determined from the salutation.
     * @param expectedTitle1 The first title, if present.
     * @param expectedTitle2 The second title, if present.
     * @param expectedFirstName The first name.
     * @param expectedSecondName The second name, if present.
     * @param expectedNobleTitle The noble title, if present.
     * @param expectedLastName The last name.
     */
    @ParameterizedTest
    @MethodSource("provideCompleteTestCases")
    void testParseContactString(
            String input,
            String expectedSalutation,
            Gender expectedGender,
            String expectedTitle1,
            String expectedTitle2,
            String expectedFirstName,
            String expectedSecondName,
            String expectedNobleTitle,
            String expectedLastName) {
        ContactSplitterImpl splitter = new ContactSplitterImpl();
        Contact result = splitter.parseContactString(input);

        assertEquals(expectedSalutation, result.getSalutation());
        assertEquals(expectedGender, result.getGender());
        assertEquals(expectedTitle1, result.getTitle1());
        assertEquals(expectedTitle2, result.getTitle2());
        assertEquals(expectedFirstName, result.getFirstName());
        assertEquals(expectedSecondName, result.getSecondName());
        assertEquals(expectedNobleTitle, result.getNobleTitle());
        assertEquals(expectedLastName, result.getLastName());
    }

}

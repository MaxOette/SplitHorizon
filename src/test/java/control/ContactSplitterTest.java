package control;

import model.Contact;
import model.Gender;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ContactSplitterTest {

    private static Stream<Arguments> provideTestCases() {
        return Stream.of(
                Arguments.of("Mustermann", "", Gender.X, "", "", "", "", "", "Mustermann"),
                Arguments.of("Max Mustermann", "", Gender.X, "", "", "Max", "", "", "Mustermann"),
                Arguments.of("Herr Max Mustermann", "Herr", Gender.M, "", "", "Max", "", "", "Mustermann"),
                Arguments.of("Herr Mustermann", "Herr", Gender.M, "", "", "", "", "", "Mustermann"),
                Arguments.of("Herr Mustermann, Max", "Herr", Gender.M, "", "", "Max", "", "", "Mustermann"),
                Arguments.of("Herr Dr. Max Mustermann", "Herr", Gender.M, "Dr.", "", "Max", "", "", "Mustermann"),
                Arguments.of("Herr Dr. Mustermann", "Herr", Gender.M, "Dr.", "", "", "", "", "Mustermann"),
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
                Arguments.of("      Herr     Prof.       Dr.     rer.      nat.       Max       Erika      Freiherr      von      Mustermann       Musterfrau      ", "Herr", Gender.M, "Prof.", "Dr. rer. nat.", "Max", "Erika", "Freiherr von", "Mustermann-Musterfrau")
                );
    }

    @ParameterizedTest
    @MethodSource("provideTestCases")
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

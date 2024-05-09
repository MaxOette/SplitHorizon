package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Class for testing functionality of NobleTitles List.
 */
public class NobleTitlesTest {

    /**
     * Add titles before each test.
     */
    @BeforeEach
    void setUp() {
        NobleTitles.titlesList.clear();
        NobleTitles.titlesList.add("von");
        NobleTitles.titlesList.add("zu");
    }

    /**
     * Add title and check that it was added correctly.
     */
    @Test
    void testAddTitleNotPresent() {
        NobleTitles.addTitle("Baron von");
        assertTrue(NobleTitles.titlesList.contains("Baron\\s*von"));
        assertEquals(3, NobleTitles.titlesList.size());
    }

    /**
     * Add already existing title and check that it was not added.
     */
    @Test
    void testAddTitleAlreadyPresent() {
        NobleTitles.addTitle("von");
        assertEquals(2, NobleTitles.titlesList.size());
    }

    /**
     * Add empty title and check that it was not added.
     */
    @Test
    void testAddEmptyTitle() {
        NobleTitles.addTitle("");
        assertEquals(2, NobleTitles.titlesList.size());
    }

}

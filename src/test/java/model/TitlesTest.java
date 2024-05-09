package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Class for testing functionality of Titles List.
 */
public class TitlesTest {

    /**
     * Add titles before each test.
     */
    @BeforeEach
    void setUp() {
        Titles.titlesList.clear();
        Titles.titlesList.add("Dr\\.");
        Titles.titlesList.add("Prof\\.");
    }

    @AfterEach
    void clearUp() {
        Titles.titlesList.clear();
        Titles.titlesList.add("Dr\\.");
        Titles.titlesList.add("Prof\\.");
    }

    /**
     * Add title and check that it was added correctly.
     */
    @Test
    void testAddTitleNotPresent() {
        Titles.addTitle("Dr. med.");
        Titles.addTitle("Dr. med.");
        assertTrue(Titles.titlesList.contains("Dr\\.\\s*med\\."));
        assertEquals(3, Titles.titlesList.size());
    }

    /**
     * Add already existing title and check that it was not added.
     */
    @Test
    void testAddTitleAlreadyPresent() {
        Titles.addTitle("Dr.");
        assertEquals(2, Titles.titlesList.size());
    }

    /**
     * Add empty title and check that it was not added.
     */
    @Test
    void testAddEmptyTitle() {
        Titles.addTitle("");
        assertEquals(2, Titles.titlesList.size());
    }
}

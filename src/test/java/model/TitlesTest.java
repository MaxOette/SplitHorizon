package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TitlesTest {

    @BeforeEach
    void setUp() {
        Titles.titlesList.clear();
        Titles.titlesList.add("Dr\\.");
        Titles.titlesList.add("Prof\\.");
    }

    @Test
    void testAddTitleNotPresent() {
        Titles.addTitle("Dr. med.");
        Titles.addTitle("Dr. med.");
        assertTrue(Titles.titlesList.contains("Dr\\.\\s*med\\."));
        assertEquals(3, Titles.titlesList.size());
    }

    @Test
    void testAddTitleAlreadyPresent() {
        Titles.addTitle("Dr.");
        assertEquals(2, Titles.titlesList.size());  // No duplicate should be added
    }

    @Test
    void testAddEmptyTitle() {
        Titles.addTitle("");
        assertEquals(2, Titles.titlesList.size());  // No empty title should be added
    }
}

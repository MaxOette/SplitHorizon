package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NobleTitlesTest {


    @BeforeEach
    void setUp() {
        NobleTitles.titlesList.clear();
        NobleTitles.titlesList.add("von");
        NobleTitles.titlesList.add("zu");
    }

    @Test
    void testAddTitleNotPresent() {
        NobleTitles.addTitle("Baron von");
        assertTrue(NobleTitles.titlesList.contains("Baron\\s*von"));
        assertEquals(3, NobleTitles.titlesList.size());
    }

    @Test
    void testAddTitleAlreadyPresent() {
        NobleTitles.addTitle("von");
        assertEquals(2, NobleTitles.titlesList.size());  // No duplicate should be added
    }

    @Test
    void testAddEmptyTitle() {
        NobleTitles.addTitle("");
        assertEquals(2, NobleTitles.titlesList.size());  // No empty title should be added
    }

}

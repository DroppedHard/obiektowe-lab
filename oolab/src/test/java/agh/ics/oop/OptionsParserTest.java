package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parseError() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            String[] options = {"abababab"};
            OptionsParser.parse(options);
        });
        String expectedMessage = "Podane parametry nie spełniają wymagań!";
        String actualMessage = exception.getMessage();
        assertEquals(expectedMessage, actualMessage);
    }
}
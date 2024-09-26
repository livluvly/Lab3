package org.translation;

import org.junit.Test;

import static org.junit.Assert.*;

public class JSONTranslationExampleTest {

    private JSONTranslationExample jsonTranslationExample = new JSONTranslationExample();

    @Test
    public void getCountryNameTranslation() {
        String expected = jsonTranslationExample.getCanadaCountryNameSpanishTranslation();
        String result = jsonTranslationExample.getCountryNameTranslation("can", "es");
        assertEquals("Translating 'can' to 'es' should be " + expected + " but was " + result, expected, result);
    }

    @Test
    public void getAfghanistanCountryNameTranslation() {
        String expected = "Afganistán";
        String result = jsonTranslationExample.getCountryNameTranslation("afg", "es");
        assertEquals("Translating 'afg' to 'es' should be " + expected + " but was " + result, expected, result);
    }
}

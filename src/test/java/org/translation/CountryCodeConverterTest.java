package org.translation;

import org.junit.Test;

import static org.junit.Assert.*;

public class CountryCodeConverterTest {

    @Test
    public void fromCountryCodeUSA() {
        CountryCodeConverter converter = new CountryCodeConverter();
        assertEquals("United States of America (the)", converter.fromCountryCode("USA"));
    }

    @Test
    public void fromCountryCodeAFG() {
        CountryCodeConverter converter = new CountryCodeConverter();
        assertEquals("Afghanistan", converter.fromCountryCode("AFG"));
    }


    @Test
    public void fromCountryCodeAllLoaded() {
        CountryCodeConverter converter = new CountryCodeConverter();
        assertEquals(249, converter.getNumCountries());
    }

    @Test
    public void fromCountryUSA() {
        CountryCodeConverter converter = new CountryCodeConverter();
        assertEquals("USA", converter.fromCountry("United States of America (the)"));
    }
}
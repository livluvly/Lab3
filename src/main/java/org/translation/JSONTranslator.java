package org.translation;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * An implementation of the Translator interface which reads in the translation
 * data from a JSON file. The data is read in once each time an instance of this class is constructed.
 */
public class JSONTranslator implements Translator {

    private final Map<String, List<String>> countryLanguages = new HashMap<>();
    private final Map<String, Map<String, String>> translations = new HashMap<>();
    private final Map<String, String> codeToName = new HashMap();

    /**
     * Constructs a JSONTranslator using data from the sample.json resources file.
     */
    public JSONTranslator() {
        this("sample.json");
    }

    /**
     * Constructs a JSONTranslator populated using data from the specified resources file.
     *
     * @param filename the name of the file in resources to load the data from
     * @throws RuntimeException if the resource file can't be loaded properly
     */
    public JSONTranslator(String filename) {
        // read the file to get the data to populate things...
        try {

            String jsonString = Files.readString(Paths.get(getClass().getClassLoader().getResource(filename).toURI()));

            JSONArray jsonArray = new JSONArray(jsonString);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject countryData = jsonArray.getJSONObject(i);
                String country = countryData.getString("alpha3");

                List<String> languages = new ArrayList<>();
                Map<String, String> countryTranslations = new HashMap<>();

                for (String lang: countryData.keySet()) {
                    if (!"id".equals(lang) && !"alpha2".equals(lang) && !"alpha3".equals(lang)) {
                        languages.add(lang);
                        countryTranslations.put(lang, countryData.getString(lang));
                    }
                    String countryName = countryData.getString("en");
                    codeToName.put(country, countryName);
                }
                countryLanguages.put(country, languages);
                translations.put(country, countryTranslations);
            }
        }
        catch (IOException | URISyntaxException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public List<String> getCountryLanguages(String country) {
        String lowercaseCountry = country.toLowerCase();
        Map<String, String> countryTranslations = translations.get(lowercaseCountry);
        List<String> countryLanguages = new ArrayList<>();
        if (countryTranslations != null) {
            for (String language: countryTranslations.keySet()) {
                countryLanguages.add(language);
            }
        }
        return countryLanguages;
    }

    @Override
    public List<String> getCountries() {
        return new ArrayList<>(countryLanguages.keySet());
    }

    @Override
    public String translate(String country, String language) {
        String lowercaseCountry = country.toLowerCase();
        Map<String, String> countryTranslation = translations.get(lowercaseCountry);
        if (countryTranslation != null) {
            return countryTranslation.get(language);
        }
        return null;
    }
}

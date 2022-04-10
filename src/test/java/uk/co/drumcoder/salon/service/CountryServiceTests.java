package uk.co.drumcoder.salon.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import uk.co.drumcoder.salon.service.country.CountryService;
import uk.co.drumcoder.salon.service.country.dao.CountryListDao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class CountryServiceTests {

    @Autowired
    private CountryService countryService;


    @Test
    void testFetchCountriesWorksSuccessfully() {
        // act
        CountryListDao countryList = this.countryService.fetchAllCountries();

        // assert
        assertTrue(countryList.contains("France"));
        assertTrue(countryList.contains("Germany"));
        assertTrue(countryList.contains("Italy"));
        assertFalse(countryList.contains("England"));
    }
}

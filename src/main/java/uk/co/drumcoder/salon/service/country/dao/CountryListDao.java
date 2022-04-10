package uk.co.drumcoder.salon.service.country.dao;

import lombok.Getter;
import uk.co.drumcoder.salon.service.award.dao.AwardDao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CountryListDao {

    private List<CountryDao> countries = new ArrayList<>();

    public void add(CountryDao country) {
        this.countries.add(country);
    }

    public boolean contains(String countryName) {
        for (CountryDao eachCountry : this.countries) {
            if (eachCountry.getName().equalsIgnoreCase(countryName)) {
                return true;
            }
        }
        return false;
    }

    public CountryDao findCountry(String countryName) {
        for (CountryDao eachCountry : this.countries) {
            if (eachCountry.getName().equalsIgnoreCase(countryName)) {
                return eachCountry;
            }
        }

        throw new UnsupportedOperationException("Country " + countryName + " not found");
    }
}

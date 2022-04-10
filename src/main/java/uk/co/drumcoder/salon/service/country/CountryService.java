package uk.co.drumcoder.salon.service.country;

import uk.co.drumcoder.salon.service.country.dao.CountryDao;
import uk.co.drumcoder.salon.service.country.dao.CountryListDao;

public interface CountryService {
    CountryListDao fetchAllCountries();
}

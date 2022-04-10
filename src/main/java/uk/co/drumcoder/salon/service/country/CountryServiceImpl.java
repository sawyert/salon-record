package uk.co.drumcoder.salon.service.country;

import lombok.RequiredArgsConstructor;

import org.jdom2.Document;
import org.jdom2.Element;
import org.springframework.stereotype.Service;
import uk.co.drumcoder.salon.framework.XmlHelper;
import uk.co.drumcoder.salon.service.country.dao.CountryDao;
import uk.co.drumcoder.salon.service.country.dao.CountryListDao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {

    @Override
    public CountryListDao fetchAllCountries(){
        CountryListDao countryListDao = new CountryListDao();

        Document document = XmlHelper.parse("data/Countries.xml");
        List<Element> children = document.getRootElement().getChildren();
        for (Element eachCountry: children) {
            CountryDao country = new CountryDao();
            country.setName(eachCountry.getText());
            countryListDao.add(country);
        }

        return countryListDao;
    }
}

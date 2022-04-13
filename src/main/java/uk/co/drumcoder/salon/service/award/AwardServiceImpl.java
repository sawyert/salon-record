package uk.co.drumcoder.salon.service.award;

import lombok.RequiredArgsConstructor;
import org.jdom2.Document;
import org.jdom2.Element;
import org.springframework.stereotype.Service;
import uk.co.drumcoder.salon.framework.XmlHelper;
import uk.co.drumcoder.salon.service.award.dao.OrganisationDao;
import uk.co.drumcoder.salon.service.award.dao.OrganisationListDao;
import uk.co.drumcoder.salon.service.award.dao.AwardDao;
import uk.co.drumcoder.salon.service.salon.SalonService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AwardServiceImpl implements AwardService {

    private SalonService salonService;

    @Override
    public OrganisationListDao listAllAwards() {
        OrganisationListDao returnAwardOrganisationList = new OrganisationListDao();

        Document document = XmlHelper.parse("data/Awards.xml");
        List<Element> children = document.getRootElement().getChildren();
        for (Element eachOrganisation: children) {
            OrganisationDao organisation = new OrganisationDao(eachOrganisation.getChildText("Name"));
            returnAwardOrganisationList.add(organisation);

            for (Element eachAward: eachOrganisation.getChild("Awards").getChildren()) {
                AwardDao award = new AwardDao();
                award.setName(eachAward.getChildText("Name"));
                award.setRequiredAcceptances(XmlHelper.getInt(eachAward, "Acceptances"));
                award.setRequiredCountries(XmlHelper.getInt(eachAward, "Countries"));
                award.setRequiredAwards(XmlHelper.getInt(eachAward, "Awards"));
                award.setRequiredImages(XmlHelper.getInt(eachAward, "Images"));
                award.setRequiredSalons(XmlHelper.getInt(eachAward, "Salons"));

                organisation.add(award);
            }
        }

        return returnAwardOrganisationList;
    }
}

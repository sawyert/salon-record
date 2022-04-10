package uk.co.drumcoder.salon.service.award;

import lombok.RequiredArgsConstructor;
import org.jdom2.Document;
import org.jdom2.Element;
import org.springframework.stereotype.Service;
import uk.co.drumcoder.salon.framework.XmlHelper;
import uk.co.drumcoder.salon.service.award.dao.AwardDao;
import uk.co.drumcoder.salon.service.award.dao.AwardListDao;
import uk.co.drumcoder.salon.service.award.dao.OrganisationDao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AwardServiceImpl implements AwardService {

    @Override
    public AwardListDao listAllAwards() {
        AwardListDao returnAwardList = new AwardListDao();

        Document document = XmlHelper.parse("data/Awards.xml");
        List<Element> children = document.getRootElement().getChildren();
        for (Element eachOrganisation: children) {
            OrganisationDao organisation = new OrganisationDao();
            organisation.setName(eachOrganisation.getChildText("Name"));

            for (Element eachAward: eachOrganisation.getChild("Awards").getChildren()) {
                AwardDao award = new AwardDao();
                award.setName(eachAward.getChildText("Name"));
                award.setAcceptances(XmlHelper.getLong(eachAward, "Acceptances"));
                award.setCountries(XmlHelper.getLong(eachAward, "Countries"));
                award.setAwards(XmlHelper.getLong(eachAward, "Awards"));
                award.setImages(XmlHelper.getLong(eachAward, "Images"));
                award.setSalons(XmlHelper.getLong(eachAward, "Salons"));
                award.setOrganisation(organisation);

                returnAwardList.add(award);
            }
        }

        return returnAwardList;
    }
}

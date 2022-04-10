package uk.co.drumcoder.salon.service.accreditations;

import lombok.RequiredArgsConstructor;
import org.jdom2.Element;
import org.springframework.stereotype.Service;
import uk.co.drumcoder.salon.service.accreditations.dao.AccreditationDao;
import uk.co.drumcoder.salon.service.accreditations.dao.AccreditationListDao;
import uk.co.drumcoder.salon.service.award.dao.OrganisationDao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AccreditationServiceImpl implements AccreditationService {

    @Override
    public AccreditationListDao processAccreditations(Element accreditationsElement) {
        AccreditationListDao accreditations = new AccreditationListDao();

        List<Element> numbersList = accreditationsElement.getChildren();
        for (Element accreditationElement : numbersList) {
            AccreditationDao accreditationDao = new AccreditationDao();
            accreditationDao.setOrganisation(new OrganisationDao(accreditationElement.getAttribute("type").getValue()));
            accreditationDao.setCode(accreditationElement.getText());

            accreditations.add(accreditationDao);
        }

        return accreditations;
    }
}

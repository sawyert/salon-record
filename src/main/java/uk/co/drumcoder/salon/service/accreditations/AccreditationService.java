package uk.co.drumcoder.salon.service.accreditations;

import org.jdom2.Element;
import uk.co.drumcoder.salon.service.accreditations.dao.AccreditationListDao;

public interface AccreditationService {
    AccreditationListDao processAccreditations(Element accreditations);
}

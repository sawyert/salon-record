package uk.co.drumcoder.salon.service.accreditations.dao;

import lombok.Getter;
import lombok.Setter;
import uk.co.drumcoder.salon.service.award.dao.OrganisationDao;

@Getter
@Setter
public class AccreditationDao {
    private OrganisationDao organisation;
    private String code;
}

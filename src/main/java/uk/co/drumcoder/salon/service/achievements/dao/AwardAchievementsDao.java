package uk.co.drumcoder.salon.service.achievements.dao;

import lombok.Getter;
import lombok.Setter;
import uk.co.drumcoder.salon.framework.AbstractDao;
import uk.co.drumcoder.salon.service.accreditations.dao.AccreditationDao;
import uk.co.drumcoder.salon.service.award.dao.OrganisationDao;
import uk.co.drumcoder.salon.service.award.dao.OrganisationListDao;
import uk.co.drumcoder.salon.service.salon.dao.SalonDao;

@Getter
@Setter
public class AwardAchievementsDao extends AbstractDao {
    private final OrganisationListDao organisationList ;

    public AwardAchievementsDao(OrganisationListDao organisationList) {
        super();
        this.organisationList = organisationList;
    }

    public OrganisationListDao getOrganisations() {
        return this.organisationList;
    }

    public OrganisationDao getOrganisation(int position) {
        return this.organisationList.getByPosition(position);
    }

    public OrganisationDao getOrganisation(String organisationName) {
        return this.organisationList.getByName(organisationName);
    }

    public void addSalonResults(SalonDao eachSalon) {
        for (AccreditationDao accreditation : eachSalon.getAccreditations().list()) {
            // do we have an organisation that matches this accreditation?
            for (OrganisationDao awardOrganisation : this.organisationList.list()) {
                if (accreditation.getOrganisation().getName().equals(awardOrganisation.getName())) {
                    awardOrganisation.addResults(eachSalon);
                }
            }
        }
    }
}

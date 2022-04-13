package uk.co.drumcoder.salon.service.award.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.co.drumcoder.salon.framework.AbstractDao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
public class OrganisationListDao extends AbstractDao {

    private List<OrganisationDao> awardOrganisations = new ArrayList<>();

    public int count() {
        return this.awardOrganisations.size();
    }

    public OrganisationDao getByPosition(int position) {
        return this.awardOrganisations.get(position);
    }

    public OrganisationDao getByName(String organisationName) {
        Optional<OrganisationDao> result = this.awardOrganisations.stream().filter(a -> a.getName().equals(organisationName)).findFirst();
        if (result.isEmpty()) {
            throw new UnsupportedOperationException("Organisation " + organisationName + " not found");
        }

        return result.get();
    }

    public List<OrganisationDao> list() {
        return this.awardOrganisations;
    }

    public void add(OrganisationDao organisation) {
        this.awardOrganisations.add(organisation);
    }
}

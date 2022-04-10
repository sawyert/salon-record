package uk.co.drumcoder.salon.service.accreditations.dao;

import antlr.NameSpace;

import java.util.ArrayList;
import java.util.List;

public class AccreditationListDao {
    private List<AccreditationDao> accreditations = new ArrayList<>();

    public void add(AccreditationDao accreditationDao) {
        this.accreditations.add(accreditationDao);
    }

    public int count() {
        return this.accreditations.size();
    }

    public AccreditationDao findByOrganisation(String organisationName) {
        for (AccreditationDao eachAccreditation: this.accreditations) {
            if (eachAccreditation.getOrganisation().getName().equals(organisationName)) {
                return eachAccreditation;
            }
        }

        throw new UnsupportedOperationException("Accreditation for " + organisationName + " not found");
    }
}

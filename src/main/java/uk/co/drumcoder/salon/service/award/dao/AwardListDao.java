package uk.co.drumcoder.salon.service.award.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AwardListDao {

    private List<AwardDao> awards = new ArrayList<>();

    public void add(AwardDao award) {
        this.awards.add(award);
    }

    public int getOrganisationCount() {
        Set<String> organisations = new HashSet<>();
        for (AwardDao eachAward: this.awards) {
            organisations.add(eachAward.getOrganisation().getName());
        }

        return organisations.size();
    }
}

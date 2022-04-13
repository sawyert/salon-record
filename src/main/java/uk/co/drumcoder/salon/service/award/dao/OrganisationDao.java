package uk.co.drumcoder.salon.service.award.dao;

import lombok.Getter;
import lombok.Setter;
import uk.co.drumcoder.salon.framework.AbstractDao;
import uk.co.drumcoder.salon.service.salon.dao.SalonDao;

@Getter
@Setter
public class OrganisationDao extends AbstractDao {

    private AwardListDao awardList = new AwardListDao();
    private final String name;

    public OrganisationDao(String name) {
        super();

        this.name = name;
    }

    public int awardCount() {
        return this.awardList.awardCount();
    }

    public AwardDao getAward(int position) {
        return this.awardList.getAwardByPosition(position);
    }

    public void add(AwardDao award) {
        this.awardList.add(award);
    }

    public void addResults(SalonDao salon) {
        for (AwardDao eachAward : this.awardList.notAchievedList()) {
            eachAward.addResults(salon);
        }
    }
}

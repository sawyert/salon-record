package uk.co.drumcoder.salon.service.award.dao;

import lombok.Getter;
import lombok.Setter;
import uk.co.drumcoder.salon.service.achievements.dao.SubmissionListItemDao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class AwardListDao {
    private List<AwardDao> awards = new ArrayList<>();

    public int awardCount() {
        return this.awards.size();
    }

    public AwardDao getAwardByPosition(int position) {
        return this.awards.get(position);
    }

    public void add(AwardDao award) {
        this.awards.add(award);
    }

    public List<AwardDao> notAchievedList() {
        return this.awards.stream().filter(a -> !a.isAchieved()).collect(Collectors.toList());
    }

    public List<SubmissionListItemDao> getSubmissionList(String organisationName, String awardName) {
        for (AwardDao award: this.awards) {
            if (award.getName().equals(awardName)) {
                return award.getSubmissionList(organisationName);
            }
        }
        throw new UnsupportedOperationException("Award " + awardName + " not found");
    }
}

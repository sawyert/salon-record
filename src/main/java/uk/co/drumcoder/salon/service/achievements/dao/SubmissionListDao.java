package uk.co.drumcoder.salon.service.achievements.dao;

import lombok.Getter;
import lombok.Setter;
import uk.co.drumcoder.salon.framework.AbstractDao;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class SubmissionListDao extends AbstractDao {
    private List<SubmissionListItemDao> submissionList = new ArrayList<>();

    public int count() {
        return this.submissionList.size();
    }

    public void populateFromAchievements(AwardAchievementsDao awardAchievements, String organisationName, String awardName) {
        List<SubmissionListItemDao> unorderedList = awardAchievements.getOrganisation(organisationName).getSubmissionList(organisationName, awardName);

        this.submissionList = unorderedList.stream().sorted(Comparator.comparing(SubmissionListItemDao::getTitle).thenComparing(SubmissionListItemDao::getSalonCode)).collect(Collectors.toList());

        int index = 0;
        String previousTitle = null;
        for (SubmissionListItemDao eachItem : submissionList) {
            if (!eachItem.getTitle().equalsIgnoreCase(previousTitle)) {
                index++;
            }
            previousTitle = eachItem.getTitle();
            eachItem.setTitleNumber(index);
        }
    }

    public SubmissionListItemDao get(int index) {
        return this.submissionList.get(index);
    }
}

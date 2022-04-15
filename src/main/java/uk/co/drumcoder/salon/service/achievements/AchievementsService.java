package uk.co.drumcoder.salon.service.achievements;

import uk.co.drumcoder.salon.service.achievements.dao.AwardAchievementsDao;
import uk.co.drumcoder.salon.service.achievements.dao.SubmissionListDao;

public interface AchievementsService {
    AwardAchievementsDao fetchAllAwardsStatus();

    SubmissionListDao fetchSubmissionList(String organisation, String award);
}

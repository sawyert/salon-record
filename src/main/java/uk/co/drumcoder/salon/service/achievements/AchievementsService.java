package uk.co.drumcoder.salon.service.achievements;

import uk.co.drumcoder.salon.service.achievements.dao.AwardAchievements;

public interface AchievementsService {
    AwardAchievements fetchAllAwardsStatus();
}

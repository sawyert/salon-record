package uk.co.drumcoder.salon.service.achievements;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uk.co.drumcoder.salon.service.achievements.dao.AwardAchievements;
import uk.co.drumcoder.salon.service.award.dao.OrganisationListDao;
import uk.co.drumcoder.salon.service.award.AwardService;
import uk.co.drumcoder.salon.service.salon.SalonService;
import uk.co.drumcoder.salon.service.salon.dao.SalonDao;
import uk.co.drumcoder.salon.service.salon.dao.SalonListDao;

@Service
@RequiredArgsConstructor
public class AchievementsServiceImpl implements AchievementsService {
    private final AwardService awardService;
    private final SalonService salonService;

    @Override
    public AwardAchievements fetchAllAwardsStatus() {
        OrganisationListDao allAwards = this.awardService.listAllAwards();
        SalonListDao salons = this.salonService.fetchAllSalons();

        AwardAchievements awardsWithStatus = this.populate(allAwards, salons);
        return awardsWithStatus;
    }

    private AwardAchievements populate(OrganisationListDao allAwards, SalonListDao salons) {
        // return structure - populate with awards
        AwardAchievements awardAchievements = new AwardAchievements(allAwards);

        // fetch all salon results
        SalonListDao salonList = this.salonService.fetchAllSalons();

        // populate structure with salon results
        for (SalonDao eachSalon : salonList.list()) {
            awardAchievements.addSalonResults(eachSalon);
         }

        return awardAchievements;
    }
}

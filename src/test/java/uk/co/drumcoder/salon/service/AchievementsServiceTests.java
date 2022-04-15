package uk.co.drumcoder.salon.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uk.co.drumcoder.salon.service.achievements.AchievementsService;
import uk.co.drumcoder.salon.service.achievements.dao.AwardAchievementsDao;
import uk.co.drumcoder.salon.service.achievements.dao.SubmissionListDao;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AchievementsServiceTests {

    @Autowired
    private AchievementsService achievementsService;

    @Test
    void testAwardAchievedFetchWorksSuccessfully() {
        // act
        AwardAchievementsDao awardAchievements = this.achievementsService.fetchAllAwardsStatus();

        // assert
        assertEquals(3, awardAchievements.getOrganisations().count());
        assertEquals("FIAP", awardAchievements.getOrganisation(0).getName());
        assertEquals("FIAP Levels", awardAchievements.getOrganisation(1).getName());
        assertEquals("BPE", awardAchievements.getOrganisation(2).getName());

        assertEquals(4, awardAchievements.getOrganisation("FIAP").awardCount());
        assertEquals("FIAP Test 1", awardAchievements.getOrganisation("FIAP").getAward(0).getName());
        assertEquals(2, awardAchievements.getOrganisation("FIAP").getAward(0).getRequiredAcceptances());
        assertEquals(1, awardAchievements.getOrganisation("FIAP").getAward(0).getRequiredCountries());
        assertEquals(0, awardAchievements.getOrganisation("FIAP").getAward(0).getRequiredAwards());
        assertEquals(1, awardAchievements.getOrganisation("FIAP").getAward(0).getRequiredImages());
        assertEquals(1, awardAchievements.getOrganisation("FIAP").getAward(0).getRequiredSalons());
        assertTrue(awardAchievements.getOrganisation("FIAP").getAward(0).isAchieved());
        assertEquals(new BigDecimal("36.07"), awardAchievements.getOrganisation("FIAP").getAward(0).getTotalCost());

        assertEquals("FIAP Test 2", awardAchievements.getOrganisation("FIAP").getAward(1).getName());
        assertEquals(3, awardAchievements.getOrganisation("FIAP").getAward(1).getRequiredAcceptances());
        assertEquals(1, awardAchievements.getOrganisation("FIAP").getAward(1).getRequiredCountries());
        assertEquals(1, awardAchievements.getOrganisation("FIAP").getAward(1).getRequiredAwards());
        assertEquals(3, awardAchievements.getOrganisation("FIAP").getAward(1).getRequiredImages());
        assertEquals(2, awardAchievements.getOrganisation("FIAP").getAward(1).getRequiredSalons());
        assertTrue(awardAchievements.getOrganisation("FIAP").getAward(1).isAchieved());
        assertEquals(new BigDecimal("45.07"), awardAchievements.getOrganisation("FIAP").getAward(1).getTotalCost());

        assertEquals("AFIAP", awardAchievements.getOrganisation("FIAP").getAward(2).getName());
        assertFalse(awardAchievements.getOrganisation("FIAP").getAward(2).isAchieved());
        assertEquals(new BigDecimal("85.13"), awardAchievements.getOrganisation("FIAP").getAward(2).getTotalCost());

        assertEquals("EFIAP", awardAchievements.getOrganisation("FIAP").getAward(3).getName());
        assertFalse(awardAchievements.getOrganisation("FIAP").getAward(3).isAchieved());
        assertEquals(new BigDecimal("85.13"), awardAchievements.getOrganisation("FIAP").getAward(3).getTotalCost());
;    }

    @Test
    void testFiapSubmissionListWorksSuccessfully() {
        // act
        SubmissionListDao submissionList = this.achievementsService.fetchSubmissionList("FIAP","FIAP Test 2");

        assertEquals(4, submissionList.count());

        assertEquals(1, submissionList.get(0).getTitleNumber());
        assertEquals("Daisy", submissionList.get(0).getTitle());
        assertEquals("Yorkshire", submissionList.get(0).getSalonName());
        assertEquals("England", submissionList.get(0).getCountry());
        assertEquals("2016/309", submissionList.get(0).getSalonCode());
        assertEquals("", submissionList.get(0).getAward());

        assertEquals(2, submissionList.get(1).getTitleNumber());
        assertEquals("Into The Mist", submissionList.get(1).getTitle());
        assertEquals("Yorkshire", submissionList.get(1).getSalonName());
        assertEquals("England", submissionList.get(1).getCountry());
        assertEquals("2016/309", submissionList.get(1).getSalonCode());
        assertEquals("", submissionList.get(1).getAward());

        assertEquals(2, submissionList.get(2).getTitleNumber());
        assertEquals("Into The Mist", submissionList.get(2).getTitle());
        assertEquals("Bohemia", submissionList.get(2).getSalonName());
        assertEquals("Czech Republic", submissionList.get(2).getCountry());
        assertEquals("2016/460", submissionList.get(2).getSalonCode());
        assertEquals("", submissionList.get(2).getAward());

        assertEquals(2, submissionList.get(3).getTitleNumber());
        assertEquals("Into The Mist", submissionList.get(3).getTitle());
        assertEquals("Photo Art Vision", submissionList.get(3).getSalonName());
        assertEquals("Czech Republic", submissionList.get(3).getCountry());
        assertEquals("2016/499", submissionList.get(3).getSalonCode());
        assertEquals("PSA HM", submissionList.get(3).getAward());
    }
}


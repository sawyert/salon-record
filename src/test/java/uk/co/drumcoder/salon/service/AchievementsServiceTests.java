package uk.co.drumcoder.salon.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uk.co.drumcoder.salon.service.achievements.AchievementsService;
import uk.co.drumcoder.salon.service.achievements.dao.AwardAchievements;

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
        AwardAchievements awardAchievements = this.achievementsService.fetchAllAwardsStatus();

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

        assertEquals("FIAP Test 2", awardAchievements.getOrganisation("FIAP").getAward(1).getName());
        assertEquals(2, awardAchievements.getOrganisation("FIAP").getAward(1).getRequiredAcceptances());
        assertEquals(1, awardAchievements.getOrganisation("FIAP").getAward(1).getRequiredCountries());
        assertEquals(1, awardAchievements.getOrganisation("FIAP").getAward(1).getRequiredAwards());
        assertEquals(3, awardAchievements.getOrganisation("FIAP").getAward(1).getRequiredImages());
        assertEquals(2, awardAchievements.getOrganisation("FIAP").getAward(1).getRequiredSalons());
        assertTrue(awardAchievements.getOrganisation("FIAP").getAward(1).isAchieved());

        assertEquals("AFIAP", awardAchievements.getOrganisation("FIAP").getAward(2).getName());
        assertFalse(awardAchievements.getOrganisation("FIAP").getAward(2).isAchieved());

        assertEquals("EFIAP", awardAchievements.getOrganisation("FIAP").getAward(3).getName());
        assertFalse(awardAchievements.getOrganisation("FIAP").getAward(3).isAchieved());
;    }
}

package uk.co.drumcoder.salon.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import uk.co.drumcoder.salon.service.award.AwardService;
import uk.co.drumcoder.salon.service.award.dao.AwardListDao;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class AwardServiceTests {

    @Autowired
    private AwardService awardService;

    @Test
    void testFetchAllAwardsWorksSuccessfully() {
        // act
        AwardListDao allAwards = this.awardService.listAllAwards();

        // assert
        assertEquals(3, allAwards.getOrganisationCount());
    }
}

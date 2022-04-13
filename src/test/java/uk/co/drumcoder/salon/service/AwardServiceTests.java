package uk.co.drumcoder.salon.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uk.co.drumcoder.salon.service.award.AwardService;
import uk.co.drumcoder.salon.service.award.dao.AwardListDao;
import uk.co.drumcoder.salon.service.award.dao.OrganisationListDao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class AwardServiceTests {

    @Autowired
    private AwardService awardService;

    @Test
    void testFetchAllAwardsWorksSuccessfully() {
        // act
        OrganisationListDao allAwardsOrganisations = this.awardService.listAllAwards();

        // assert
        assertEquals(3, allAwardsOrganisations.count());
    }
}

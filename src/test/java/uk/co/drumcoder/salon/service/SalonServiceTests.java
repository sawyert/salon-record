package uk.co.drumcoder.salon.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import uk.co.drumcoder.salon.service.salon.SalonService;
import uk.co.drumcoder.salon.service.salon.dao.SalonDao;
import uk.co.drumcoder.salon.service.salon.dao.SalonListDao;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SalonServiceTests {

    @Autowired
    private SalonService salonService;

    @Test
    void testFetchSalonsWorksSuccessfully() {
        // act
        SalonListDao allSalons = this.salonService.fetchAllSalons();

        assertEquals(7, allSalons.count());
        assertTrue(allSalons.containsName("Photo Art Vision"));
        assertTrue(allSalons.containsName("Belghoria Photo Lovers"));
        assertFalse(allSalons.containsName("Rubbish"));

        SalonDao awardedSalon = allSalons.getByNameAndYear("Photo Art Vision", "2016");
        assertEquals("Photo Art Vision", awardedSalon.getName());
        assertEquals("http://vision.photoart.cz/", awardedSalon.getWeb());
        assertEquals("Czech Republic", awardedSalon.getCountry().getName());
        assertEquals("2016", awardedSalon.getYear());
        assertEquals(LocalDate.of(2016, 12, 4), awardedSalon.getJudgeDate());
        assertEquals(LocalDate.of(2016, 12, 11), awardedSalon.getNotificationDate());
        assertEquals(2, awardedSalon.getAccreditations().count());
        assertEquals("PSA", awardedSalon.getAccreditations().findByOrganisation("PSA").getOrganisation().getName());
        assertEquals("2016-317", awardedSalon.getAccreditations().findByOrganisation("PSA").getCode());
        assertEquals("FIAP", awardedSalon.getAccreditations().findByOrganisation("FIAP").getOrganisation().getName());
        assertEquals("2016/499", awardedSalon.getAccreditations().findByOrganisation("FIAP").getCode());
        assertEquals(new BigDecimal("22.07"), awardedSalon.getCost());
        assertEquals(1, awardedSalon.getAcceptedImageCount());
        assertEquals(1, awardedSalon.getAwardedImageCount());
        assertEquals("Into The Mist", awardedSalon.getAcceptedImage(0).getTitle());
        assertEquals("PSA HM", awardedSalon.getAcceptedImage(0).getAward());
    }
}

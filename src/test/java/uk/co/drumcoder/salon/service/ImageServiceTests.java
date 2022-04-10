package uk.co.drumcoder.salon.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import uk.co.drumcoder.salon.service.country.CountryService;
import uk.co.drumcoder.salon.service.country.dao.CountryListDao;
import uk.co.drumcoder.salon.service.images.ImageService;
import uk.co.drumcoder.salon.service.images.dao.ImageListDao;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class ImageServiceTests {

    @Autowired
    private ImageService imageService;


    @Test
    void testFetchImagesWorksSuccessfully() {
        // act
        ImageListDao imageList = this.imageService.fetchAllImages();

        // assert
        assertTrue(imageList.contains("Kirkjufell Snow"));
        assertTrue(imageList.contains("Zombie Girl"));
        assertTrue(imageList.contains("Orbs"));
        assertFalse(imageList.contains("Not A Valid Name"));
    }
}

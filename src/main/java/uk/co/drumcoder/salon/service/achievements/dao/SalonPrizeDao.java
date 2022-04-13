package uk.co.drumcoder.salon.service.achievements.dao;

import lombok.Getter;
import uk.co.drumcoder.salon.service.images.dao.ImageDao;

@Getter
public class SalonPrizeDao {
    private final String imageTitle;
    private final String awardName;

    public SalonPrizeDao(ImageDao eachImage) {
        this.awardName = eachImage.getAward();
        this.imageTitle = eachImage.getTitle();
    }
}

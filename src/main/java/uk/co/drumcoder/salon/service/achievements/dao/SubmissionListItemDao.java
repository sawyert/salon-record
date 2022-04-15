package uk.co.drumcoder.salon.service.achievements.dao;

import lombok.Getter;
import lombok.Setter;
import uk.co.drumcoder.salon.service.images.dao.ImageDao;

@Getter
@Setter
public class SubmissionListItemDao {
    private int titleNumber;
    private String title;
    private String salonName;
    private String country;
    private String salonCode;
    private String award;

    public SubmissionListItemDao(ImageDao eachImage, String organisationName) {
        this.title = eachImage.getTitle();
        this.salonName = eachImage.getSalon().getName();
        this.country = eachImage.getSalon().getCountry().getName();
        this.salonCode = eachImage.getSalon().getAccreditationCode(organisationName);
        this.award = eachImage.getAward();
    }
}

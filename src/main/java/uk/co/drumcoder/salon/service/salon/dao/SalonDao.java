package uk.co.drumcoder.salon.service.salon.dao;

import antlr.NameSpace;
import lombok.Getter;
import lombok.Setter;
import uk.co.drumcoder.salon.service.accreditations.dao.AccreditationListDao;
import uk.co.drumcoder.salon.service.achievements.dao.SalonPrizeDao;
import uk.co.drumcoder.salon.service.country.dao.CountryDao;
import uk.co.drumcoder.salon.service.images.dao.ImageDao;
import uk.co.drumcoder.salon.service.images.dao.ImageListDao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class SalonDao {
    private String name;
    private String web;
    private CountryDao country;
    private String year;
    private LocalDate judgeDate;
    private LocalDate notificationDate;
    private AccreditationListDao accreditations;
    private BigDecimal cost;
    private ImageListDao acceptedImages;

    public void setCost(String costAsString) {
        this.cost = new BigDecimal(costAsString);
    }

    public void setJudgeDate(String judgeDateAsString) {
        this.judgeDate = LocalDate.parse(judgeDateAsString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public void setNotificationDate(String notificationDateAsString) {
        this.notificationDate = LocalDate.parse(notificationDateAsString, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public int getAcceptedImageCount() {
        return this.acceptedImages.count();
    }

    public int getAwardedImageCount() {
        return this.acceptedImages.awardCount();
    }

    public ImageDao getAcceptedImage(int position) {
        return this.acceptedImages.get(position);
    }

    public List<SalonPrizeDao> getAwardedImages() {
        return this.acceptedImages.getAwardedImages();

    }

    public String getAccreditationCode(String organisationName) {
        return this.accreditations.findByOrganisation(organisationName).getCode();
    }
}

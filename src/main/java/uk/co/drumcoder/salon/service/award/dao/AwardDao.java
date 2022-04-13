package uk.co.drumcoder.salon.service.award.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.co.drumcoder.salon.framework.AbstractDao;
import uk.co.drumcoder.salon.service.achievements.dao.SalonPrizeDao;
import uk.co.drumcoder.salon.service.country.dao.CountryDao;
import uk.co.drumcoder.salon.service.images.dao.ImageDao;
import uk.co.drumcoder.salon.service.salon.dao.SalonDao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class AwardDao extends AbstractDao {
    private String name;
    private int requiredAcceptances;
    private int requiredCountries;
    private int requiredAwards;
    private int requiredImages;
    private int requiredSalons;

    private int achievedAcceptances = 0;
    private Set<CountryDao> achievedCountries = new HashSet<>();
    private Set<SalonPrizeDao> achievedAwards = new HashSet<>();
    private Set<ImageDao> achievedImages = new HashSet<>();
    private int achievedSalons = 0;
    private BigDecimal totalCost = new BigDecimal("0");

    public boolean isAchieved() {
        boolean achieved = true;

        achieved = achieved && this.getAchievedAcceptances() >= this.requiredAcceptances;
        achieved = achieved && this.getAchievedCountries().size() >= this.requiredCountries;
        achieved = achieved && this.getAchievedAwards().size() >= this.requiredAwards;
        achieved = achieved && this.getAchievedImages().size() >= this.requiredImages;
        achieved = achieved && this.getAchievedSalons() >= this.requiredSalons;

        return achieved;
    }

    public void addResults(SalonDao salon) {
        this.achievedAcceptances += salon.getAcceptedImageCount();
        this.achievedCountries.add(salon.getCountry());
        this.achievedAwards.addAll(salon.getAwardedImages());
        this.achievedImages.addAll(salon.getAcceptedImages().list());
        this.achievedSalons += 1;
    }
}

package uk.co.drumcoder.salon.service.award.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.co.drumcoder.salon.framework.AbstractDao;
import uk.co.drumcoder.salon.service.achievements.dao.SalonPrizeDao;
import uk.co.drumcoder.salon.service.achievements.dao.SubmissionListItemDao;
import uk.co.drumcoder.salon.service.country.dao.CountryDao;
import uk.co.drumcoder.salon.service.images.dao.ImageDao;
import uk.co.drumcoder.salon.service.salon.dao.SalonDao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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
    private Map<String, ImageDao> achievedImages = new HashMap<>();
    private int achievedSalons = 0;
    private BigDecimal totalCost = new BigDecimal("0");

    public String getAcceptancesString() {
        return this.achievedAcceptances + " of " + this.requiredAcceptances;
    }

    public String getCountriesString() {
        return this.achievedCountries.size() + " of " + this.requiredCountries;
    }

    public String getAwardsString() {
        return this.achievedAwards.size() + " of " + this.requiredAwards;
    }

    public String getImagesString() {
        return this.achievedImages.size() + " of " + this.requiredImages;
    }

    public String getSalonsString() {
        return this.achievedSalons + " of " + this.requiredSalons;
    }

    public String getCostString() { return "£" + this.totalCost.toPlainString(); }

    public boolean isAchieved() {
        boolean achieved = true;

        achieved = achieved && this.getAchievedAcceptances() >= this.requiredAcceptances;
        achieved = achieved && this.getAchievedCountries().size() >= this.requiredCountries;
        achieved = achieved && this.getAchievedAwards().size() >= this.requiredAwards;
        achieved = achieved && this.getAchievedImages().size() >= this.requiredImages;
        achieved = achieved && this.getAchievedSalons() >= this.requiredSalons;

        System.out.println(this.name);
        for (String eachImage : this.getAchievedImages().keySet().stream().sorted().collect(Collectors.toList())) {
            System.out.println("  " + eachImage);
        }

        return achieved;
    }

    public void addResults(SalonDao salon) {
        this.achievedAcceptances += salon.getAcceptedImageCount();
        this.achievedCountries.add(salon.getCountry());
        this.achievedAwards.addAll(salon.getAwardedImages());
        for (ImageDao eachImage : salon.getAcceptedImages().list()) {
            this.achievedImages.put(eachImage.getTitle(), eachImage);
        }
        this.achievedSalons += 1;
        this.totalCost = this.totalCost.add(salon.getCost());
    }

    public List<SubmissionListItemDao> getSubmissionList(String organisationName) {
        List<SubmissionListItemDao> submissionList = new ArrayList<>();
        for (ImageDao eachImage : this.getAchievedImages().values()) {
            SubmissionListItemDao submissionItem = new SubmissionListItemDao(eachImage, organisationName);
            submissionList.add(submissionItem);
        }

        return submissionList;
    }
}

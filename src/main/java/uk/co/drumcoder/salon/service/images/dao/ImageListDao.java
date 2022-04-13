package uk.co.drumcoder.salon.service.images.dao;

import uk.co.drumcoder.salon.service.achievements.dao.SalonPrizeDao;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ImageListDao {

    private List<ImageDao> images = new ArrayList<>();

    public void add(ImageDao image) {
        this.images.add(image);
    }

    public boolean contains(String imageName) {
        for (ImageDao eachImage : this.images) {
            if (eachImage.getTitle().equalsIgnoreCase(imageName)) {
                return true;
            }
        }
        return false;
    }

    public int count() {
        return this.images.size();
    }

    public int awardCount() {
        int count = 0;
        for (ImageDao eachImage : this.images) {
            if (eachImage.getAward() != null) {
                count += 1;
            }
        }
        return count;
    }

    public ImageDao get(int position) {
        return this.images.get(position);
    }

    public Collection<? extends ImageDao> list() {
        return this.images;
    }

    public List<SalonPrizeDao> getAwardedImages() {
        List<SalonPrizeDao> allSalonPrizes = new ArrayList<>();
        for (ImageDao eachImage : this.images) {
            if (eachImage.getAward() != null) {
                allSalonPrizes.add(new SalonPrizeDao(eachImage));
            }
        }
        return allSalonPrizes;
    }
}

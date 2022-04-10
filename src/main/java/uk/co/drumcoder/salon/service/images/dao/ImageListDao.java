package uk.co.drumcoder.salon.service.images.dao;

import java.util.ArrayList;
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
}

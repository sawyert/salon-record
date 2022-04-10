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
            if (eachImage.getName().equalsIgnoreCase(imageName)) {
                return true;
            }
        }
        return false;
    }
}

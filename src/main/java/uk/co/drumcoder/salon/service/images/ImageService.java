package uk.co.drumcoder.salon.service.images;

import org.jdom2.Element;
import uk.co.drumcoder.salon.service.images.dao.ImageListDao;

public interface ImageService {
    ImageListDao fetchAllImages();

    ImageListDao processImagesForSalon(Element awardedImages);
}

package uk.co.drumcoder.salon.service.images;

import org.jdom2.Element;
import uk.co.drumcoder.salon.service.images.dao.ImageListDao;
import uk.co.drumcoder.salon.service.salon.dao.SalonDao;

public interface ImageService {
    ImageListDao fetchAllImages();

    ImageListDao processImagesForSalon(SalonDao salon, Element awardedImages);
}

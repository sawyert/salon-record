package uk.co.drumcoder.salon.service.images;

import uk.co.drumcoder.salon.service.images.dao.ImageListDao;

public interface ImageService {
    ImageListDao fetchAllImages();
}

package uk.co.drumcoder.salon.service.images;

import lombok.RequiredArgsConstructor;
import org.jdom2.Document;
import org.jdom2.Element;
import org.springframework.boot.context.properties.bind.UnboundConfigurationPropertiesException;
import org.springframework.stereotype.Service;
import uk.co.drumcoder.salon.framework.XmlHelper;
import uk.co.drumcoder.salon.service.country.dao.CountryDao;
import uk.co.drumcoder.salon.service.country.dao.CountryListDao;
import uk.co.drumcoder.salon.service.images.dao.ImageDao;
import uk.co.drumcoder.salon.service.images.dao.ImageListDao;
import uk.co.drumcoder.salon.service.salon.dao.SalonDao;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    @Override
    public ImageListDao fetchAllImages(){
        ImageListDao imageListDao = new ImageListDao();

        Document document = XmlHelper.parse("data/Images.xml");
        List<Element> children = document.getRootElement().getChildren();
        for (Element eachImage: children) {
            ImageDao image = new ImageDao();
            image.setTitle(eachImage.getText());
            image.setAward(null);
            imageListDao.add(image);
        }

        return imageListDao;
    }

    @Override
    public ImageListDao processImagesForSalon(SalonDao salon, Element acceptedImagesElement) {
        ImageListDao allValidImages = this.fetchAllImages();
        ImageListDao salonImages = new ImageListDao();

        for (Element imageElement : acceptedImagesElement.getChildren()) {
            ImageDao imageDao = new ImageDao();
            imageDao.setTitle(imageElement.getText());

            if (imageDao.getTitle() == null || imageDao.getTitle().trim().length() == 0) {
                throw new UnsupportedOperationException("Image name is blank");
            }

            if (!allValidImages.contains(imageDao.getTitle())) {
              throw new UnsupportedOperationException("Image " + imageDao.getTitle() + " not found in Images.xml");
            }

            if (imageElement.getAttribute("award") != null) {
                imageDao.setAward(imageElement.getAttribute("award").getValue());
            } else {
                imageDao.setAward("");
            }

            imageDao.setSalon(salon);
            salonImages.add(imageDao);
        }

        return salonImages;
    }
}

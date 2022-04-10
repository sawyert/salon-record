package uk.co.drumcoder.salon.service.images;

import lombok.RequiredArgsConstructor;
import org.jdom2.Document;
import org.jdom2.Element;
import org.springframework.stereotype.Service;
import uk.co.drumcoder.salon.framework.XmlHelper;
import uk.co.drumcoder.salon.service.country.dao.CountryDao;
import uk.co.drumcoder.salon.service.country.dao.CountryListDao;
import uk.co.drumcoder.salon.service.images.dao.ImageDao;
import uk.co.drumcoder.salon.service.images.dao.ImageListDao;

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
            image.setName(eachImage.getText());
            imageListDao.add(image);
        }

        return imageListDao;
    }
}

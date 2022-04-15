package uk.co.drumcoder.salon.service.salon;

import lombok.RequiredArgsConstructor;
import org.jdom2.Document;
import org.jdom2.Element;
import org.springframework.stereotype.Service;
import uk.co.drumcoder.salon.framework.XmlHelper;

import uk.co.drumcoder.salon.service.accreditations.AccreditationService;
import uk.co.drumcoder.salon.service.country.CountryService;
import uk.co.drumcoder.salon.service.images.ImageService;
import uk.co.drumcoder.salon.service.salon.dao.SalonDao;
import uk.co.drumcoder.salon.service.salon.dao.SalonListDao;

import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SalonServiceImpl implements SalonService {
    private final AccreditationService accreditationService;
    private final CountryService countryService;
    private final ImageService imageService;

    @Override
    public SalonListDao fetchAllSalons() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();

        SalonListDao salonList = new SalonListDao();

        List<String> yearFolders = this.fetchYearFolders();
        for (String year: yearFolders) {
            String salonPath = "data/Salons/" + year;
            System.out.println("Looking for files in " + salonPath);
            URL url = loader.getResource(salonPath);
            String path = url.getPath();
            File[] filesInYear = new File(path).listFiles();

            for (File file: filesInYear) {
                String fileToLoad = salonPath + "/" + file.getName();
                System.out.println("  loading " + fileToLoad);
                Document document = XmlHelper.parse(fileToLoad);
                Element root = document.getRootElement();
                if (root.getName().equalsIgnoreCase("circuit")) {
                    this.processCircuit(salonList, root, year);
                } else {
                    this.processSalon(salonList, root, year);
                }
            }
        }
        return salonList;
    }

    private List<String> fetchYearFolders() {
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        URL url = loader.getResource("data/Salons");
        String path = url.getPath();
        File[] files = new File(path).listFiles();

        List<String> years = new ArrayList<>();
        for (File file: files) {
            String year = file.getName();
            if (year.startsWith(".")) {
                continue;
            }
            years.add(year);
        }

        return years.stream().sorted().collect(Collectors.toList());
    }

    private void processCircuit(SalonListDao salonList, Element circuitElement, String year) {
        List<Element> salons = circuitElement.getChild("Salons").getChildren();
        BigDecimal totalCircuitCost = new BigDecimal(circuitElement.getChildText("Cost"));
        BigDecimal eachSalonCost = totalCircuitCost.divide(new BigDecimal("" + salons.size()), 2, RoundingMode.HALF_UP);
        for (Element salon: salons) {
            salon.addContent(new Element("Web").setText(circuitElement.getChild("Web").getText()));
            salon.addContent(new Element("Cost").setText(eachSalonCost.toPlainString()));
            salon.addContent(new Element("JudgeDate").setText(circuitElement.getChild("JudgeDate").getText()));
            salon.addContent(new Element("NotificationDate").setText(circuitElement.getChild("NotificationDate").getText()));
            Element salonNameElement = salon.getChild("Name");
            String newName = circuitElement.getChildText("Name") + " - " + salonNameElement.getText();
            salonNameElement.setText(newName);
            this.processSalon(salonList, salon, year);
        }
    }

    private void processSalon(SalonListDao salonList, Element salonElement, String year) {
        SalonDao salon = new SalonDao();
        salon.setName(salonElement.getChildText("Name"));
        salon.setWeb(salonElement.getChildText("Web"));
        salon.setCountry(this.countryService.findCountry(salonElement.getChildText("Country")));
        salon.setYear(year);
        salon.setJudgeDate(salonElement.getChildText("JudgeDate"));
        salon.setNotificationDate(salonElement.getChildText("NotificationDate"));
        if (salonElement.getChild("Cost") != null) {
            salon.setCost(salonElement.getChildText("Cost"));
        }

        salon.setAccreditations(this.accreditationService.processAccreditations(salonElement.getChild("Accreditations")));
        salon.setAcceptedImages(this.imageService.processImagesForSalon(salon, salonElement.getChild("AcceptedImages")));

        salonList.add(salon);
    }
}

package uk.co.drumcoder.salon.service.salon;

import uk.co.drumcoder.salon.service.salon.dao.SalonListDao;

public interface SalonService {

    SalonListDao fetchAllSalons();

}

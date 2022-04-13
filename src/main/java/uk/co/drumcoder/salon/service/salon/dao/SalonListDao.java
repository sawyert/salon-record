package uk.co.drumcoder.salon.service.salon.dao;

import java.util.ArrayList;
import java.util.List;

public class SalonListDao {
    List<SalonDao> salons = new ArrayList<>();

    public void add(SalonDao salon) {
        this.salons.add(salon);
    }

    public int count() {
        return this.salons.size();
    }

    public boolean containsName(String salonName) {
        for (SalonDao salon : this.salons) {
            if (salon.getName().equals(salonName)) {
                return true;
            }
        }
        return false;
    }

    public SalonDao getByNameAndYear(String salonName, String salonYear) {
        for (SalonDao salon : this.salons) {
            if (salon.getName().equals(salonName) && salon.getYear().equals(salonYear)) {
                return salon;
            }
        }
        throw new UnsupportedOperationException("Salon " + salonName + " not found");
    }

    public List<SalonDao> list() {
        return this.salons;
    }
}

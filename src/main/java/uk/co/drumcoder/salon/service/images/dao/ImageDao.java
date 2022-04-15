package uk.co.drumcoder.salon.service.images.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.co.drumcoder.salon.framework.AbstractDao;
import uk.co.drumcoder.salon.service.salon.dao.SalonDao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
public class ImageDao extends AbstractDao {
    private String title;
    private String award;
    private SalonDao salon;
}

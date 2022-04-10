package uk.co.drumcoder.salon.service.award.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.co.drumcoder.salon.framework.AbstractDao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
public class OrganisationDao extends AbstractDao {
    private String name;
}

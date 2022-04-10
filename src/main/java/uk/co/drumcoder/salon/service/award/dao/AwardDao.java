package uk.co.drumcoder.salon.service.award.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.co.drumcoder.salon.framework.AbstractDao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
public class AwardDao extends AbstractDao {
    private String name;
    private OrganisationDao organisation;
    private int acceptances;
    private int countries;
    private int awards;
    private int images;
    private int salons;
}

package uk.co.drumcoder.salon.service.country.dao;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.co.drumcoder.salon.framework.AbstractDao;

@Getter
@Setter
@NoArgsConstructor
public class CountryDao extends AbstractDao {
    private String name;
}

package uk.co.drumcoder.salon.service.award;

import uk.co.drumcoder.salon.service.award.dao.AwardDao;
import uk.co.drumcoder.salon.service.award.dao.AwardListDao;

import java.util.List;

public interface AwardService {

    AwardListDao listAllAwards();
}

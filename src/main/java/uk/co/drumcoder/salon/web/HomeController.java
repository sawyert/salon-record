package uk.co.drumcoder.salon.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import uk.co.drumcoder.salon.service.achievements.AchievementsService;
import uk.co.drumcoder.salon.service.achievements.dao.AwardAchievementsDao;
import uk.co.drumcoder.salon.service.achievements.dao.SubmissionListDao;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final AchievementsService achievementsService;

    @GetMapping("/")
    public String homepage(Model model) {
        AwardAchievementsDao awardAchievements = this.achievementsService.fetchAllAwardsStatus();

        model.addAttribute("OrganisationList", awardAchievements.list());

        return "home";
    }

    @GetMapping("/application/{organisationName}/{awardName}")
    public String submission(Model model, @PathVariable String organisationName, @PathVariable String awardName) {
        String award = awardName.replace('_','/');
        SubmissionListDao submissionList = this.achievementsService.fetchSubmissionList(organisationName, award);

        model.addAttribute("SubmissionList", submissionList.getSubmissionList());
        model.addAttribute("OrganisationName", organisationName);
        model.addAttribute("AwardName", award);

        return "submission";
    }
}

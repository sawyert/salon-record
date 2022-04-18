package uk.co.drumcoder.salon.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import uk.co.drumcoder.salon.service.achievements.AchievementsService;
import uk.co.drumcoder.salon.service.achievements.dao.AwardAchievementsDao;

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
}

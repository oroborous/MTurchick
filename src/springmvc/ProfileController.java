package springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfileController {
    @RequestMapping("/profile")
    public String showProfile(Model model) {
        return "profile";
    }

    @RequestMapping("/process-profile-form")
    public String processFormAnnotation(@RequestParam String firstName,
                                        @RequestParam String lastName,
                                        Model model) {

        String theMessage = "Hello, " + firstName + " " + lastName;

        model.addAttribute("message", theMessage);

        return "/confirm-manual";
    }
}

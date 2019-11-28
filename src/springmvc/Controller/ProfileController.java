package springmvc.Controller;

import com.turchik.hibernate.entity.Comment;
import com.turchik.hibernate.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springmvc.Service.IProfileService;

import javax.validation.Valid;

@Controller
public class ProfileController {
    @Autowired
    private IProfileService profileService;

    @RequestMapping("/profile")
    public String showProfile(Model model) {
        var profile = profileService.getProfile(102);
        model.addAttribute("profile", profile);
        model.addAttribute("new_comment", new Comment());
        model.addAttribute("del_comment", new Comment());
        return "profile";
    }

    @PostMapping("/updateProfile")
    public String updateProfile(@Valid @ModelAttribute Profile profile,
                                BindingResult bindingResult,
                                Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            return "redirect:/profile";
        }

        profileService.saveProfile(profile);
        return "redirect:/profile";
    }

    @PostMapping("/addProfileComment")
    public String addProfileComment(@Valid @ModelAttribute Comment comment,
                                    BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            return "redirect:/profile";
        }

        profileService.addComment(comment);
        return "redirect:/profile";
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        // Trim whitespace from all string form parameters read by this controller
        // If the entire string is whitespace, trim it to null
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

}

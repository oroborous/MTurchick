package springmvc.Controller;

import com.turchik.hibernate.entity.Comment;
import com.turchik.hibernate.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import springmvc.Service.IImageService;
import springmvc.Service.IProfileService;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ImageController {
    @Autowired
    private IImageService imageService;
    @Autowired
    private IProfileService profileService;

    @RequestMapping("/gallery")
    public String showGallery(Model model) {
        model.addAttribute("images", imageService.getImages());
        return "gallery";
    }

    @RequestMapping("/detail")
    public String showDetails(@RequestParam int imgId,
                              Model model) {
        model.addAttribute("image", imageService.getImage(imgId));
        model.addAttribute("new_comment", new Comment());
        return "detail";
    }

    @PostMapping("/addImageComment")
    public String addImageComment(@Valid @ModelAttribute Comment comment,
                                  BindingResult bindingResult,
                                  Model model) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            model.addAttribute("image", comment.getImage());
            model.addAttribute("new_comment", comment);
            return "detail";
        }

        imageService.addComment(comment);
        return "redirect:/detail?imgId=" + comment.getImage().getId();
    }

    @GetMapping("/favoriteImage")
    public String favoriteImage(@RequestParam int imageId,
                                @RequestParam int profileId) {
        var image = imageService.getImage(imageId);
        var profile = profileService.getProfile(profileId);
        image.addFavorite(profile);
        profileService.saveProfile(profile);
        return "redirect:/detail?imgId=" + image.getId();
    }

    @GetMapping("/unFavoriteImage")
    public String unFavoriteImage(@RequestParam int imageId,
                                  @RequestParam int profileId) {
        Image image = imageService.getImage(imageId);
        image.removeFavorite(profileId);
        imageService.saveImage(image);
        return "redirect:/detail?imgId=" + imageId;
    }

    @GetMapping("/searchImages")
    public String searchImages(@RequestParam String searchTerm,
                               Model model) {
        var images = imageService.getImages();
        List<Image> matching = new ArrayList<>();
        for (var img :
                images) {
            if (img.getTitle().contains(searchTerm))
                matching.add(img);
        }
        model.addAttribute("images", matching);
        return "gallery";
    }

    @InitBinder
    public void initBinder(WebDataBinder dataBinder) {
        // Trim whitespace from all string form parameters read by this controller
        // If the entire string is whitespace, trim it to null
        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }
}

package springmvc.Controller;

import com.turchik.hibernate.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import springmvc.Service.ICommentService;

import javax.validation.Valid;

@Controller
public class CommentController {
    @Autowired
    private ICommentService commentService;


    @PostMapping("/removeProfileComment")
    public String removeProfileComment(@Valid @ModelAttribute Comment comment,
                                       BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult);
            return "redirect:/profile";
        }

        commentService.deleteComment(comment.getId());
        return "redirect:/profile";
    }
}

package springmvc.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import springmvc.Service.ICommentService;

@Controller
public class CommentController {
    @Autowired
    private ICommentService commentService;

    @GetMapping("/removeProfileComment")
    public String removeProfileComment(@RequestParam int commentId) {
        commentService.deleteComment(commentId);
        return "redirect:/profile";
    }

    @GetMapping("/removeImageComment")
    public String removeImageComment(@RequestParam int commentId) {
        var imageId = commentService.getComment(commentId).getImage().getId();
        commentService.deleteComment(commentId);
        return "redirect:/detail?imgId=" + imageId;
    }
}

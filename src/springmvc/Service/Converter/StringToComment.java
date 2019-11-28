package springmvc.Service.Converter;

import com.turchik.hibernate.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import springmvc.Service.ICommentService;

public class StringToComment implements Converter<String, Comment> {
    @Autowired
    private ICommentService commentService;

    @Override
    public Comment convert(String source) {
        int commentId = Integer.parseInt(source);

        return commentService.getComment(commentId);
    }
}

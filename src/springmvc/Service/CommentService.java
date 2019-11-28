package springmvc.Service;

import com.turchik.hibernate.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springmvc.Dao.ICommentDao;

@Service
public class CommentService implements ICommentService {
    @Autowired
    private ICommentDao commentData;

    @Override
    public Comment getComment(int id) {
        return commentData.getComment(id);
    }

    @Override
    public void saveComment(Comment c) {
        //do some magic
        var valid = true;
        if (c.getImage() != null && c.getProfile() != null)
            valid = false;
        if (c.getImage() == null && c.getProfile() == null)
            valid = false;
        if (c.getContent() == null || c.getContent().isEmpty() || c.getContent().isBlank())
            valid = false;

        if (valid)
            commentData.saveComment(c);
    }

    @Override
    public void deleteComment(int id) {
        var comment = commentData.getComment(id);

        if (comment != null)
            commentData.deleteComment(id);
    }
}

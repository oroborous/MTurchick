package springmvc.Dao;

import com.turchik.hibernate.entity.Comment;
import com.turchik.hibernate.entity.Image;
import com.turchik.hibernate.entity.Profile;

import java.util.List;

public interface ICommentDao {
    List<Comment> getProfileComments(Profile p);

    List<Comment> getImageComments(Image i);

    Comment getComment(int id);

    void saveComment(Comment c);

    void deleteComment(int id);
}

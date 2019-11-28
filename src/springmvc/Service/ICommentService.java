package springmvc.Service;

import com.turchik.hibernate.entity.Comment;

public interface ICommentService {
    Comment getComment(int id);

    void saveComment(Comment c);

    void deleteComment(int id);
}

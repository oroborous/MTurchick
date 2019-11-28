package springmvc.Dao;

import com.turchik.hibernate.entity.Comment;
import com.turchik.hibernate.entity.Image;
import com.turchik.hibernate.entity.Profile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class CommentDao implements ICommentDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Comment> getProfileComments(Profile p) {
        Session session = sessionFactory.getCurrentSession();

        //create query
        var query = session.createQuery("from Comment where Profile = :link", Comment.class);
        //set param
        query.setParameter("link", p);

        return query.getResultList();
    }

    @Override
    @Transactional
    public List<Comment> getImageComments(Image i) {
        Session session = sessionFactory.getCurrentSession();

        //create query
        var query = session.createQuery("from Comment where Image = :link", Comment.class);
        //set param
        query.setParameter("link", i);

        return query.getResultList();
    }

    @Override
    @Transactional
    public Comment getComment(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Comment.class, id);
    }

    @Override
    @Transactional
    public void saveComment(Comment c) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(c);
    }

    @Override
    @Transactional
    public void deleteComment(int id) {
        Session session = sessionFactory.getCurrentSession();

        var query = session.createQuery("delete from Comment where id = :docId");
        query.setParameter("docId", id);

        query.executeUpdate();
    }
}

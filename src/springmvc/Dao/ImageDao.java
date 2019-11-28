package springmvc.Dao;

import com.turchik.hibernate.entity.Image;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ImageDao implements IImageDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Image> getImages() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("from Image", Image.class).getResultList();
    }

    @Override
    @Transactional
    public Image getImage(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Image.class, id);
    }

    @Override
    @Transactional
    public void saveImage(Image i) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(i);
    }

    @Override
    @Transactional
    public void deleteImage(int id) {
        Session session = sessionFactory.getCurrentSession();

        var query = session.createQuery("delete from Image where id = :docId");
        query.setParameter("docId", id);

        query.executeUpdate();
    }
}

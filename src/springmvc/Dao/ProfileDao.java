package springmvc.Dao;

import com.turchik.hibernate.entity.Profile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ProfileDao implements IProfileDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    @Transactional
    public List<Profile> getProfiles() {
        Session session = sessionFactory.getCurrentSession();

        return session.createQuery("from Profile", Profile.class).getResultList();
    }

    @Override
    @Transactional
    public Profile getProfile(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Profile.class, id);
    }

    @Override
    @Transactional
    public void saveProfile(Profile p) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(p);
    }


    @Override
    @Transactional
    public void deleteProfile(int id) {
        Session session = sessionFactory.getCurrentSession();

        var query = session.createQuery("delete from Profile where id = :docId");
        query.setParameter("docId", id);

        query.executeUpdate();
    }
}

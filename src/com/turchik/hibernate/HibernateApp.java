package com.turchik.hibernate;

import com.turchik.hibernate.entity.Comment;
import com.turchik.hibernate.entity.CommentType;
import com.turchik.hibernate.entity.Image;
import com.turchik.hibernate.entity.Profile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class HibernateApp {
    private SessionFactory factory;

    private HibernateApp() {
        factory = new Configuration().configure("hibernate.cfg.xml")
                .addAnnotatedClass(Profile.class)
                .addAnnotatedClass(Image.class)
                .addAnnotatedClass(Comment.class)
                .buildSessionFactory();
    }

    private void close() {
        factory.close();
    }

    private static void write(String s) {
        System.out.println(s);
        System.out.println();
    }

    private static void writeList(List<String> list) {
        for (String s :
                list) {
            System.out.println(s);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        HibernateApp app = new HibernateApp();

        try {
            //Un comment out sequentially or all at once
            System.out.println("========================================");
            app.doProfileStuff();
            System.out.println("========================================");
//            app.doImageStuff();
            System.out.println("========================================");
//            app.doProfileCommentStuff();
            System.out.println("========================================");
//            app.doImageCommentStuff();
            System.out.println("========================================");
        } finally {
            app.close();
        }
    }

    //=========================================
    private void doProfileCommentStuff() {
        write("Create Profile =");
        var profileId = createProfile();
        readProfile(profileId);

        write("Create Comment =");
        var commentId = createComment(profileId, CommentType.Profile);
        readProfile(profileId);

        write("Update Comment =");
        updateComment(commentId);
        readProfile(profileId);

        write("Delete Comment =");
        deleteComment(commentId);
        readProfile(profileId);

        write("Delete Profile =");
        deleteProfile(profileId);
    }

    private void doImageCommentStuff() {
        write("Create Image =");
        var imageId = createImage();
        readImage(imageId);

        write("Create Comment =");
        var commentId = createComment(imageId, CommentType.Image);
        readImage(imageId);

        write("Update Comment =");
        updateComment(commentId);
        readImage(imageId);

        write("Delete Comment =");
        deleteComment(commentId);
        readImage(imageId);

        write("Delete Image =");
        deleteImage(imageId);
    }

    private int createComment(int id, CommentType type) {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        var comment = new Comment("BEST COMMENT EVER!");
        switch (type) {
            case Profile:
                Profile profile = session.get(Profile.class, id);
                profile.addComment(comment);
                break;
            case Image:
                Image image = session.get(Image.class, id);
                image.addComment(comment);
                break;
        }

        session.getTransaction().commit();
        return comment.getId();
    }

    private void updateComment(int id) {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        var comment = session.get(Comment.class, id);
        if (comment != null)
            comment.setContent("WORST COMMENT SINCE THE FIRST!");

        session.getTransaction().commit();
    }

    private void deleteComment(int id) {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        var comment = session.get(Comment.class, id);
        if (comment != null)
            session.delete(comment);

        session.getTransaction().commit();
    }
    //=========================================

    //=========================================
    private void doProfileStuff() {
        readProfiles();
        var id = createProfile();
        readProfile(id);
        updateProfile(id);
        readProfile(id);
        deleteProfile(id);
        readProfiles();
    }

    private int createProfile() {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        var profile = new Profile("New Profile", "Wow great starting motto!", "Mice");
        session.save(profile);

        session.getTransaction().commit();
        return profile.getId();
    }

    private void readProfiles() {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        var profiles = session.createQuery("from Profile").getResultList();
        List<String> profileStrings = new ArrayList<>();
        for (var p :
                profiles) {
            profileStrings.add(p.toString());
        }
        writeList(profileStrings);

        session.getTransaction().commit();
    }

    private void readProfile(int id) {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        var profile = session.get(Profile.class, id);
        if (profile != null)
            write(profile.toString());

        session.getTransaction().commit();
    }

    private void updateProfile(int id) {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        var profile = session.get(Profile.class, id);
        if (profile != null) {
            profile.setName("Updated Profile");
            profile.setMotto("Best Updated Motto!");
            profile.setFavorite("Doggos");
        }

        session.getTransaction().commit();
    }

    private void deleteProfile(int id) {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        var profile = session.get(Profile.class, id);
        if (profile != null)
            session.delete(profile);

        session.getTransaction().commit();
    }
    //=========================================

    //=========================================
    private void doImageStuff() {
        readImages();
        var id = createImage();
        readImage(id);
        updateImage(id);
        readImage(id);
        deleteImage(id);
        readImages();
    }

    private int createImage() {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        var image = new Image("New Mouse", "img/1.jpeg", "Nice Mouse!");
        session.save(image);

        session.getTransaction().commit();
        return image.getId();
    }

    private void readImages() {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        var images = session.createQuery("from Image").getResultList();
        List<String> imageStrings = new ArrayList<>();
        for (var i :
                images) {
            imageStrings.add(i.toString());
        }
        writeList(imageStrings);

        session.getTransaction().commit();
    }

    private void readImage(int id) {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        var image = session.get(Image.class, id);
        if (image != null)
            write(image.toString());

        session.getTransaction().commit();
    }

    private void updateImage(int id) {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        var image = session.get(Image.class, id);
        if (image != null) {
            image.setTitle("Updated Mouse");
            image.setFileName("img/2.jpeg");
            image.setDescription("Even better mouse!");
        }

        session.getTransaction().commit();
    }

    private void deleteImage(int id) {
        Session session = factory.getCurrentSession();

        session.beginTransaction();

        var image = session.get(Image.class, id);
        if (image != null)
            session.delete(image);

        session.getTransaction().commit();
    }
    //=========================================
}

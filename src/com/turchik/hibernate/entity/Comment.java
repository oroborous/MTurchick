package com.turchik.hibernate.entity;

import javax.persistence.*;

@Entity
@Table(name = "COMMENT")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMMENTID")
    private int Id;
    @Column(name = "CONTENT")
    private String Content;
    //The cascade type used here includes all but delete, as the parent (Profile) should be updated without
    //  the deleted comment, but we shouldn't delete the parent along with the comment.
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "IMAGEID")
    private Image Image;
    //The cascade type used here includes all but delete, as the parent (Image) should be updated without
    //  the deleted comment, but we shouldn't delete the parent along with the comment.
    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "PROFILEID")
    private Profile Profile;

    public Comment() {
    }

    public Comment(String content) {
        Content = content;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public com.turchik.hibernate.entity.Image getImage() {
        return Image;
    }

    public void setImage(com.turchik.hibernate.entity.Image image) {
        Image = image;
    }

    public com.turchik.hibernate.entity.Profile getProfile() {
        return Profile;
    }

    public void setProfile(com.turchik.hibernate.entity.Profile profile) {
        Profile = profile;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "Id=" + Id +
                ", Content='" + Content + '\'' +
                '}';
    }
}


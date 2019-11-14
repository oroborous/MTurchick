package com.turchik.hibernate.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "PROFILE")
public class Profile {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROFILEID")
    private int Id;
    @Column(name = "NAME")
    private String Name;
    @Column(name = "MOTTO")
    private String Motto;
    @Column(name = "FAVORITE")
    private String Favorite;
    //Cascade type must be all as the PK of Profile is found in Comments.
    //If Profile is deleted, then if comments are not deleted, there will be orphans. (And orphans make me sad)
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "Profile")
    private List<Comment> Comments;

    public Profile() {
    }

    public Profile(String name, String motto, String favorite) {
        Name = name;
        Motto = motto;
        Favorite = favorite;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMotto() {
        return Motto;
    }

    public void setMotto(String motto) {
        Motto = motto;
    }

    public String getFavorite() {
        return Favorite;
    }

    public void setFavorite(String favorite) {
        Favorite = favorite;
    }

    public List<Comment> getComments() {
        return Comments == null ? new ArrayList<>() : Comments;
    }

    public void setComments(List<Comment> comments) {
        Comments = comments;
    }

    public void addComment(Comment comment) {
        if (Comments == null)
            Comments = new ArrayList<>();
        Comments.add(comment);
        //Reverse set image to profile
        comment.setProfile(this);
    }

    @Override
    public String toString() {
        return "Profile{" +
                "Id=" + Id +
                ", Name='" + Name + '\'' +
                ", Motto='" + Motto + '\'' +
                ", Favorite='" + Favorite + '\'' +
                ", Comments='" + getComments() + '\'' +
                '}';
    }
}

package com.turchik.hibernate.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
@Entity
@Table(name = "PROFILE")
public class Profile {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PROFILEID")
    private int Id;

    @NotNull(message = "Required")
    @Size(min = 5, max = 30, message = "Must be between 5 and 30.")
    @Column(name = "NAME")
    private String Name;

    @NotNull(message = "Required")
    @Size(min = 5, max = 50, message = "Must be between 5 and 50.")
    @Column(name = "MOTTO")
    private String Motto;

    @NotNull(message = "Required")
    @Size(min = 5, max = 30, message = "Must be between 5 and 30.")
    @Column(name = "FAVORITE")
    private String Favorite;
    //Cascade type must be all as the PK of Profile is found in Comments.
    //If Profile is deleted, then if comments are not deleted, there will be orphans. (And orphans make me sad)
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "Profile", fetch = FetchType.EAGER)
    private List<Comment> Comments;

    @ManyToMany(cascade = {CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH},
            fetch = FetchType.EAGER)
    @JoinTable(name = "FAVORITE_IMAGES",
            joinColumns = @JoinColumn(name = "PROFILEID"),
            inverseJoinColumns = @JoinColumn(name = "IMAGEID"))
    private List<Image> imagesFavorited;

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

    public List<Image> getImages() {
        return imagesFavorited;
    }

    public void setImages(List<Image> imagesFavorited) {
        this.imagesFavorited = imagesFavorited;
    }

    public void addFavorite(Image image) {
        if (imagesFavorited == null)
            imagesFavorited = new ArrayList<>();

        if (!imagesFavorited.contains(image)) {
            imagesFavorited.add(image);
            //Reverse set image to comment
            image.addFavorite(this);
        }
    }

    public void removeFavorite(int id) {
        if (imagesFavorited == null)
            imagesFavorited = new ArrayList<>();

        imagesFavorited.removeIf((image) -> image.getId() == id);
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

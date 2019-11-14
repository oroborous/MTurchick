package com.turchik.hibernate.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "IMAGE")
public class Image {
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IMAGEID")
    private int Id;
    @Column(name = "TITLE")
    private String Title;
    @Column(name = "FILENAME")
    private String FileName;
    @Column(name = "DESCRIPTION")
    private String Description;
    //Cascade type must be all as the PK of Image is found in Comments.
    //If Image is deleted, then if comments are not deleted, there will be orphans. (And orphans make me sad)
    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "Image")
    private List<Comment> Comments;

    public Image() {
    }

    public Image(String title, String fileName, String description) {
        Title = title;
        FileName = fileName;
        Description = description;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
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
        //Reverse set image to comment
        comment.setImage(this);
    }

    @Override
    public String toString() {
        return "Image{" +
                "Id=" + Id +
                ", Title='" + Title + '\'' +
                ", FileName='" + FileName + '\'' +
                ", Description='" + Description + '\'' +
                ", Comments='" + getComments() + '\'' +
                '}';
    }
}

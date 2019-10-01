package springxml.beans;

public class ImageBean {
    private int ImageId;
    private String FileName;
    private String Title;
    private String Description;
    private boolean Favorite;

    public ImageBean() {
        ImageId = 0;
        FileName = "";
        Title = "";
        Description = "";
        Favorite = false;
    }

    public int getImageId() {
        return ImageId;
    }

    public void setImageId(int imageId) {
        ImageId = imageId;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String fileName) {
        FileName = fileName;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public boolean isFavorite() {
        return Favorite;
    }

    public void setFavorite(boolean favorite) {
        Favorite = favorite;
    }
}

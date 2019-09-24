package nicemice.profile;

import java.io.Serializable;

public class ProfileBean implements Serializable {
    private String name;
    private String favorite;
    private String motd;

    public ProfileBean() {
        name = "name";
        favorite = "fave";
        motd = "message";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFavorite() {
        return favorite;
    }

    public void setFavorite(String favorite) {
        this.favorite = favorite;
    }

    public String getMotd() {
        return motd;
    }

    public void setMotd(String motd) {
        this.motd = motd;
    }
}

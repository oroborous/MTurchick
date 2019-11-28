package springmvc.Service;

import com.turchik.hibernate.entity.Comment;
import com.turchik.hibernate.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springmvc.Dao.IProfileDao;

import java.util.List;

@Service
public class ProfileService implements IProfileService {
    @Autowired
    private IProfileDao profileData;

    @Override
    public List<Profile> getProfiles() {
        return profileData.getProfiles();
    }

    @Override
    public List<Comment> getProfileComments(int id) {
        var profile = getProfile(id);
        return profile.getComments();
    }

    @Override
    public Profile getProfile(int id) {
        return profileData.getProfile(id);
    }

    @Override
    public void saveProfile(Profile p) {
        //do some checking on given profile
        var valid = true;
        if (p.getName() == null || p.getName().isBlank() || p.getName().isEmpty())
            valid = false;
        if (p.getFavorite() == null || p.getFavorite().isBlank() || p.getFavorite().isEmpty())
            valid = false;
        if (p.getMotto() == null || p.getMotto().isBlank() || p.getMotto().isEmpty())
            valid = false;

        if (valid)
            profileData.saveProfile(p);
    }

    @Override
    public void deleteProfile(int id) {
        var toDelete = profileData.getProfile(id);

        if (toDelete != null)
            profileData.deleteProfile(id);
    }

    @Override
    public void addComment(Comment c) {
        if (c.getProfile() != null) {
            c.getProfile().addComment(c);
            profileData.saveProfile(c.getProfile());
        }
    }
}

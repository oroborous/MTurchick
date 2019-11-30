package springmvc.Service;

import com.turchik.hibernate.entity.Comment;
import com.turchik.hibernate.entity.Profile;

import java.util.List;

public interface IProfileService {
    List<Profile> getProfiles();

    List<Comment> getProfileComments(int id);

    Profile getProfile(int id);

    void saveProfile(Profile p);

    void deleteProfile(int id);

    void addComment(Comment c);
}

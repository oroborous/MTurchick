package springmvc.Dao;

import com.turchik.hibernate.entity.Profile;

import java.util.List;

public interface IProfileDao {
    List<Profile> getProfiles();

    Profile getProfile(int id);

    void saveProfile(Profile p);

    void deleteProfile(int id);
}

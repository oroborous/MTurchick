package springmvc.Service.Converter;

import com.turchik.hibernate.entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import springmvc.Service.IProfileService;

public class StringToProfile implements Converter<String, Profile> {
    @Autowired
    private IProfileService profileService;

    @Override
    public Profile convert(String source) {
        int profileId = Integer.parseInt(source);

        return profileService.getProfile(profileId);
    }
}

package springmvc.Service.Converter;

import com.turchik.hibernate.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import springmvc.Service.IImageService;

public class StringToImage implements Converter<String, Image> {
    @Autowired
    private IImageService imageService;

    @Override
    public Image convert(String source) {
        int imageId = Integer.parseInt(source);

        return imageService.getImage(imageId);
    }
}

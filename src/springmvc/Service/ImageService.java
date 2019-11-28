package springmvc.Service;

import com.turchik.hibernate.entity.Comment;
import com.turchik.hibernate.entity.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springmvc.Dao.IImageDao;

import java.util.List;

@Service
public class ImageService implements IImageService {
    @Autowired
    private IImageDao imageData;

    @Override
    public List<Image> getImages() {
        return imageData.getImages();
    }

    @Override
    public List<Comment> getImageComments(int id) {
        var image = getImage(id);
        return image.getComments();
    }

    @Override
    public Image getImage(int id) {
        return imageData.getImage(id);
    }
}

package springmvc.Service;

import com.turchik.hibernate.entity.Comment;
import com.turchik.hibernate.entity.Image;

import java.util.List;

public interface IImageService {
    List<Image> getImages();

    List<Comment> getImageComments(int id);

    Image getImage(int id);
    //todo: I don't want to implement these methods for images just yet... lot of work
    //void saveImage(Image i);
    //void deleteImage(int id);
}

package springmvc.Service;

import com.turchik.hibernate.entity.Comment;
import com.turchik.hibernate.entity.Image;

import java.util.List;

public interface IImageService {
    List<Image> getImages();

    List<Comment> getImageComments(int id);

    Image getImage(int id);

    void saveImage(Image i);

    void addComment(Comment c);

}

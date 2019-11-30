package springmvc.Dao;

import com.turchik.hibernate.entity.Image;

import java.util.List;

public interface IImageDao {
    List<Image> getImages();

    Image getImage(int id);

    void saveImage(Image i);

    void deleteImage(int id);
}

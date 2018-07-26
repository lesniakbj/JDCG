package ui.util;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;

public class ImageScaleUtil {
    public static final double NORMAL_IMAGE_RATIO = (9.0 / 16.0);
    public static final double MAP_IMAGE_HEIGHT_RATIO = (9.0 / 21.0);

    public static BufferedImage tryLoadImage(String imagePath) {
        try {
            URL path = ImageScaleUtil.class.getResource(imagePath);
            if(path != null) {
                return ImageIO.read(path);
            }
        } catch (IOException ignored) {}

        return new BufferedImage(1, 1, 1);
    }
}

package ui.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageScaleUtil {
    public static final double NORMAL_IMAGE_RATIO = (9.0 / 16.0);
    public static final double MAP_IMAGE_HEIGHT_RATIO = (9.0 / 21.0);

    public static BufferedImage tryLoadImage(String imagePath) {
        try {
            return ImageIO.read(ImageScaleUtil.class.getResource(imagePath));
        } catch (IOException ignored) {}

        return null;
    }
}


package com.wireghost;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import java.awt.image.BufferedImage;

public class ImageUtil {
    public static Image convertToFxImage(BufferedImage awtImage) {
        return SwingFXUtils.toFXImage(awtImage, null);
    }
}

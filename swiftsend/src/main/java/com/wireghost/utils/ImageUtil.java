package com.wireghost.utils;


import java.awt.image.BufferedImage;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class ImageUtil {
    public static Image convertToFxImage(BufferedImage awtImage) {
        return SwingFXUtils.toFXImage(awtImage, null);
    }
}

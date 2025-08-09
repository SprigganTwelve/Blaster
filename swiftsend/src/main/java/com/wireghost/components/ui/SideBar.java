package com.wireghost;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.layout.VBox;

public final class SideBar extends VBox {

    public   final double  MARGIN = 50.0;
    public   final double  SVG_HEIGHT = 25.0;
    public static  final double  SVG_WIDTH = 25.0;
    public static  final double  PREF_WIDTH = SVG_WIDTH + 10;

    public SideBar(double spacing) {
        super(spacing);

        this.setPrefWidth(PREF_WIDTH);
        this.setAlignment(Pos.CENTER);

        try {
            CustomSVGImage app = new CustomSVGImage("/assets/icons/app-apps-applications-tile-delete-svgrepo-com.svg", Cursor.HAND);
            CustomSVGImage folder = new CustomSVGImage("/assets/icons/folder-svgrepo-com.svg", Cursor.HAND);
            CustomSVGImage sharing = new CustomSVGImage("/assets/icons/sharing-symbol-svgrepo-com.svg", Cursor.HAND);
            CustomSVGImage settings = new CustomSVGImage("/assets/icons/settings-minimalistic-svgrepo-com.svg");

            this.getChildren().addAll(
                app.getScaledImageView(SVG_WIDTH, SVG_HEIGHT),
                folder.getScaledImageView(SVG_WIDTH, SVG_HEIGHT),
                sharing.getScaledImageView(SVG_WIDTH, SVG_HEIGHT),
                settings.getScaledImageView(SVG_WIDTH, SVG_HEIGHT)
            );

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}

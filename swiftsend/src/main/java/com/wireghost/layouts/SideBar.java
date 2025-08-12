package com.wireghost.layouts;

import com.wireghost.components.ui.CustomSVGImage;

import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.layout.VBox;

public final class SideBar extends VBox {

    public final double  MARGIN = 50.0;
    public final double  SVG_HEIGHT = 25.0;
    public static  final double  SVG_WIDTH = 25.0;
    public static  final double  PREF_WIDTH = SVG_WIDTH + 10;

    public SideBar(double spacing) {
        super(spacing);

        this.setPrefWidth(PREF_WIDTH);
        this.setAlignment(Pos.CENTER);

        try {
            CustomSVGImage app = new CustomSVGImage("/assets/icons/black/app-apps-applications-tile-delete-svgrepo-com.svg", Cursor.HAND);
            CustomSVGImage folder = new CustomSVGImage("/assets/icons/black/folder-svgrepo-com.svg", Cursor.HAND);
            CustomSVGImage sharing = new CustomSVGImage("/assets/icons/black/sharing-symbol-svgrepo-com.svg", Cursor.HAND);
            CustomSVGImage browse =  new CustomSVGImage("/assets/icons/black/browser-svgrepo-com.svg", Cursor.HAND);
            CustomSVGImage settings = new CustomSVGImage("/assets/icons/black/settings-minimalistic-svgrepo-com.svg", Cursor.HAND);
            CustomSVGImage favorite = new CustomSVGImage("/assets/icons/black/favorite-svgrepo-com.svg", Cursor.HAND);

            this.getChildren().addAll(
                app.getScaledNode(SVG_WIDTH, SVG_HEIGHT),
                folder.getScaledNode(SVG_WIDTH, SVG_HEIGHT),
                sharing.getScaledNode(SVG_WIDTH, SVG_HEIGHT),
                browse.getScaledNode(SVG_WIDTH, SVG_HEIGHT),
                favorite.getScaledNode(SVG_WIDTH, SVG_HEIGHT) ,
                settings.getScaledNode(SVG_WIDTH, SVG_HEIGHT)
            );

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}


package com.wireghost.layouts;

import com.wireghost.components.ui.CustomSVGImage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;


public final  class QuickAccessBar extends HBox
{
    public QuickAccessBar(double width)
    {
        super(40.0);
        this.setAlignment(Pos.CENTER);

        final double quickAccessSvgWidth = 35.0 ;
        final double quickAccessSvgHeight = 35.0;
        final double quickAccessSVGPadding = 8.0;


        CustomSVGImage imageCustomSVG = new CustomSVGImage("/assets/icons/image-nature-round-svgrepo-com.svg", Cursor.HAND);
        Pane  imageSVGPane = (Pane) imageCustomSVG.getScaledNode(quickAccessSvgWidth, quickAccessSvgHeight);

        if (imageSVGPane != null ) {
            imageSVGPane.setPadding(new Insets(quickAccessSVGPadding));
            imageSVGPane.setBackground(new  Background(
                new BackgroundFill(Color.rgb(137, 159, 232),   new CornerRadii(5.0), Insets.EMPTY)
            ));
        }


        CustomSVGImage videosCustomSVG = new CustomSVGImage("/assets/icons/youtube-play-svgrepo-com.svg", Cursor.HAND);
        Pane  videosSVGPane = (Pane) videosCustomSVG.getScaledNode(quickAccessSvgWidth, quickAccessSvgHeight);

        if (videosSVGPane != null ) {
            videosSVGPane.setPadding(new Insets(quickAccessSVGPadding));
            videosSVGPane.setBackground(new  Background(
                new BackgroundFill(Color.rgb(174, 11, 234),   new CornerRadii(5.0), Insets.EMPTY)
            ));
        }


        CustomSVGImage audiosCustomSVG = new CustomSVGImage("/assets/icons/audio-ui-svgrepo-com.svg", Cursor.HAND);
        Pane  audiosSVGPane = (Pane) audiosCustomSVG.getScaledNode(quickAccessSvgWidth, quickAccessSvgHeight);

        if (audiosSVGPane != null ) {
            audiosSVGPane.setPadding(new Insets(quickAccessSVGPadding));
            audiosSVGPane.setBackground(new  Background(
                new BackgroundFill(Color.rgb(60, 205, 147),   new CornerRadii(5.0), Insets.EMPTY)
            ));
        }

        CustomSVGImage docsCustomSVG = new CustomSVGImage("/assets/icons/google-docs-svgrepo-com.svg", Cursor.HAND);
        Pane  docsSVGPane = (Pane) docsCustomSVG.getScaledNode(quickAccessSvgWidth, quickAccessSvgHeight);

        if (docsSVGPane != null ) {
            docsSVGPane.setPadding(new Insets(quickAccessSVGPadding));
            docsSVGPane.setBackground(new  Background(
                new BackgroundFill(Color.rgb(208, 193, 235),   new CornerRadii(5.0), Insets.EMPTY)
            ));
        }

        CustomSVGImage appsCustomSVG = new CustomSVGImage("/assets/icons/apps-svg.svg", Cursor.HAND);
        Pane  appsSVGPane = (Pane) appsCustomSVG.getScaledNode(quickAccessSvgWidth, quickAccessSvgHeight);

        if (appsSVGPane != null ) {
            appsSVGPane.setPadding(new Insets(quickAccessSVGPadding));
            appsSVGPane.setBackground(new  Background(
                new BackgroundFill(Color.rgb(234, 64, 32),   new CornerRadii(5.0), Insets.EMPTY)
            ));
        }

        CustomSVGImage downloadCustomSVG = new CustomSVGImage("/assets/icons/download-square-svgrepo-com.svg", Cursor.HAND);
        Pane  downloadSVGPane = (Pane) downloadCustomSVG.getScaledNode(quickAccessSvgWidth, quickAccessSvgHeight);

        if (downloadSVGPane != null ) {
            downloadSVGPane.setPadding(new Insets(quickAccessSVGPadding));
            downloadSVGPane.setBackground(new  Background(
                new BackgroundFill(Color.rgb(88, 14, 235),   new CornerRadii(5.0), Insets.EMPTY)
            ));
        }


        this.getChildren().addAll( imageSVGPane, videosSVGPane, audiosSVGPane, docsSVGPane, appsSVGPane, downloadSVGPane );

    }
}
package com.wireghost.components.ui;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import  javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class CustomSVGImage {

    public boolean activate; // define a behaviour when the svg is click

    private Pane pane;
    private Cursor cursor;
    private final String svgPath;
    private  ImageView imageView;
 
    public CustomSVGImage(String svgPath) {
        this.svgPath = svgPath;
    }

    public CustomSVGImage(String svgPath, Cursor cursor)
    {
        this.svgPath = svgPath;
        this.cursor = cursor;
    }


    public CustomSVGImage(String svgPath, Cursor cursor, boolean activate)
    {
        this.cursor = cursor;
        this.svgPath = svgPath;
        this.activate = activate;
    }

    public ImageView getImageView(){
        return imageView;
    }
    

    public Node getScaledNode(double width, double height) {

        try (InputStream svgStream = getClass().getResourceAsStream(svgPath)) {
            if (svgStream == null) {
                throw new RuntimeException("SVG not found: " + svgPath);
            }

            TranscoderInput input = new TranscoderInput(svgStream);

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            TranscoderOutput output = new TranscoderOutput(outputStream);

            PNGTranscoder transcoder = new PNGTranscoder();
            transcoder.addTranscodingHint(PNGTranscoder.KEY_WIDTH, (float) width);
            transcoder.addTranscodingHint(PNGTranscoder.KEY_HEIGHT, (float) height);

            ByteArrayInputStream pngInputStream = new ByteArrayInputStream(outputStream.toByteArray());
            Image fxImage = new Image(pngInputStream);

            imageView = new ImageView(fxImage);
            imageView.setFitWidth(width);
            imageView.setFitHeight(height);
            imageView.setPreserveRatio(true);

            if(this.cursor != null){
                
                pane = new Pane(imageView);
                pane.setCursor(this.cursor);

                imageView.layoutXProperty().bind(pane.widthProperty().subtract(imageView.fitWidthProperty()).divide(2));
                imageView.layoutYProperty().bind(pane.heightProperty().subtract(imageView.fitHeightProperty()).divide(2));


                if (activate) {
                    pane.setBackground(new Background(
                        new BackgroundFill(
                            Color.rgb(65, 62, 62, 1),
                            new CornerRadii(10.15),
                            new Insets(6.0)
                        )   
                    ));
                }

                return pane;
            }

            transcoder.transcode(input, output);

            return imageView;

        }
         catch (Exception e) {
            e.printStackTrace();
            return new ImageView(); // empty fallback
        }
    }

    public Pane getPane()
    {
        return  pane;
    }
}

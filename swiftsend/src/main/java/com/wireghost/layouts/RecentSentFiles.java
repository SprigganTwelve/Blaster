package com.wireghost.layouts;

import java.util.ArrayList;

import com.wireghost.components.ui.CustomSVGImage;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;



public final class RecentSentFiles
{
    private ArrayList<String> filesArray;


    public RecentSentFiles(ArrayList<String> filesArray)
    {
        this.filesArray = filesArray;
    }

    public Node getNode()
    {
        System.err.println(
            filesArray
        );
        if (filesArray.isEmpty()) {

            StackPane stackPane = new StackPane();
            stackPane.setPrefHeight(90.0);
            stackPane.setMinHeight(90.0);
            stackPane.setMaxHeight(90.0);

            Rectangle rectangle = new Rectangle();
            rectangle.heightProperty().bind( stackPane.heightProperty() ); // fit the stackPane height
            rectangle.widthProperty().bind(  stackPane.widthProperty() );   // fit the stackPane width 
            rectangle.setManaged(false);

            rectangle.setArcWidth(10); //  horizontal radius
            rectangle.setArcHeight(10); // vertical radius
            rectangle.setStrokeWidth(1);
            rectangle.setFill(Color.TRANSPARENT);
            rectangle.setStroke( Color.rgb(81, 82, 81) );
            rectangle.setStrokeLineCap(StrokeLineCap.ROUND);
            rectangle.getStrokeDashArray().addAll(15.0, 10.0);


            CustomSVGImage notFoundSVG = new CustomSVGImage("/assets/icons/not-found.svg");
            Node notFoundSVGNode = notFoundSVG.getScaledNode(40.0, 40.0);

            VBox caption = new VBox(20.0);
            caption.setAlignment(Pos.CENTER);
            Text subTitle = new Text("No recent files transferred");
            subTitle.setFont(Font.font("Calibri", FontWeight.BOLD,  15.0));
            caption.getChildren().addAll(notFoundSVGNode, subTitle);

            stackPane.getChildren().addAll(rectangle, caption);

            return stackPane;

        }
        else {
            Pane pane = new Pane();
            return  pane;
        }
    }


    public void setFilesArray(String str)
    {
        filesArray.add(str);
    }
}
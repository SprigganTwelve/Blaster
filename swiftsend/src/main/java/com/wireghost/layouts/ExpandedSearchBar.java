package com.wireghost.layouts;


import com.wireghost.components.ui.CustomSVGImage;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public final class ExpandedSearchBar extends HBox {

    public TextField textField;
    public final double PREF_MARGIN = 15;
    public final static  double[] PADDING = { 2, 8, 2, 8 } ; // top, right, bottom, left 


    //---------constructors

    public ExpandedSearchBar( double availableSpace ) {
        super(12.0);
        super.setAlignment(Pos.CENTER);
        build(availableSpace);
    }



    public ExpandedSearchBar(double availableSpace, String style)
    {
        super.setAlignment(Pos.CENTER);
        build(availableSpace);
        if(textField != null)
            textField.setStyle(style);
    } 

    public ExpandedSearchBar(double availableSpace, Font font)
    {
        super.setAlignment(Pos.CENTER);
        build(availableSpace);
        if(textField != null)
            textField.setFont(font);
    } 


    //--- Generics/shared constructor function 

    public void build(
                    double availableSpace           // it is the width that should be occupied/reserved for the seacrhBar 
        )                                           // build the basis of the app searchBar
    {

        CustomSVGImage searchIcon = new CustomSVGImage("/assets/icons/search-alt-svgrepo-com.svg", Cursor.HAND);

        TextField input = new TextField();
        input.getStyleClass().add("custom-input");

        HBox.setHgrow(input, Priority.ALWAYS);

        input.setMaxWidth(Double.MAX_VALUE);
        this.textField = input;
        
        this.setPrefWidth(availableSpace);
        this.setMaxHeight(30.0);
        this.setPadding(new Insets(PADDING[0], PADDING[1], PADDING[2], PADDING[3]));

        this.setBackground(
            new Background(
                new BackgroundFill(
                    Color.rgb(249, 250, 254, 1.0),
                    new CornerRadii(10.15),
                    null
                )
            )
        );

        this.getChildren().addAll(
            input, searchIcon.getScaledNode(25.0, 25.0)
        );
    }

    public double getPrefMargin() {
        return PREF_MARGIN;
    }

}


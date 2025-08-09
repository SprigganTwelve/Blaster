

package com.wireghost;


import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;


public class MediaHub extends VBox {
    
    public  MediaHub(double windowWidth)
    {
        ExpandedSearchBar searchBar = new ExpandedSearchBar(0.6 * (windowWidth - SideBar.PREF_WIDTH), Font.font("Calibri", FontWeight.BOLD ,15.0  )); 
        ObservableList<Node> children = this.getChildren();

        VBox.setMargin(searchBar, new Insets( 0, searchBar.PREF_MARGIN, 0, searchBar.PREF_MARGIN ));

        children.addAll(searchBar);
    }

}

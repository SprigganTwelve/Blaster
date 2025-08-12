

package com.wireghost.templates;



import java.util.ArrayList;

import com.wireghost.layouts.QuickAccessBar;
import com.wireghost.layouts.RecentSentFiles;
import com.wireghost.layouts.SideBar;

import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;



public final class MediaHub extends VBox {

    public  MediaHub(double WINDOW_WIDTH)
    {
        super(25.0);
        super.setAlignment(Pos.CENTER_LEFT);


        Text quickAccessTitle = new Text("Quick Access");
        quickAccessTitle.setFont(Font.font("Calibri", FontWeight.BOLD , 20.0));
        QuickAccessBar quickAccessBar = new QuickAccessBar(0.6 * (WINDOW_WIDTH - SideBar.PREF_WIDTH));
        
        // String[] filesArray = ;
        Text rencentSentFilesTitle = new Text("Recent Files");
        rencentSentFilesTitle.setFont(Font.font("Calibri", FontWeight.BOLD , 20.0));
        RecentSentFiles recentSentFiles = new RecentSentFiles(new ArrayList<String>());
        Node recentSentFilesNode = recentSentFiles.getNode();
        VBox.setVgrow(recentSentFilesNode, Priority.ALWAYS);



        ObservableList<Node> children = this.getChildren();
        children.addAll( quickAccessTitle,  quickAccessBar, rencentSentFilesTitle, recentSentFilesNode);
    }

}

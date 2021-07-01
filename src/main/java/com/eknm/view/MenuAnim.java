package com.eknm.view;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;


/**
 * Class to  anim menu
 */
public class MenuAnim {
    private MenuAnim() {
    }

    /**
     * Size of shift
     */
    public static final int SIZE_OF_SHIFT = 300;
    /**
     * Width of menu panel
     */
    public static final double SIZE_OF_PANEL = 255;

    /**
     * Method to open menu
     *
     * @param menu pull-out menu
     * @param fon  brown fon pane
     */
    public static void animOpen(AnchorPane menu, AnchorPane fon) {
        fon.setVisible(true);
        AnchorPane.setLeftAnchor(menu, (double) -SIZE_OF_SHIFT);
        final Timeline loop = new Timeline(new KeyFrame(Duration.millis(1), event -> change(menu, fon, 1)));
        loop.setCycleCount(SIZE_OF_SHIFT);
        loop.playFromStart();
    }

    /**
     * Method to close menu
     *
     * @param menu (menu) with buttons with another calculators
     * @param fon  brown fon pane
     */
    public static void animClose(AnchorPane menu, AnchorPane fon) {
        fon.setVisible(false);
        AnchorPane.setLeftAnchor(menu, 0d);
         final Timeline loop = new Timeline(new KeyFrame(Duration.millis(1),
                 new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                change(menu,fon,-1);
            }
        }));
        loop.setCycleCount(SIZE_OF_SHIFT);
        loop.playFromStart();
    }


    private static void change(AnchorPane menu, AnchorPane fon, int change) {
        double tmp = AnchorPane.getLeftAnchor(menu);
        AnchorPane.setLeftAnchor(menu, tmp + change);
        AnchorPane.setLeftAnchor(fon, tmp + SIZE_OF_PANEL);
    }
}

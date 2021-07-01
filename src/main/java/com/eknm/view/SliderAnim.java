package com.eknm.view;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.util.Duration;


import java.util.Set;

/**
 * Class for slider anim
 */
public class SliderAnim {
    private SliderAnim() {
    }

    /**
     * Css scroll barr name
     */
    public static final String SCROLL_BARR = ".scroll-bar";
    /**
     * Css scroll thumb name
     */
    public static final String THUMB = ".thumb";
    /**
     * Css scroll track name
     */
    public static final String TRACK = ".track";
    /**
     * background-color transparent
     */
    public static final String BACK_COLOR_TRANSPARENT = "-fx-background-color: transparent;";
    /**
     * background inserts 0
     */
    public static final String BACK_INSETS_ZERO = "-fx-background-insets: 0;\n";
    /**
     * background radius 0
     */
    public static final String BACK_RADIUS_ZERO = "-fx-background-radius: 0;\n";
    /**
     * padding 0
     */
    public static final String PADDING_ZERO = "-fx-padding: 0;\n";
    /**
     * default css state
     */
    public static final String DEFAULT_CSS = BACK_COLOR_TRANSPARENT + "\n" +
            BACK_RADIUS_ZERO +
            BACK_INSETS_ZERO +
            PADDING_ZERO;

    /**
     * TimeLine for animation
     */
    private static Timeline loop;

    /**
     * Method to initialise scrollPane
     *
     * @param scrollPane scrollPane to anim
     */
    public static void init(ScrollPane scrollPane) {

        Set<Node> nodes = scrollPane.lookupAll(SCROLL_BARR);
        setScrollDeff(nodes);
    }

    private static boolean isAnimating() {
        return loop != null && loop.getStatus() == Animation.Status.RUNNING;
    }

    /**
     * Method to  start scrolling
     *
     * @param scrollPane scrollPane to anim
     */
    public static void scrollStart(ScrollPane scrollPane) {
        Set<Node> nodes = scrollPane.lookupAll(SCROLL_BARR);
        if (isAnimating())
            loop.stop();
        setScrollAfterScroll(nodes);
    }

    /**
     * Method to  finish scrolling
     *
     * @param scrollPane scrollPane to anim
     */
    public static void scrollFinish(ScrollPane scrollPane) {
        Set<Node> nodes = scrollPane.lookupAll(SCROLL_BARR);
        loop = new Timeline(new KeyFrame(Duration.seconds(3), event -> setScrollDeff(nodes)));
        loop.setCycleCount(1);
        loop.playFromStart();
    }


    private static void setScrollAfterScroll(Set<Node> nodes) {
        for (Node node : nodes) {
            node.setStyle(BACK_COLOR_TRANSPARENT +
                    "-fx-background-radius: 1;\n" +
                    "-fx-pref-width: 8px;\n");

            Node nods = node.lookup(THUMB);
            nods.setStyle("-fx-background-color: derive(black, 90%);\n" +
                    "-fx-background-radius: 2em;\n" +
                    "-fx-pref-width: 1px;\n");
            node.setDisable(false);
        }
    }

    private static void setScrollDeff(Set<Node> nodes) {
        for (Node node : nodes) {
            node.setStyle(BACK_COLOR_TRANSPARENT +
                    BACK_RADIUS_ZERO +
                    "-fx-pref-width: 0px;\n");
            Node nods = node.lookup(".increment-arrow");
            nods.setStyle(DEFAULT_CSS);
            nods = node.lookup(THUMB);
            nods.setStyle(BACK_COLOR_TRANSPARENT + "\n" +
                    "-fx-background-radius: 0em;\n" +
                    "-fx-pref-width: 0px;\n");
            nods = node.lookup(".decrement-arrow");
            nods.setStyle(DEFAULT_CSS);
            nods = node.lookup(TRACK);
            nods.setStyle(BACK_COLOR_TRANSPARENT);

        }
    }
}

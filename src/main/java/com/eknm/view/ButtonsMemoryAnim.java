package com.eknm.view;



import javafx.scene.control.Button;
import javafx.scene.paint.Paint;

/**
 * Class to  anim buttons MS M+ M-
 */
public class ButtonsMemoryAnim  {
    private ButtonsMemoryAnim() {
    }

    /**
     * Code of color black
     */
    public static final String COLOR_BLACK = "#000000";
    /**
     * Code of color gray
     */
    public static final String COLOR_GRAY = "#b1b0b0";

    /**
     * Class anim for available buttons
     */
    public static void buttonAvailable(Button button) {
        button.setTextFill(Paint.valueOf(COLOR_BLACK));
        button.getStyleClass().clear();
        button.getStyleClass().add("button");
        button.getStyleClass().add("buttonMS");
    }
    /**
     * Class anim for disable buttons
     */
    public static void buttonDisable(Button button) {
        button.setTextFill(Paint.valueOf(COLOR_GRAY));
        button.getStyleClass().clear();
        button.getStyleClass().add("button");
        button.getStyleClass().add("buttonMSTmp");
    }
}

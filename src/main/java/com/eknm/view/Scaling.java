package com.eknm.view;


import com.eknm.Main;
import com.eknm.controller.Controller;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;


import java.util.List;

/**
 * Class to scale data with resizing
 */
public class Scaling {
    private Scaling() {
    }

    /**
     * Max size of font for label
     */
    public static final int MAX_FONT_SIZE = 46;
    /**
     * Min size of font label
     */
    public static final int MIN_FONT_SIZE = 20;
    /**
     * Max size of font for button with data
     */
    public static final int MAX_SIZE_FOR_BUTTON_WITH_DATA = 30;
    /**
     * Max size of font for button with memory
     */
    public static final int MAX_SIZE_FOR_BUTTON_WITH_M = 23;

    /**
     * Default button with data size
     */
    private static final int DEFAULT_BUTTON_VALUE = 6;
    /**
     * Default button with memory size
     */
    private static final int DEFAULT_M_BUTTON_VALUE = 4;


    /**
     * Method that change size of cells and fonts
     *
     * @param withData   GridPane  all buttons without memory
     * @param withM      GridPane  with memory
     * @param anchorPane AnchorPane of all form
     * @param field      field with data where we input data
     */
    public static void scaleData(GridPane withData, GridPane withM, AnchorPane anchorPane, Label field) {
        double heightScaleWithShift = (Main.scaleHeight * 1.5 - 0.40);
        double coeffOfScaling = 70;
        double coeffOfScalingWithM = 60;
        double coeffOfScalingWithData = 65;
        double indent = coeffOfScalingWithData + coeffOfScaling * heightScaleWithShift;
        double limit = 170;
        double constIndent = 45;

        if (indent > limit) {
            AnchorPane.setTopAnchor(withM, constIndent + coeffOfScalingWithM * heightScaleWithShift);
        } else {
            indent = constIndent * 2 + coeffOfScaling * (Main.scaleHeight);
            AnchorPane.setTopAnchor(withM, coeffOfScalingWithM + coeffOfScaling * (Main.scaleHeight));

        }

        AnchorPane.setTopAnchor(withData, indent);
        AnchorPane.setBottomAnchor(withM, anchorPane.getHeight() - indent);

        int countOfButtonsInOneLine = 6;
        AnchorPane.setRightAnchor(withM, (Main.scaleWidth * Main.MIN_WIDTH) / countOfButtonsInOneLine);

        double coeffFieldShift = 15;
        AnchorPane.setTopAnchor(field, coeffOfScalingWithData + Main.scaleHeight * coeffFieldShift);
        scaleFont(field.getText(), field);

        changeFont(withData.getChildren(), getSizeOfButtonWithData());
        changeFont(withM.getChildren(), getSizeOfButtonWithM());
    }

    private static void changeFont(List<Node> nodes, double size) {
        for (Node n : nodes
        ) {
            Font font = ((Button) n).getFont();
            ((Button) n).setFont(Font.font(font.getName(), size));
        }
    }

    /**
     * Method that  change font of label based on length string
     *
     * @param tmpField string, that will be inside label
     * @param field    field  which  font we will change
     */
    public static void scaleFont(String tmpField, Label field) {

        if (Controller.MAP_OF_EXCEPTION_EQUIVALENTS.entrySet().contains(tmpField)) {
            setInvalidData(field, tmpField);
            return;
        }

        choseSizeForFont(MAX_FONT_SIZE, 0.55, 0.005, field, tmpField.replaceAll("E", "e"));
    }

    /**
     * Method that  change font of label based on length  string with invalid data
     *
     * @param tmpField string, that will be inside label
     * @param field    field  which  font we will change
     */
    public static void setInvalidData(Label field, String tmpField) {
        choseSizeForFont(MAX_FONT_SIZE, 0.6, 0.005, field, tmpField);
    }

    private static void choseSizeForFont(double previousFontSize, double coefficient, double shift, Label field, String tmpField) {
        if (tmpField.length() != 0) {
            while (field.getWidth() <= tmpField.length() * previousFontSize * coefficient) {
                previousFontSize -= shift;
            }
            if (previousFontSize < MIN_FONT_SIZE) {
                previousFontSize = MIN_FONT_SIZE;
            }
        }
        field.setFont(Font.font(field.getFont().getName(), previousFontSize));
        field.setText(tmpField);
    }


    /**
     * Method that change size  fonts
     *
     * @param withData   GridPane  all buttons without memory
     * @param withM      GridPane  with memory
     * @param anchorPane AnchorPane of all form
     * @param field      field with data where we input data
     */
    public static void fontSizeScale(GridPane withData, GridPane withM, AnchorPane anchorPane, Label field,
                                     String tmpField) {
        scaleFont(tmpField, field);
        Scaling.scaleData(withData, withM, anchorPane, field);
    }


    private static double getSizeOfButtonWithM() {
        double coeffScaling = 0.35;
        double shift = 8;
        int res = (int) (DEFAULT_M_BUTTON_VALUE * (Main.scaleHeight * coeffScaling) + shift);

        if (res > MAX_SIZE_FOR_BUTTON_WITH_M)
            res = MAX_SIZE_FOR_BUTTON_WITH_M;
        return res;
    }

    private static double getSizeOfButtonWithData() {
        double shift = 2;
        double coeff = 0.5;
        int res = (int) (DEFAULT_BUTTON_VALUE * (Main.scaleHeight + shift));

        if (res > MAX_SIZE_FOR_BUTTON_WITH_DATA)
            res = MAX_SIZE_FOR_BUTTON_WITH_DATA;
        return shift + coeff * res;
    }

}

package com.eknm;

import com.eknm.controller.Controller;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Labeled;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.loadui.testfx.utils.FXTestUtils;

import java.awt.*;
import java.awt.event.InputEvent;


class RobotTest {

    static final String BUTTON_PERCENT = "buttonPercent";
    static final String BUTTON_SQRT = "buttonSqrt";
    static final String BUTTON_POW_SQUARE = "buttonPowSquare";
    static final String BUTTON_DIVISION_BY_X = "buttonOneDivisionByX";
    static final String BUTTON_CE = "buttonCE";
    static final String BUTTON_C = "buttonC";
    static final String BUTTON_DELL = "buttonDell";
    static final String BUTTON_DIVISION = "buttonDivision";

    static final String BUTTON_SEVEN = "buttonSeven";
    static final String BUTTON_EIGHT = "buttonEight";
    static final String BUTTON_NINE = "buttonNine";

    static final String BUTTON_FOUR = "buttonFour";
    static final String BUTTON_FIVE = "buttonFive";
    static final String BUTTON_SIX = "buttonSix";

    static final String BUTTON_ONE = "buttonOne";
    static final String BUTTON_TWO = "buttonTwo";
    static final String BUTTON_THREE = "buttonThree";

    static final String BUTTON_ZERO = "buttonZero";
    static final String BUTTON_DOT = "buttonDot";

    static final String BUTTON_PLUS_MINUS = "buttonPlusMinus";
    static final String BUTTON_MULTIPLY = "buttonMultiply";
    static final String BUTTON_MINUS = "buttonMinus";
    static final String BUTTON_PLUS = "buttonPlus";
    static final String BUTTON_EQUALLY = "buttonEqually";

    static final String BUTTON_MC = "buttonMC";
    static final String BUTTON_MR = "buttonMR";
    static final String BUTTON_MPLUS = "buttonMPLUS";
    static final String BUTTON_M_MINUS = "buttonMMinus";
    static final String BUTTON_MS = "buttonMS";
    static final String BUTTON_MENU = "buttonMenu";
    static final String field = "field";


    private Robot robot = new Robot();
    private Stage stage;


    public RobotTest(Stage stage) throws AWTException {
        this.stage = stage;
    }

    public void click(Coordinate c) {
        robot.mouseMove((int) c.getX(), (int) c.getY());
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
        robot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            FXTestUtils.awaitEvents();

    }

    public void clickSelector(String selector) {
        click(getCoordinateFromNode(getNodeFromSelector(selector)));
    }

    public void click(String string) {

        for (String strings : string.split(" ")) {
            if (strings.replaceAll("[0123456789,.]", "").length() == 0) {
                for (String s : strings.split("")) {
                    FXTestUtils.awaitEvents();
                    switch (s) {
                        case "0":
                            click(getCoordinateFromNode(getNodeFromSelector(BUTTON_ZERO)));
                            break;
                        case "1":
                            click(getCoordinateFromNode(getNodeFromSelector(BUTTON_ONE)));
                            break;
                        case "2":
                            click(getCoordinateFromNode(getNodeFromSelector(BUTTON_TWO)));
                            break;
                        case "3":
                            click(getCoordinateFromNode(getNodeFromSelector(BUTTON_THREE)));
                            break;
                        case "4":
                            click(getCoordinateFromNode(getNodeFromSelector(BUTTON_FOUR)));
                            break;
                        case "5":
                            click(getCoordinateFromNode(getNodeFromSelector(BUTTON_FIVE)));
                            break;
                        case "6":
                            click(getCoordinateFromNode(getNodeFromSelector(BUTTON_SIX)));
                            break;
                        case "7":
                            click(getCoordinateFromNode(getNodeFromSelector(BUTTON_SEVEN)));
                            break;
                        case "8":
                            click(getCoordinateFromNode(getNodeFromSelector(BUTTON_EIGHT)));
                            break;
                        case "9":
                            click(getCoordinateFromNode(getNodeFromSelector(BUTTON_NINE)));
                            break;
                        case ",":
                            click(getCoordinateFromNode(getNodeFromSelector(BUTTON_DOT)));
                        case ".":
                            click(getCoordinateFromNode(getNodeFromSelector(BUTTON_DOT)));
                            break;
                    }
                }
            } else {
                FXTestUtils.awaitEvents();
                switch (strings) {

                    case "MC":
                        click(getCoordinateFromNode(getNodeFromSelector(BUTTON_MC)));
                        break;
                    case "MR":
                        click(getCoordinateFromNode(getNodeFromSelector(BUTTON_MR)));
                        break;
                    case "M+":
                        click(getCoordinateFromNode(getNodeFromSelector(BUTTON_MPLUS)));
                        break;
                    case "M-":
                        click(getCoordinateFromNode(getNodeFromSelector(BUTTON_M_MINUS)));
                        break;
                    case "MS":
                        click(getCoordinateFromNode(getNodeFromSelector(BUTTON_MS)));
                        break;
                    case "C":
                        click(getCoordinateFromNode(getNodeFromSelector(BUTTON_CE)));
                        break;
                    case "CE":
                        click(getCoordinateFromNode(getNodeFromSelector(BUTTON_CE)));
                        break;
                    case "%":
                        click(getCoordinateFromNode(getNodeFromSelector(BUTTON_PERCENT)));
                        break;
                    case "√":
                        click(getCoordinateFromNode(getNodeFromSelector(BUTTON_SQRT)));
                        break;
                    case "^2":
                        click(getCoordinateFromNode(getNodeFromSelector(BUTTON_POW_SQUARE)));
                        break;
                    case "1/x":
                        click(getCoordinateFromNode(getNodeFromSelector(BUTTON_DIVISION_BY_X)));
                        break;
                    case "dell":
                        click(getCoordinateFromNode(getNodeFromSelector(BUTTON_DELL)));
                        break;
                    case "/":
                        click(getCoordinateFromNode(getNodeFromSelector(BUTTON_DIVISION)));
                        break;
                    case "*":
                        click(getCoordinateFromNode(getNodeFromSelector(BUTTON_MULTIPLY)));
                        break;
                    case "-":
                        click(getCoordinateFromNode(getNodeFromSelector(BUTTON_MINUS)));
                        break;
                    case "+":
                        click(getCoordinateFromNode(getNodeFromSelector(BUTTON_PLUS)));
                        break;
                    case "=":
                        click(getCoordinateFromNode(getNodeFromSelector(BUTTON_EQUALLY)));
                        break;
                    case "±":
                        click(getCoordinateFromNode(getNodeFromSelector(BUTTON_PLUS_MINUS)));
                        break;
                    case "/x":
                        click(getCoordinateFromNode(getNodeFromSelector(BUTTON_DIVISION_BY_X)));
                        break;


                }
            }
        }
    }

    public Labeled getLabeledFromSelector(String selector) {
        return (Labeled) stage.getScene().getRoot().lookup(selector);
    }

    public javafx.scene.control.Button getButtonFromSelector(String selector) {
        return (Button) stage.getScene().getRoot().lookup(selector);
    }

    public javafx.scene.control.Button getButton9() {
        return getButtonFromSelector("#" + BUTTON_NINE);
    }

    public javafx.scene.control.Button getButtonMS() {
        return getButtonFromSelector("#" + BUTTON_MS);
    }


    public Node getNodeFromSelector(String selector) {
        return stage.getScene().getRoot().lookup("#" + selector);
    }

    public Coordinate getCoordinateFromNode(Node node) {
        return new Coordinate(stage.getX() + node.getLocalToSceneTransform().getTx() + node.getBoundsInLocal().getMaxX() / 2,
                stage.getY() + node.getLocalToSceneTransform().getTy() + node.getBoundsInLocal().getMaxY() / 2);
    }

    public String getTextFromLabel() {
        return getLabeledFromSelector("#" + field).getText().replaceAll(" ", "");
    }

    public Labeled getLabel() {
        return getLabeledFromSelector("#" + field);
    }

    public void scale() {
        Controller controller = Controller.getInstance();
        controller.scale();
    }

    public GridPane getGridPane() {
        return (GridPane) getNodeFromSelector("gridPaneWithM");
    }
    public AnchorPane getAnchorPane(){
       return  ( AnchorPane)  stage.getScene().getRoot().lookup("#anchorPane");

    }
public Button getButtonSqrt(){
        return  (Button) getButtonFromSelector("#"+"buttonSqrt");
}


    public void reSize(int x, int y) {
        stage.setWidth(x);
        stage.setHeight(y);
            FXTestUtils.awaitEvents();
            scale();
            FXTestUtils.awaitEvents();
    }

}

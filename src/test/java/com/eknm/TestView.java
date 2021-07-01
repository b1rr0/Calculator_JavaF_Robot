package com.eknm;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.loadui.testfx.utils.FXTestUtils;
import org.testfx.framework.junit.ApplicationTest;

/*
5,00000647e+9983
 1,e+9999
 1,e-14
 1,e-9999
 9,999999999999999e+9999

*/

public class TestView extends ApplicationTest {
    private RobotTest robotTest;


    @Before
    public void setUpClass() throws Exception {
        ApplicationTest.launch(Main.class);

    }


    @Override
    public void start(Stage stage) throws Exception {
        robotTest = new RobotTest(stage);
        stage.setX(0);
        stage.setY(0);
        stage.show();
        sleep(2000);
    }


    @Test
    public void testResizeWithDifferentData() {
        checkButtons("0,000000238472374");
        checkButtons("2");
        checkButtons(com.eknm.Test.MAX_VALUE_MINUS_ONE_MS + " 1 = ");
        checkButtons("299239999999999");
        checkButtons("2 ^2 ^2 ^2 ^2 ^2 ^2 ^2 ^2 ^2 ^2 ^2 ^2 ");
        checkButtons("0,2 ^2 ^2 ^2 ^2 ^2 ^2 ^2 ^2 ^2 ^2 ^2 ^2 ");
        checkButtons("100 / 0 =");

    }


    @Test
    public void shiftTest4() {
        robotTest.reSize(420, 380);

        robotTest.click("CE");

        double lastSize = robotTest.getLabel().getFont().getSize();
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "4");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSize(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSize(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSize(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");

    }


    @Test
    public void shiftTest4F() {
        robotTest.reSize(420, 380);

        robotTest.click("CE");

        double lastSize = robotTest.getLabel().getFont().getSize();
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "0,25");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSize(lastSize, "^2");
        lastSize = checkLabelWithNumbersSize(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSize(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSize(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
    }

    @Test
    public void shiftTest3F() {
        robotTest.reSize(380, 320);

        robotTest.click("CE");
        double lastSize = robotTest.getLabel().getFont().getSize();
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "0,25");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSize(lastSize, "^2");
        lastSize = checkLabelWithNumbersSize(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSize(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSize(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");

    }


    @Test
    public void shiftTest3() {
        robotTest.reSize(380, 320);

        robotTest.click("CE");
        double lastSize = robotTest.getLabel().getFont().getSize();
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "4");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSize(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSize(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSize(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
    }

    @Test
    public void shiftTest2() {
        robotTest.reSize(600, 600);


        robotTest.click("CE");
        double lastSize = robotTest.getLabel().getFont().getSize();
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "4");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");

    }

    @Test
    public void shiftTest2Fractional() {
        robotTest.reSize(600, 600);


        robotTest.click("CE");
        double lastSize = robotTest.getLabel().getFont().getSize();
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "0,25");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
        lastSize = checkLabelWithNumbersSizeEqualse(lastSize, "^2");
    }


    public void checkButtons(String startData) {
        robotTest.reSize(360, 360);
        FXTestUtils.awaitEvents();
        double distanceToGridPlane = checkGridPane(0);
    //    double sizeAnchorPane = checkAnchorPane(0);
        double sizeButton = checkButton(0);
        robotTest.click("CE");
        robotTest.click(startData);

        robotTest.reSize(400, 500);
        FXTestUtils.awaitEvents();
        distanceToGridPlane = checkGridPane(distanceToGridPlane);
      //  sizeAnchorPane = checkAnchorPane(sizeAnchorPane);
        sizeButton = checkButton(sizeButton);

        robotTest.reSize(500, 700);
        FXTestUtils.awaitEvents();
        distanceToGridPlane = checkGridPane(distanceToGridPlane);
      //  sizeAnchorPane = checkAnchorPane(sizeAnchorPane);
        sizeButton = checkButton(sizeButton);


        robotTest.reSize(750, 740);
        sleep(100);
        distanceToGridPlane = checkGridPane(distanceToGridPlane);
     //   sizeAnchorPane = checkAnchorPane(sizeAnchorPane);
        sizeButton = checkButton(sizeButton);

        robotTest.reSize(1000, 800);
        FXTestUtils.awaitEvents();
        distanceToGridPlane = checkGridPane(distanceToGridPlane);
     //   sizeAnchorPane = checkAnchorPane(sizeAnchorPane);
        sizeButton = checkButton(sizeButton);

    }


    public double checkGridPane(double lastSize) {
        Coordinate coordinate = robotTest.getCoordinateFromNode(robotTest.getGridPane());

        Assert.assertTrue(lastSize < coordinate.getX());
        return coordinate.getX();
    }

    public double checkAnchorPane(double lastSize) {
        AnchorPane anchorPane = robotTest.getAnchorPane();

        Assert.assertTrue(lastSize < anchorPane.getScaleX());
        return anchorPane.getScaleX();
    }

    public double checkButton(double lastSize) {
        Button button = robotTest.getButtonSqrt();
        Assert.assertTrue(lastSize < button.getWidth());
        return button.getWidth();
    }

    public double checkLabelWithNumbersSize(double lastSize, String operation) {
        robotTest.click(operation);
        FXTestUtils.awaitEvents();
        Assert.assertTrue(lastSize > robotTest.getLabel().getFont().getSize());
        return robotTest.getLabel().getFont().getSize();
    }

    public double checkLabelWithNumbersSizeEqualse(double lastSize, String operation) {
        robotTest.click(operation);
        FXTestUtils.awaitEvents();
        Assert.assertEquals(lastSize,robotTest.getLabel().getFont().getSize(),0);

        return robotTest.getLabel().getFont().getSize();
    }


}

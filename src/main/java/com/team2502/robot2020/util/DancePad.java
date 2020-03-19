package com.team2502.robot2020.util;

import edu.wpi.first.wpilibj.GenericHID;

public class DancePad extends GenericHID {

    public enum Button {
        upArrow(3),
        downArrow(2),
        rightArrow(4),
        leftArrow(1),

        start(10),
        select(9),

        topRight(7),
        topLeft(8),
        bottomRight(6),
        bottomLeft(5);

        public final int value;

        Button(int value) {
            this.value = value;
        }
    }

    public DancePad(int port) {
        super(port);
    }

    @Override
    public double getX(Hand hand) {
        return 0;
    }

    @Override
    public double getY(Hand hand) {
        return 0;
    }

    /**
     * Whether the Up Arrow button was pressed since the last check.
     *
     * @return Whether the button was pressed since the last check.
     */
    public boolean upArrowPressed() { return getRawButtonPressed(Button.upArrow.value); }

    /**
     * Whether the Down Arrow button was pressed since the last check.
     *
     * @return Whether the button was pressed since the last check.
     */
    public boolean downArrowPressed() { return getRawButtonPressed(Button.downArrow.value); }

    /**
     * Whether the Left Arrow button was pressed since the last check.
     *
     * @return Whether the button was pressed since the last check.
     */
    public boolean leftArrowPressed() { return getRawButtonPressed(Button.leftArrow.value); }

    /**
     * Whether the Right Arrow button was pressed since the last check.
     *
     * @return Whether the button was pressed since the last check.
     */
    public boolean rightArrowPressed() { return getRawButtonPressed(Button.rightArrow.value); }


}

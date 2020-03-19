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
}

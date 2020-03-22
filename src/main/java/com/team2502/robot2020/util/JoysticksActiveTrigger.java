package com.team2502.robot2020.util;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.Trigger;

public class JoysticksActiveTrigger extends Trigger {
    public final Joystick joystick1;
    public final Joystick joystick2;

    public JoysticksActiveTrigger(Joystick joystick1, Joystick joystick2){
        this.joystick1 = joystick1;
        this.joystick2 = joystick2;
    }

    @Override
    public boolean get() {
        return outsideDeadband(joystick1) || outsideDeadband(joystick2);
    }

    public boolean outsideDeadband(Joystick joystick){
        return joystick.getY() > 0.05 || joystick.getY() < -0.05;
    }
}

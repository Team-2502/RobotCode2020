package com.team2502.robot2020.command.dancepad;

import com.team2502.robot2020.subsystem.DrivetrainSubsystem;
import com.team2502.robot2020.util.DancePad;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class DancePadDriveCommand extends CommandBase {
    private final DrivetrainSubsystem drivetrain;
    private final DancePad dancepad;

    private double lastButtonPressTime;

    public DancePadDriveCommand(DrivetrainSubsystem drivetrain, DancePad dancepad){
        this.drivetrain = drivetrain;
        this.dancepad = dancepad;
        addRequirements(drivetrain);
    }

    @Override
    public void execute() {
        if(System.currentTimeMillis() - lastButtonPressTime > 400){
            drivetrain.getDrive().stopMotor();
        }

        if(dancepad.upArrowPressed()){
            drivetrain.getDrive().tankDrive(0.4, 0.4);
            lastButtonPressTime = System.currentTimeMillis();
        }
        else if(dancepad.downArrowPressed()){
            drivetrain.getDrive().tankDrive(-0.4, -0.4);
            lastButtonPressTime = System.currentTimeMillis();
        }
        else if(dancepad.rightArrowPressed()){
            drivetrain.getDrive().tankDrive(0.6, -0.6);
            lastButtonPressTime = System.currentTimeMillis();
        }
        else if(dancepad.leftArrowPressed()){
            drivetrain.getDrive().tankDrive(-0.6, 0.6);
            lastButtonPressTime = System.currentTimeMillis();
        }
    }
}

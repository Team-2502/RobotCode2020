package com.team2502.robot2020.subsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import com.team2502.robot2020.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

// CANSparkMax methods, http://www.revrobotics.com/content/sw/max/sw-docs/java/com/revrobotics/CANSparkMax.html


public class ShooterSubsystem extends SubsystemBase {

    private final CANSparkMax shootRight;
    private final CANSparkMax shootLeft;

    public ShooterSubsystem() {
        //Fix int for Motor ID
        shootLeft = new CANSparkMax(Constants.RobotMap.Motors.SHOOTER_LEFT, CANSparkMaxLowLevel.MotorType.kBrushless);
        shootRight = new CANSparkMax(Constants.RobotMap.Motors.SHOOTER_RIGHT, CANSparkMaxLowLevel.MotorType.kBrushless);

        // Add copy motor, motor follows other.
        //FOllOWS?
        //shootLeft.follow(shootRight);

    }

    //Sets Shooters speed between -1.0 and 1.0
    public void setShooterSpeed(double speed){
        shootLeft.set(-speed);
        shootRight.set(speed);
    }
    //Set Shooters RPM (Possible?)
    public void setShooterRPM(){

    }
    //Stops Shooter
    public void stopShooter(){
        shootRight.set(0);
        shootLeft.set(0);
    }
    //Returns current Shooter Power for both motors
    public void getShooterPower(){
        shootRight.get();
        shootLeft.get();
    }

}

package com.team2502.robot2020.subsystem;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//* CANSparkMax methods, http://www.revrobotics.com/content/sw/max/sw-docs/java/com/revrobotics/CANSparkMax.html


public class ShooterSubsystem extends SubsystemBase {

    private final CANSparkMax shootRight;
    private final CANSparkMax shootLeft;

    public ShooterSubsystem() {
        //Fix int for Motor ID
        shootLeft = new CANSparkMax(0, CANSparkMaxLowLevel.MotorType.kBrushless);
        shootRight = new CANSparkMax(1, CANSparkMaxLowLevel.MotorType.kBrushless);

        // Add copy motor, motor follows other.
        //FOllOWS?
        shootLeft.follows(shootRight);

    }
    //Sets Shooters Power in V
    public void setShooterPower(double power){

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
    public void CANSparkMax getShooterPower(){
        return shootRight;
        return shootLeft;
    }

}

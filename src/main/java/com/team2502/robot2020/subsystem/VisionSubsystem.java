package com.team2502.robot2020.subsystem;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class VisionSubsystem extends SubsystemBase {
    private NetworkTable LIMELIGHT;

    private double TX;
    private double AREA;

    public VisionSubsystem() {
        LIMELIGHT = NetworkTableInstance.getDefault().getTable("limelight-sammy");
    }

    @Override
    public void periodic() {
        NetworkTableEntry TX_ENTRY = LIMELIGHT.getEntry("tx");
        NetworkTableEntry AREA_ENTRY = LIMELIGHT.getEntry("ta");

        TX = TX_ENTRY.getDouble(0.0);
        AREA = AREA_ENTRY.getDouble(0.0);

        SmartDashboard.putNumber("Limelight X", TX);
        SmartDashboard.putNumber("Limelight Area", AREA);
    }

    public double getTX(){
        return TX;
    }

    public double getAREA(){
        return AREA;
    }
}

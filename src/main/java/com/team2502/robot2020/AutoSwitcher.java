package com.team2502.robot2020;

import com.team2502.robot2020.command.ShiftDrivetrainCommand;
import com.team2502.robot2020.command.autonomous.CommandFactory;
import com.team2502.robot2020.command.autonomous.groups.AutonomousCommandGroupFactory;
import com.team2502.robot2020.command.autonomous.ingredients.DoNothingCommand;
import com.team2502.robot2020.command.autonomous.ingredients.DriveStraightCommandNavX;
import com.team2502.robot2020.command.autonomous.ingredients.TurnToAngleCommandNavX;
import com.team2502.robot2020.command.autonomous.ingredients.VoltageDriveCommand;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

public class AutoSwitcher {
    /**
     * The actual sendable containing the autonomi
     */
    private static SendableChooser<AutoMode> autoChooser;

    /**
     * Initialize AutoStartLocationSwitcher#autoChooser, put the enum values in it, and put it on the dashboard
     */
    static void putToSmartDashboard()
    {
        autoChooser = new SendableChooser<>();

        for (int i = 0; i < AutoMode.values().length; i++) {
            AutoMode mode = AutoMode.values()[i];
            if(i == 0) {
                autoChooser.setDefaultOption(mode.name, mode);
            }
            else {
                autoChooser.addOption(mode.name, mode);
            }
        }

        SmartDashboard.putData("Autonomous Chooser", autoChooser);
    }

    /**
     * Get an instance of the autonomous selected
     *
     * @return A new instance of the selected autonomous
     */
    static CommandFactory getAutoInstance() { return autoChooser.getSelected().getCommandFactory(); }

    /**
     * An enum containing all the autonomi the drivers can select from
     */
    public enum AutoMode
    {

        SIMPLE_SHOOT_3_BACKWARDS("Shoot 3 Center and Back Up", AutonomousCommandGroupFactory::Shoot3CenterMoveBackwards),
        SIMPLE_SHOOT_3_FORWARDS("Shoot 3 Center and Go Forwards", AutonomousCommandGroupFactory::Shoot3CenterMoveForwards),
        TRENCH_6_BALL_AUTO("Start Right Trench 6 Ball", AutonomousCommandGroupFactory::Shoot3RightDriveIntake3Trench),
        LEFT_START_RENDEZVOUS_4_BALL("Start Left Rendezvous 4 Ball", AutonomousCommandGroupFactory::Shoot3BackAndShootLeftSideRendezvous),
        TEST_DRIVE_STRAIGHT((d, i, h, v, s) -> new SequentialCommandGroup(
                new TurnToAngleCommandNavX(d, 180),
                new PrintCommand("bananananannanaa"),
                new DriveStraightCommandNavX(d, 0.5)
        )),
        TEST_TURN((d,i,h,v,s) -> new TurnToAngleCommandNavX(d, 22d)),
        TEST_FRICTION_COEFF((d,i,h,v,s) -> new SequentialCommandGroup(
                new InstantCommand(d::enterHighGear, d),
                new VoltageDriveCommand(d, -0.452, 0.452))),
        DO_NOTHING("Do Nothing", DoNothingCommand::new); // always put last


        /**
         * A lambda that creates a new instance of the command
         */
        private final CommandFactory commandFactory;

        /**
         * The name of the command to display on the driver station
         */
        private final String name;

        /**
         * Make a new auto mode that can be selected from
         *
         * @param name           The name of the command
         * @param commandFactory A lambda that can create a new command (usually method reference to constructor)
         */
        AutoMode(String name, CommandFactory commandFactory)
        {
            this.commandFactory = commandFactory;
            this.name = name;
        }

        AutoMode(CommandFactory commandFactory) {
            this.commandFactory = commandFactory;
            this.name = name().replace('_', ' ').toLowerCase();
        }

        AutoMode(String name, SimpleCommandFactory sCf) {
            this(name, simpleToFull(sCf));
        }

        /**
         * @return A new instance of the command (generally runs constructor)
         */
        public CommandFactory getCommandFactory()
        {
            return commandFactory;
        }
    }

    @FunctionalInterface
    private interface SimpleCommandFactory
    {
        CommandBase getInstance();
    }

    private static CommandFactory simpleToFull(SimpleCommandFactory sCF) {
        return (d, i, h, v, s) -> sCF.getInstance();
    }
}

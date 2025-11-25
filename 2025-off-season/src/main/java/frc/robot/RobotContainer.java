// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.Subsystem.Drive.Drive;
import frc.robot.Subsystem.Manipulator.Manipulator;
import frc.robot.Commands.CartesianDriveCmd;
import frc.robot.Commands.SetArmAngleCmd;
import frc.robot.Commands.SpinWheelsCmd;
import frc.robot.Commands.StopArmAngleCmd;
import frc.robot.Commands.StopWheelsCmd;
import frc.robot.Constants.MechConstants;
import frc.robot.Constants.OperatorConstants;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard; 
import frc.robot.Commands.Autonomous.AutoBuilder;

public class RobotContainer {
  private final Drive mDriveSubsystem = new Drive();
  private final Manipulator mManipulatorSubsystem = new Manipulator();

  private final CommandJoystick joystick = new CommandJoystick(OperatorConstants.kControllerPort);

  private SendableChooser<Command> autoChooser = new SendableChooser<>();
  private SendableChooser<DriverStation.Alliance> allianceChooser = new SendableChooser<>();

  public RobotContainer() {
    configureBindings();
    configureAutonomousOptions();

    mDriveSubsystem.setDefaultCommand(new CartesianDriveCmd(mDriveSubsystem,
      () -> joystick.getRawAxis(0),() -> joystick.getRawAxis(1),() -> joystick.getRawAxis(2)));
  }

  private void configureBindings() {
    //Dropper & intake
    joystick.button(OperatorConstants.kSpinWheelsButton).toggleOnTrue(new SpinWheelsCmd(mManipulatorSubsystem, MechConstants.kWheelSpeed));
    joystick.button(OperatorConstants.kStopWheelsButton).toggleOnTrue(new StopWheelsCmd(mManipulatorSubsystem));

    //Arm
    joystick.button(OperatorConstants.kExtendArmButton).toggleOnTrue(new SetArmAngleCmd(mManipulatorSubsystem, MechConstants.kExtendedArmAngle));
    joystick.button(OperatorConstants.kExtendArmButton).toggleOnTrue(new StopArmAngleCmd(mManipulatorSubsystem));

    //Testing / Debug
    joystick.button(OperatorConstants.kTestRotationButton).toggleOnTrue(mManipulatorSubsystem.testRotationCmd(MechConstants.kRotationTestSpeed));
  }

  private void configureAutonomousOptions(){
    allianceChooser.setDefaultOption("Blue", DriverStation.Alliance.Blue);
    allianceChooser.addOption("Red", DriverStation.Alliance.Red);

    var autonBuilder = new AutoBuilder(mDriveSubsystem,mManipulatorSubsystem);

    autoChooser.setDefaultOption("Center",autonBuilder.Center());
    autoChooser.addOption("Left", autonBuilder.Left());
    autoChooser.addOption("Right", autonBuilder.Right());
    autoChooser.addOption("Test", autonBuilder.Test());

    SmartDashboard.putData("Starting Position", autoChooser);
    SmartDashboard.putData("Alliance Chooser", allianceChooser);
  }

  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }
}

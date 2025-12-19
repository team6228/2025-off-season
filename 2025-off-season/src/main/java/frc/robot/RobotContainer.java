// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.Subsystem.Drive.Drive;
import frc.robot.Subsystem.Manipulator.Manipulator;
import frc.robot.Commands.CartesianDriveCmd;
import frc.robot.Commands.FullStopArmCmd;
import frc.robot.Commands.ManuelArmCmd;
import frc.robot.Commands.ResetEncoderCmd;
import frc.robot.Commands.SetArmAngleCmd;
import frc.robot.Commands.SpinWheelsCmd;
import frc.robot.Commands.StopArmAngleCmd;
import frc.robot.Commands.StopWheelsCmd;
import frc.robot.Constants.MechConstants;
import frc.robot.Constants.OperatorConstants;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard; 
import frc.robot.Commands.Autonomous.AutoBuilder;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;

public class RobotContainer {
  private final Drive mDriveSubsystem = new Drive();
  private final Manipulator mManipulatorSubsystem = new Manipulator();

  //private final CommandXboxController joystick = new CommandXboxController(OperatorConstants.kControllerPort);
  private final CommandJoystick joystick = new CommandJoystick(OperatorConstants.kControllerPort);

  private SendableChooser<Command> autoChooser = new SendableChooser<>();
  private SendableChooser<String> joystickChooser = new SendableChooser<>();

  public RobotContainer() {
    SmartDashboard.putData("Joystick type",joystickChooser);
    joystickChooser.addOption("Radiomaster", "Radiomaster");
    joystickChooser.addOption("Logitech", "Logitech");
    joystickChooser.addOption("Xbox", "Xbox");

    configureBindings();
    configureAutonomousOptions();

    /* 
    //Xbox
    mDriveSubsystem.setDefaultCommand(new CartesianDriveCmd(mDriveSubsystem,
      () -> joystick.getRightY(),() -> joystick.getRightX(),() -> joystick.getLeftX()));
    */
     
    //Radiomaster
    mDriveSubsystem.setDefaultCommand(new CartesianDriveCmd(mDriveSubsystem,
      () -> joystick.getRawAxis(1), () -> joystick.getRawAxis(0), () -> joystick.getRawAxis(3)));
    
    /* 
    //Logitech
    mDriveSubsystem.setDefaultCommand(new CartesianDriveCmd(mDriveSubsystem,
      () -> joystick.getRawAxis(1) * -1, () -> joystick.getRawAxis(0), () -> joystick.getRawAxis(2)));
    */
    
  }

  private void configureBindings() {
    /* 
    //Xbox
    //Dropper & intake
    joystick.leftTrigger().toggleOnTrue(new SpinWheelsCmd(mManipulatorSubsystem, MechConstants.kWheelAlgeaSpeed));
    joystick.rightTrigger().toggleOnTrue(new SpinWheelsCmd(mManipulatorSubsystem,MechConstants.kWheelReefSpeed));
    //Arm
    joystick.rightBumper().toggleOnTrue(new SetArmAngleCmd(mManipulatorSubsystem, MechConstants.kExtendedArmAngle));
    joystick.leftBumper().toggleOnTrue(new SetArmAngleCmd(mManipulatorSubsystem, MechConstants.kRetractArmAngle));
    */
    /* 
    //Logitech
    //Dropper & intake
    joystick.button(3).toggleOnTrue(new SpinWheelsCmd(mManipulatorSubsystem, MechConstants.kWheelAlgeaSpeed));
    joystick.button(4).toggleOnTrue(new SpinWheelsCmd(mManipulatorSubsystem,MechConstants.kWheelReefSpeed));

    //Arm
    joystick.button(5).toggleOnTrue(new SetArmAngleCmd(mManipulatorSubsystem, MechConstants.kExtendedArmAngle));
    joystick.button(6).toggleOnTrue(new SetArmAngleCmd(mManipulatorSubsystem, MechConstants.kRetractArmAngle));
    */
     
    //Radiomaster
    joystick.button(2).onTrue(new FullStopArmCmd(mManipulatorSubsystem));
    //Dropper & intake
    joystick.axisGreaterThan(6, 0).whileTrue(new SpinWheelsCmd(mManipulatorSubsystem, MechConstants.kWheelAlgeaSpeed));
    joystick.axisGreaterThan(4, 0).whileTrue(new SpinWheelsCmd(mManipulatorSubsystem,MechConstants.kWheelReefSpeed));

    //------------------[PID]---------------------
    joystick.axisLessThan(5,0).whileTrue(new SetArmAngleCmd(mManipulatorSubsystem, MechConstants.kExtendedArmAngle));
    joystick.axisGreaterThan(5, 0).whileTrue(new SetArmAngleCmd(mManipulatorSubsystem, MechConstants.kRetractArmAngle));
    //-----------------[MANUEL]-------------------
    joystick.axisGreaterThan(7, 0).whileTrue(new ManuelArmCmd(mManipulatorSubsystem, 0.2,-0.5)); //Up
    joystick.axisLessThan(7, 0).whileTrue(new ManuelArmCmd(mManipulatorSubsystem, -0.2,0.6)); //Down
  }

  private void configureAutonomousOptions(){
    var autonBuilder = new AutoBuilder(mDriveSubsystem,mManipulatorSubsystem);

    autoChooser.setDefaultOption("Center",autonBuilder.Center());
    autoChooser.addOption("Left", autonBuilder.Left());
    autoChooser.addOption("Right", autonBuilder.Right());
    autoChooser.addOption("Test", autonBuilder.Test());

    SmartDashboard.putData("Starting Position", autoChooser);
  }

  public Command getAutonomousCommand() {
    return autoChooser.getSelected();
  }
}

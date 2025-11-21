// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.Subsystem.Drive.Drive;
import frc.robot.Subsystem.Manipulator.Manipulator;
import frc.robot.Constants.OperatorConstants;

public class RobotContainer {
  private final Drive mDriveSubsystem = new Drive();
  private final Manipulator mManipulatorSubsystem = new Manipulator();

  private final CommandJoystick joystick = new CommandJoystick(OperatorConstants.kControllerPort);

  public RobotContainer() {
    configureBindings();


  }

  private void configureBindings() {
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}

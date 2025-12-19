package frc.robot.Commands.Autonomous;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Subsystem.Manipulator.Manipulator;
import frc.robot.Constants.AutonConstants;
import frc.robot.Constants.MechConstants;
import frc.robot.Commands.CartesianDriveTimerCmd;
import frc.robot.Commands.CartesianTurnTimerCmd;
import frc.robot.Commands.SpinWheelsCmd;
import frc.robot.Commands.StopWheelsCmd;
import frc.robot.Subsystem.Drive.Drive;

public class AutoBuilder extends Command{
    private final Drive mDrive;
    private final Manipulator mManipulator;

    public AutoBuilder(Drive drive,Manipulator manipulator){
        this.mDrive = drive;
        this.mManipulator = manipulator;

        addRequirements(drive,manipulator);
    }

    public Command Center(){
        return Commands.sequence(
            new CartesianDriveTimerCmd(mDrive, AutonConstants.kStartDriveSpeed, AutonConstants.kStartForwardSeconds),
            new WaitCommand(AutonConstants.kReefCooldown),
            
            new SpinWheelsCmd(mManipulator, MechConstants.kWheelReefSpeed).withTimeout(AutonConstants.kSpinWheelsSeconds),
            
            new StopWheelsCmd(mManipulator)
        );
    }

    public Command Left(){
        return Commands.sequence(
            //First approach
            new CartesianDriveTimerCmd(mDrive, AutonConstants.kLeftDriveSpeed, AutonConstants.kLeftDriveSeconds),
            new WaitCommand(AutonConstants.kReefCooldown),
            //Turn based on alliance
            Commands.either(
                new CartesianTurnTimerCmd(mDrive, AutonConstants.kLeftTurnSpeed, AutonConstants.kLeftTurnSeconds),
                new CartesianTurnTimerCmd(mDrive, -1 * AutonConstants.kLeftTurnSpeed, AutonConstants.kLeftTurnSeconds), 
                () -> DriverStation.getAlliance().orElse(Alliance.Blue) == Alliance.Red
            ),
            new WaitCommand(AutonConstants.kTurnCooldown),
            //Final approach
            new CartesianDriveTimerCmd(mDrive, AutonConstants.kLeftFinalSpeed, AutonConstants.kLeftFinalSeconds),

            new WaitCommand(AutonConstants.kReefCooldown),
            new SpinWheelsCmd(mManipulator, MechConstants.kWheelReefSpeed).withTimeout(AutonConstants.kSpinWheelsSeconds),
            new StopWheelsCmd(mManipulator)
        );
    }

    public Command Right(){
        return Commands.sequence(
            //First approach
            new CartesianDriveTimerCmd(mDrive, AutonConstants.kRightDriveSpeed, AutonConstants.kRightDriveSeconds),
            new WaitCommand(AutonConstants.kReefCooldown),
            //Turn based on alliance
            Commands.either(
                new CartesianTurnTimerCmd(mDrive, AutonConstants.kRightTurnSpeed, AutonConstants.kRightTurnSeconds),
                new CartesianTurnTimerCmd(mDrive, -1 * AutonConstants.kRightTurnSpeed, AutonConstants.kRightTurnSeconds), 
                () -> DriverStation.getAlliance().orElse(Alliance.Blue) == Alliance.Red
            ),
            new WaitCommand(AutonConstants.kTurnCooldown),
            //Final approach
            new CartesianDriveTimerCmd(mDrive, AutonConstants.kRightFinalSpeed, AutonConstants.kRightFinalSeconds),

            new WaitCommand(AutonConstants.kReefCooldown),
            new SpinWheelsCmd(mManipulator, MechConstants.kWheelReefSpeed).withTimeout(AutonConstants.kSpinWheelsSeconds),
            new StopWheelsCmd(mManipulator)
        );
    }

    public Command Test(){
        return Commands.sequence(
            Commands.either(
                Commands.print("Red Test"),
                Commands.print("Blue Test"), 
                () -> DriverStation.getAlliance().orElse(Alliance.Blue) == Alliance.Red
            ),

            new CartesianDriveTimerCmd(mDrive, 0.5, 2.),

            new SpinWheelsCmd(mManipulator, 0.5)
        );
    }

    @Override
    public void initialize(){}

    @Override
    public void execute(){
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return false;
    }
}

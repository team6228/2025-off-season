package frc.robot.Commands.Autonomous;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.Subsystem.Manipulator.Manipulator;
import frc.robot.Constants.AutonConstants;
import frc.robot.Constants.MechConstants;
import frc.robot.Commands.CartesianDriveTimerCmd;
import frc.robot.Commands.CartesianTurnTimer;
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
            
            new SpinWheelsCmd(mManipulator, MechConstants.kWheelSpeed).withTimeout(AutonConstants.kSpinWheelsSeconds),
            
            new StopWheelsCmd(mManipulator)
        );
    }

    public Command Left(){
        return Commands.sequence(
            //First approach
            new CartesianDriveTimerCmd(mDrive, AutonConstants.kLeftDriveSpeed, AutonConstants.kLeftDriveSeconds),
            //Turn
            new CartesianTurnTimer(mDrive, AutonConstants.kLeftTurnSpeed, AutonConstants.kLeftTurnSeconds),
            //Final approach
            new CartesianDriveTimerCmd(mDrive, AutonConstants.kLeftFinalSpeed, AutonConstants.kLeftFinalSeconds),

            new SpinWheelsCmd(mManipulator, MechConstants.kWheelSpeed).withTimeout(AutonConstants.kSpinWheelsSeconds),
            new StopWheelsCmd(mManipulator)
        );
    }

    public Command Right(){
        return Commands.sequence(
            //First approach
            new CartesianDriveTimerCmd(mDrive, AutonConstants.kRightDriveSeconds, AutonConstants.kRightDriveSeconds),
            //Turn
            new CartesianTurnTimer(mDrive, AutonConstants.kRightTurnSpeed, AutonConstants.kRightTurnSeconds),
            //Final approach
            new CartesianDriveTimerCmd(mDrive, AutonConstants.kRightFinalSpeed, AutonConstants.kRightFinalSeconds),

            new SpinWheelsCmd(mManipulator, MechConstants.kWheelSpeed).withTimeout(AutonConstants.kSpinWheelsSeconds),
            new StopWheelsCmd(mManipulator)
        );
    }

    public Command Test(){
        return Commands.sequence(
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

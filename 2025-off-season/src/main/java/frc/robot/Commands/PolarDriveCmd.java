package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystem.Drive.Drive;

public class PolarDriveCmd extends Command{
    private final Drive mDriveSubsystem;
    //[TODO] get the needed joystick values

    public PolarDriveCmd(Drive DriveSubsystem){
        this.mDriveSubsystem = DriveSubsystem;
        
        addRequirements(DriveSubsystem);
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

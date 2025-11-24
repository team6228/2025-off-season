package frc.robot.Commands;


import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystem.Drive.Drive;

public class PolarDriveTimerCmd extends Command{
    private final Drive mDriveSubsystem;
    private final Double mSpeed;
    private final Double mSeconds;
    private final Timer timer = new Timer();

    public PolarDriveTimerCmd(Drive DriveSubsystem,Double speed,Double seconds){
        this.mDriveSubsystem = DriveSubsystem;
        this.mSpeed = speed;
        this.mSeconds = seconds;
        
        addRequirements(DriveSubsystem);
    }

    @Override
    public void initialize(){}

    @Override
    public void execute(){
        //[TODO] really cuh?
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return false;
    }
}

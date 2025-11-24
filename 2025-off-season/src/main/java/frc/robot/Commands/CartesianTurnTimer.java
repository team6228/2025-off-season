package frc.robot.Commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystem.Drive.Drive;

public class CartesianTurnTimer extends Command{
    private final Drive mDriveSubsystem;
    private final Double mSpeed;
    private final Double mSeconds;
    private final Timer timer = new Timer();

    public CartesianTurnTimer(Drive DriveSubsystem,Double speed,Double seconds){
        this.mDriveSubsystem = DriveSubsystem;
        this.mSpeed = speed;
        this.mSeconds = seconds;
        
        addRequirements(DriveSubsystem);

        //Do i really need to add this?
        timer.reset();
    }

    @Override
    public void initialize(){
        timer.restart();
    }

    @Override
    public void execute(){
        //[TODO] be sure that xSpeed is forward.Wallahi wpilib docs be quircky at night
        if(timer.get() < mSeconds)
            mDriveSubsystem.driveCartesian(0., 0.,mSpeed);
        else if(timer.get() > mSeconds)
            mDriveSubsystem.stopMotors();
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return false;
    }
}

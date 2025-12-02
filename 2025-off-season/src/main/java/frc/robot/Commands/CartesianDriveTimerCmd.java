package frc.robot.Commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystem.Drive.Drive;

public class CartesianDriveTimerCmd extends Command{
    private final Drive mDrive;
    private final Double mSpeed;
    private final Double mSeconds;
    private final Timer timer = new Timer();

    public CartesianDriveTimerCmd(Drive DriveSubsystem,Double speed,Double seconds){
        this.mDrive = DriveSubsystem;
        this.mSpeed = speed;
        this.mSeconds = seconds;
        
        addRequirements(DriveSubsystem);

        SmartDashboard.putNumber("Strafe Correction", 0);
        SmartDashboard.putNumber("Rotation Correction", 0);
    }

    @Override
    public void initialize(){
        timer.restart();
    }

    @Override
    public void execute(){
        //[TODO] be sure that xSpeed is forward.Wallahi wpilib docs be quircky at night
        if(timer.get() < mSeconds){
            double accelX = mDrive.accel.getX();   
            double accelZ = mDrive.accel.getZ();   

            double kStrafe = 0.5;  
            double kRotate = 0.25;

            double strafeCorrection   = -accelX * kStrafe; 
            double rotationCorrection = -accelZ * kRotate;  

            SmartDashboard.putNumber("Strafe Correction", strafeCorrection);
            SmartDashboard.putNumber("Rotation Correction", rotationCorrection);

            mDrive.driveCartesian(mSpeed, strafeCorrection, rotationCorrection);
        }else if(timer.get() > mSeconds)
            mDrive.stopMotors();
    }

    @Override
    public void end(boolean interrupted){
        mDrive.stopMotors();
    }

    @Override
    public boolean isFinished(){
        return timer.get() >= mSeconds;
    }
}

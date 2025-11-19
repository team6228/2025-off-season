package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystem.Drive.Drive;

public class CartesianDriveCmd extends Command{
    private final Drive mDriveSubsystem;
    private final Supplier<Double> mXSpeedSupplier,mYSpeedSupplier,mZRotationSupplier;

    public CartesianDriveCmd(Drive DriveSubsystem,Supplier<Double> xSpeedSupplier,
            Supplier<Double> ySpeedSupplier,Supplier<Double> zRotationSupplier){
        this.mDriveSubsystem = DriveSubsystem;
        this.mXSpeedSupplier = xSpeedSupplier;
        this.mYSpeedSupplier = ySpeedSupplier;
        this.mZRotationSupplier = zRotationSupplier;
        
        addRequirements(DriveSubsystem);
    }

    @Override
    public void initialize(){}

    @Override
    public void execute(){
        Double xSpeed = mXSpeedSupplier.get();
        Double ySpeed = mYSpeedSupplier.get();
        Double zRotation = mZRotationSupplier.get();

        mDriveSubsystem.driveCartesian(xSpeed, ySpeed, zRotation);
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return false;
    }
}

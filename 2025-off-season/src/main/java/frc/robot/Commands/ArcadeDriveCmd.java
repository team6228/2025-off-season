package frc.robot.Commands;

import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystem.Drive.Drive;

public class ArcadeDriveCmd extends Command{
    private final Drive mDriveSubsystem;
    private final Supplier<Double> mFwdSupplier,mRotSupplier;

    public ArcadeDriveCmd(Drive DriveSubsystem,Supplier<Double> fwdFSupplier,Supplier<Double> rotSupplier){
        this.mDriveSubsystem = DriveSubsystem;
        this.mFwdSupplier = fwdFSupplier;
        this.mRotSupplier = rotSupplier;

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

package frc.robot.Commands;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.drive.DifferentialDrive.WheelSpeeds;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystem.Manipulator.Manipulator;

public class ManuelArmCmd extends Command{
    private Manipulator mManipulatorSubsystem;
    private Double mAngleSpeed,mWheelSpeed;

    public ManuelArmCmd(Manipulator manipulatorSubsystem,Double angleSpeed,Double wheelSpeed){
        this.mManipulatorSubsystem = manipulatorSubsystem;
        this.mAngleSpeed = angleSpeed;
        this.mWheelSpeed = wheelSpeed;

        addRequirements(manipulatorSubsystem);
    }

    @Override
    public void initialize(){
    }

    @Override
    public void execute(){
        mManipulatorSubsystem.manuelControl(-mAngleSpeed);
        mManipulatorSubsystem.spinWheels(mWheelSpeed);
    }

    @Override
    public void end(boolean interrupted){
        mManipulatorSubsystem.stopRotation();
        mManipulatorSubsystem.stopWheels();
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}

package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystem.Manipulator.Manipulator;

public class SpinWheelsCmd extends Command{
    private Manipulator mManipulatorSubsystem;
    private Double mSpeed;

    public SpinWheelsCmd(Manipulator manipulatorSubsystem,Double speed){
        this.mManipulatorSubsystem = manipulatorSubsystem;
        this.mSpeed = speed;

        addRequirements(manipulatorSubsystem);
    }

    @Override
    public void initialize(){}

    @Override
    public void execute(){
        mManipulatorSubsystem.spinWheels(mSpeed);
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return false;
    }
}

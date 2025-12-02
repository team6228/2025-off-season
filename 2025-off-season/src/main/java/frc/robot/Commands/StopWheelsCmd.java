package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystem.Manipulator.Manipulator;

public class StopWheelsCmd extends Command{
    private Manipulator mManipulatorSubsystem;

    public StopWheelsCmd(Manipulator manipulatorSubsystem){
        this.mManipulatorSubsystem = manipulatorSubsystem;

        addRequirements(manipulatorSubsystem);
    }

    @Override
    public void initialize(){}

    @Override
    public void execute(){
        mManipulatorSubsystem.stopWheels();
    }

    @Override
    public void end(boolean interrupted){
        mManipulatorSubsystem.stopWheels();
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}

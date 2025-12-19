package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystem.Manipulator.Manipulator;

public class StopArmAngleCmd extends Command{
    private Manipulator mManipulatorSubsystem;

    public StopArmAngleCmd(Manipulator manipulatorSubsystem){
        this.mManipulatorSubsystem = manipulatorSubsystem;

        addRequirements(manipulatorSubsystem);
    }

    @Override
    public void initialize(){}

    @Override
    public void execute(){
        mManipulatorSubsystem.stopRotation();
    }

    @Override
    public void end(boolean interrupted){
        mManipulatorSubsystem.stopRotation();
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}

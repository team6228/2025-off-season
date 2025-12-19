package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystem.Manipulator.Manipulator;

public class FullStopArmCmd extends Command{
    private Manipulator mManipulatorSubsystem;

    public FullStopArmCmd(Manipulator manipulatorSubsystem){
        this.mManipulatorSubsystem = manipulatorSubsystem;

        addRequirements(manipulatorSubsystem);
    }

    @Override
    public void initialize(){}

    @Override
    public void execute(){
        mManipulatorSubsystem.fullStop();
        System.out.println("STOPY STOP STOP");
    }

    @Override
    public void end(boolean interrupted){
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}

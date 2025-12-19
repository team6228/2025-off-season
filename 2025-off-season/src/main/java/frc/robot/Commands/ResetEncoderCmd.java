package frc.robot.Commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystem.Manipulator.Manipulator;

public class ResetEncoderCmd extends Command{
    private final Manipulator mManipulator;

    public ResetEncoderCmd(Manipulator manipulator){
        this.mManipulator = manipulator;

        addRequirements(manipulator);
    }

    @Override
    public void initialize(){
        mManipulator.resetEncoder();
    }

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

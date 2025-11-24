package frc.robot.Commands;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystem.Manipulator.Manipulator;

public class SetArmAngleCmd extends Command{
    private Manipulator mManipulatorSubsystem;
    private Double mPosition;

    public SetArmAngleCmd(Manipulator manipulatorSubsystem,Double position){
        this.mManipulatorSubsystem = manipulatorSubsystem;
        this.mPosition = position;

        addRequirements(manipulatorSubsystem);
    }

    @Override
    public void initialize(){
    }

    @Override
    public void execute(){
        mManipulatorSubsystem.setAngle(Units.degreesToRadians(mPosition));
    }

    @Override
    public void end(boolean interrupted){}

    @Override
    public boolean isFinished(){
        return false;
    }
}

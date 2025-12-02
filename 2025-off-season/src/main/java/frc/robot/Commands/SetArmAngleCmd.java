package frc.robot.Commands;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Subsystem.Manipulator.Manipulator;

public class SetArmAngleCmd extends Command{
    private Manipulator mManipulatorSubsystem;
    private Double mAngle;

    public SetArmAngleCmd(Manipulator manipulatorSubsystem,Double angle){
        this.mManipulatorSubsystem = manipulatorSubsystem;
        this.mAngle = angle;

        addRequirements(manipulatorSubsystem);
    }

    @Override
    public void initialize(){
        mManipulatorSubsystem.setSetpoint(mAngle);
    }

    @Override
    public void execute(){
        mManipulatorSubsystem.calculate();
    }

    @Override
    public void end(boolean interrupted){
        mManipulatorSubsystem.stopWheels();
        mManipulatorSubsystem.resetPid();
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}

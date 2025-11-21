package frc.robot.Subsystem.Manipulator;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Manipulator extends SubsystemBase{
    private final SparkMax armRotationMotor = new SparkMax(ManipulatorConstants.kArmRotationMotorCanID, 
        ManipulatorConstants.kArmRotationMotorType);
    private final SparkMax spinMotor = new SparkMax(ManipulatorConstants.kSpinMotorCanID, 
        ManipulatorConstants.kSpinMotorType);

    private final Encoder encoder = new Encoder(
        ManipulatorConstants.kEncoderDIOPorts[0],
        ManipulatorConstants.kEncoderDIOPorts[1],
        ManipulatorConstants.kEncoderReversed);

    private final ProfiledPIDController pidController = new ProfiledPIDController(
        ManipulatorConstants.kP,
        ManipulatorConstants.kI,
        ManipulatorConstants.kD,
        new TrapezoidProfile.Constraints(ManipulatorConstants.kMaxVelocity, 
            ManipulatorConstants.kMaxAcceleration));

    ArmFeedforward feedforwardController =
      new ArmFeedforward(
          ManipulatorConstants.kS,
          ManipulatorConstants.kG,
          ManipulatorConstants.kV,
          ManipulatorConstants.kA);

    public Manipulator(){
        encoder.reset();
        encoder.setDistancePerPulse(ManipulatorConstants.kDistancePerPulse);
    }

    @Override
    public void periodic(){

    }

    public void setAngle(double angle){
        pidController.setGoal(Units.degreesToRadians(angle));
        double pidOutput = pidController.calculate(encoder.getDistance());
        //double ffOutput = feedforwardController.calculate(encoder.getDistance(),pidController.getSetpoint().velocity);

        //rotationSpark.setVoltage(pidOutput);
        //armRotationMotor.setVoltage(pidOutput + ffOutput);
        armRotationMotor.setVoltage(pidOutput);
    }

    public void spinWheels(double speed){
        spinMotor.set(speed);
    }

    public void stopRotation(){
        armRotationMotor.stopMotor();
    }

    public void stopWheels(){
        spinMotor.stopMotor();
    }
}

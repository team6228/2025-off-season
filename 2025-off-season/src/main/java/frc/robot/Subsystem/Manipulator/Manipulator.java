package frc.robot.Subsystem.Manipulator;

import javax.lang.model.util.ElementScanner14;

import com.revrobotics.spark.SparkMax;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.ArmFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
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

    private final PIDController pidController = new PIDController(
        ManipulatorConstants.kP,
        ManipulatorConstants.kI,
        ManipulatorConstants.kD);

    private double setpointAngle = 0;

    //Debug
    double pidOutput;

    /* 
    private final ProfiledPIDController pidController = new ProfiledPIDController(
        ManipulatorConstants.kP,
        ManipulatorConstants.kI,
        ManipulatorConstants.kD,
        new TrapezoidProfile.Constraints(ManipulatorConstants.kMaxVelocity, 
            ManipulatorConstants.kMaxAcceleration));
    */

    ArmFeedforward feedforwardController =
      new ArmFeedforward(
          ManipulatorConstants.kS,
          ManipulatorConstants.kG,
          ManipulatorConstants.kV,
          ManipulatorConstants.kA);

    public Manipulator(){
        encoder.reset();
        encoder.setDistancePerPulse(ManipulatorConstants.kDistancePerPulse);


        //pidController.setTolerance(2.5);
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("encoder reading", encoderReading());
        SmartDashboard.putNumber("Pid value", pidOutput);
        SmartDashboard.putNumber("Target", pidController.getSetpoint());

        if(encoderReading() > ManipulatorConstants.kSoftLimitUp || encoderReading() < ManipulatorConstants.kSoftLimitDown){
            pidController.close();
            stopRotation();
        }
    }

    public Command testRotationCmd(double speed){
        return this.run(() -> testRotation(speed));
    }

    public Command getToAngleCmd(double angle){
        return this.run(() -> getToAngle(angle));
    }

    public void resetEncoder(){
        encoder.reset();
    }

    public void setSetpoint(double angle){
        setpointAngle = angle;
        pidController.setSetpoint(angle);
        //pidController.setGoal(angle);
    }

    public void calculate(){
        //double pidOutput = pidController.calculate(encoderReading());
        //pidOutput = pidController.calculate(encoderReading());
        pidOutput = MathUtil.clamp(pidController.calculate(encoderReading()), -1.0, 1.0);

        //double ffOutput = feedforwardController.calculate(encoderReading(),pidController.getSetpoint().velocity);

        if (encoderReading() > 25){
            System.out.println("Stop");
            spinMotor.set(0.0);
        }else if (encoderReading() > -15 && encoderReading() < 25 && pidOutput > 0.001){
            System.out.println("Up");
            spinMotor.set(-0.5); 
        }else if (encoderReading() > -20 && encoderReading() < 25 &&  pidOutput < 0.001){
            System.out.println("Down");
            spinMotor.set(0.6);
        }else{
            spinMotor.set(0.0);
        }

        //rotationSpark.setVoltage(pidOutput);
        //armRotationMotor.setVoltage(pidOutput + ffOutput);

        armRotationMotor.set( -1.0 * pidOutput );

        //armRotationMotor.set(-pidOutput);
    }

    public void fullStop(){
        spinMotor.set(0.0);
        armRotationMotor.set(0.0);
        pidController.close();
        armRotationMotor.stopMotor();
        spinMotor.stopMotor();
        armRotationMotor.disable();
        spinMotor.disable();
    }

    public void manuelControl(double speed){
        armRotationMotor.set(-speed);
    }

    public void getToAngle(double angle){
        if(encoderReading() < angle){
            armRotationMotor.set(-0.6);
        }else if(encoderReading() > angle){
            armRotationMotor.set(0);
        }
    }

    public void spinWheels(double speed){
        spinMotor.set(-speed);
    }

    public void stopRotation(){
        armRotationMotor.stopMotor();
    }

    public void stopWheels(){
        spinMotor.stopMotor();
    }

    public void testRotation(double speed){
        armRotationMotor.set(-speed);
    }

    public void resetPid(){
        pidController.reset();
    }

    public double encoderReading(){
        return encoder.getDistance() - ManipulatorConstants.kEncoderOffset;
    }
}

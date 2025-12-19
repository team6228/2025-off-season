package frc.robot.Subsystem.Drive;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase{
    //[TODO] implement april tag follow
    private final VictorSP frontLeftMotor = new VictorSP(DriveConstants.frontLeftMotorPWMPort);
    private final VictorSP frontRightMotor = new VictorSP(DriveConstants.frontRightMotorPWMPort);
    private final VictorSP rearLeftMotor = new VictorSP(DriveConstants.rearLeftMotorPWMPort);
    private final VictorSP rearRightMotor = new VictorSP(DriveConstants.rearRightMotorPWMPort);

    private final MecanumDrive robotDrive = new MecanumDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);

    public final BuiltInAccelerometer accel = new BuiltInAccelerometer();

    private final NetworkTable visionTable = NetworkTableInstance.getDefault().getTable("Vision");

    public Drive(){
        frontLeftMotor.setInverted(DriveConstants.frontLeftMotorReversed);
        frontRightMotor.setInverted(DriveConstants.frontRightMotorReversed);
        rearLeftMotor.setInverted(DriveConstants.rearLeftMotorReversed);
        rearRightMotor.setInverted(DriveConstants.rearRightMotorReversed);
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("accelX", accel.getX());
        SmartDashboard.putNumber("accelY", accel.getY());
        SmartDashboard.putNumber("accelZ", accel.getZ());

        SmartDashboard.putNumber("yaw", visionTable.getEntry("yaw").getDouble(0));
        SmartDashboard.putNumber("yaw", visionTable.getEntry("pitch").getDouble(0));
        SmartDashboard.putNumber("yaw", visionTable.getEntry("roll").getDouble(0));
    }

    //[TODO] How dose polar drive even work bruh?
    public void drivePolar(Double magnitued,Double angle,Double zRotation){
        robotDrive.drivePolar(magnitued,Rotation2d.fromDegrees(angle),zRotation);
    }

    //[TODO] Make sure arg names are right
    //Speed vars names match coordinates not joystick axis 
    public void driveCartesian(Double xSpeed,Double ySpeed,Double zRotation){
        SmartDashboard.putNumber("xSpeed", xSpeed);
        SmartDashboard.putNumber("ySpeed", ySpeed);
        SmartDashboard.putNumber("zRotation", zRotation);

        robotDrive.driveCartesian(xSpeed*DriveConstants.xAxisCoefficient, 
            ySpeed*DriveConstants.yAxisCoefficient, zRotation*DriveConstants.zAxisCoefficient);
    }

    public void driveArcade(Double fwd,Double Rot){
        //[TODO] Could implement a custom tank drive
        return;
    }

    public void brakeMotors(){
        //[TODO] Dont make it fly cuh
        robotDrive.driveCartesian(-0.2, 0, 0);
    }

    public void stopMotors(){
        frontLeftMotor.set(0.0);
        frontRightMotor.set(0.0);;
        rearLeftMotor.set(0.0);;
        rearRightMotor.set(0.0);;
    }

    public Command frontLeftMotorCmd(){
        return run(() -> frontLeftMotor.set(0.75));
    }
    public Command frontRightMotorCmd(){
        return run(() -> frontRightMotor.set(0.75));
    }
    public Command rearLeftMotorCmd(){
        return run(() -> rearLeftMotor.set(0.75));
    }
    public Command rearRightMotorCmd(){
        return run(() -> rearRightMotor.set(0.75));
    }
}

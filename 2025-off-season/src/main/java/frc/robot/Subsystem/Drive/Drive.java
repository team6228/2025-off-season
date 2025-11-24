package frc.robot.Subsystem.Drive;

import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.MecanumDriveKinematics;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase{
    private final VictorSP frontLeftMotor = new VictorSP(DriveConstants.frontLeftMotorPWMPort);
    private final VictorSP frontRightMotor = new VictorSP(DriveConstants.frontRightMotorPWMPort);
    private final VictorSP rearLeftMotor = new VictorSP(DriveConstants.rearLeftMotorPWMPort);
    private final VictorSP rearRightMotor = new VictorSP(DriveConstants.rearRightMotorPWMPort);

    private final MecanumDrive robotDrive = new MecanumDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);

    //[TODO] Change the locations.These are example values
    private final Translation2d frontLeftLocation = new Translation2d(0.381, 0.381);
    private final Translation2d frontRightLocation = new Translation2d(0.381, -0.381);
    private final Translation2d backLeftLocation = new Translation2d(-0.381, 0.381);
    private final Translation2d backRightLocation = new Translation2d(-0.381, -0.381);

    MecanumDriveKinematics m_kinematics = new MecanumDriveKinematics(frontLeftLocation, frontRightLocation, 
        backLeftLocation, backRightLocation);

    public Drive(){
    }

    @Override
    public void periodic(){
    }

    //[TODO] How dose polar drive even work bruh?
    public void drivePolar(Double magnitued,Double angle,Double zRotation){
        robotDrive.drivePolar(magnitued,Rotation2d.fromDegrees(angle),zRotation);
    }

    //[TODO] Make sure arg names are right
    //Speed vars names match coordinates not joystick axis 
    public void driveCartesian(Double xSpeed,Double ySpeed,Double zRotation){
        robotDrive.driveCartesian(xSpeed*DriveConstants.xAxisCoefficient, 
            ySpeed*DriveConstants.yAxisCoefficient, zRotation*DriveConstants.zAxisCoefficient);
    }

    public void driveArcade(Double fwd,Double Rot){
        //[TODO] Could implement a custom tank drive
        return;
    }

    public void stopMotors(){
        frontLeftMotor.stopMotor();
        frontRightMotor.stopMotor();
        rearLeftMotor.stopMotor();
        rearRightMotor.stopMotor();
    }
}

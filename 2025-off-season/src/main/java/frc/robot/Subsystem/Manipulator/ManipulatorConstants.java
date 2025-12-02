package frc.robot.Subsystem.Manipulator;

import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.util.Units;

public class ManipulatorConstants{
    public static final int kArmRotationMotorCanID = 4;
    public static final MotorType kArmRotationMotorType = MotorType.kBrushed;
    public static final boolean kArmRotationMotorReversed = false;

    public static final int kSpinMotorCanID = 5;
    public static final MotorType kSpinMotorType = MotorType.kBrushed;
    public static final boolean kSpinMotorReversed = false;

    public static final int[] kEncoderDIOPorts = {0,1};
    public static final boolean kEncoderReversed = true;

    public static final double kEncoderCPR = 1024.;
    public static final double kPulleyDiameter = 6. / 1; //In meters
    //public static final double kDistancePerPulse = 8.0 * Math.PI / (kEncoderCPR * 4);
    public static final double kDistancePerPulse =
        ((Math.PI * 8) / (kEncoderCPR * 4)) * (180.0 / Math.PI);
    public static final double kAnglePerPulse = 360.0 / kEncoderCPR;

    public static final double kEncoderOffset = 5.;

    public static final double kSoftLimitUp = 90;
    public static final double kSoftLimitDown = -15;

    public static final double kP = 0.0200;
    public static final double kI = 0.0000;
    public static final double kD = 0.0005;

    public static final double kErrorTolerance = 5;

    public static final double kMaxVelocity = Units.degreesToRadians(0); 
    public static final double kMaxAcceleration = Units.degreesToRadians(0); 

    public static final double kS = 0.0;    
    public static final double kG = 0.0; 
    public static final double kV = 0.0; 
    public static final double kA = 0.0; 
}

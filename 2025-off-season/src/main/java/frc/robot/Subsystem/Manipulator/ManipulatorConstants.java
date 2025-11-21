package frc.robot.Subsystem.Manipulator;

import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.util.Units;

public class ManipulatorConstants{
    public static final int kArmRotationMotorPWMPort = 4;
    public static final int kArmRotationMotorCanID = 1;
    public static final MotorType kArmRotationMotorType = MotorType.kBrushless;
    public static final boolean kArmRotationMotorReversed = false;

    public static final int kSpinMotorPWMPort = 5;
    public static final int kSpinMotorCanID = 2;
    public static final MotorType kSpinMotorType = MotorType.kBrushless;
    public static final boolean kSpinMotorReversed = false;

    public static final int[] kEncoderDIOPorts = {0,1};
    public static final boolean kEncoderReversed = true;

    public static final double kEncoderCPR = 1024;
    public static final double kDistancePerPulse = 2.0 * Math.PI / (kEncoderCPR * 4);

    public static final double kP = 1.00;
    public static final double kI = 0.00;
    public static final double kD = 0.00;

    public static final double kMaxVelocity = Units.degreesToRadians(0); 
    public static final double kMaxAcceleration = Units.degreesToRadians(0); 

    public static final double kS = 0.0;    
    public static final double kG = 0.0; 
    public static final double kV = 0.0; 
    public static final double kA = 0.0; 
}

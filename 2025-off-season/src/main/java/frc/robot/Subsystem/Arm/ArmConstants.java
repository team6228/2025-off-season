package frc.robot.Subsystem.Arm;

import com.revrobotics.spark.SparkLowLevel.MotorType;

import edu.wpi.first.math.util.Units;

public class ArmConstants{
    //[TODO] Make sure of the values
    public static final int kRotationMotorPWMPort = 4;
    public static final int kRotationMotorCanID = 1;
    public static final boolean kRotationMotorReversed = false;
    public static final MotorType kRotationMotorType = MotorType.kBrushed;

    //[TODO] Could combine arm and dropper hold on
    public static final int kIntakeMotorPWMPort = 5;
    public static final int kIntakeMotorCanID = 2;
    public static final boolean kIntakeMotorReversed = false;
    public static final MotorType kIntakeMotorType = MotorType.kBrushed;

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

package frc.robot;

public class Constants {
    public static class OperatorConstants{
        public static final int kControllerPort = 0;

        public static final int kSpinWheelsButton = 3;
        public static final int kStopWheelsButton = 4;
        public static final int kExtendArmButton = 5;
        public static final int kRetractButton = 6;

        public static final int kTestRotationButton = 8;
    }

    public static class MechConstants{
        public static final double kWheelReefSpeed = -0.467;
        public static final double kWheelAlgeaSpeed = 0.67;

        public static final double kExtendedArmAngle = 75;
        public static final double kRetractArmAngle = -5;

        public static final double kRotationTestSpeed = 0.5;
    }

    public static class AutonConstants{
        public static final double kSpinWheelsSeconds = 1;

        public static final double kLeftTurnSpeed = 0.5;
        
        public static final double kRightTurnSpeed = -0.5;

        public static final double kLeftTurnSeconds = .5;
        public static final double kRightTurnSeconds = .5;

        public static final double kStartDriveSpeed = 0.35;

        public static final double kStartForwardSeconds = 4.;

        public static final double kOffCenterSpeed = 0.35;

        public static final double kOffCenterSeconds = 7.;

        public static final double kLeftDriveSpeed =  0.35;
        public static final double kRightDriveSpeed = 0.35;

        public static final double kLeftDriveSeconds = 1.75;
        public static final double kRightDriveSeconds = 1.75;

        public static final double kLeftFinalSpeed = 0.35;
        public static final double kRightFinalSpeed = 0.35;

        public static final double kLeftFinalSeconds = 2.25;
        public static final double kRightFinalSeconds = 2.25;

        public static final double kReefCooldown = 1.0;
        public static final double kTurnCooldown = 1.0;
    }
}

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
        public static final double kWheelSpeed = 0.75;

        public static final double kExtendedArmAngle = 85;
        public static final double kRotationTestSpeed = 0.5;
    }
    public static class AutonConstants{
        public static final double kSpinWheelsSeconds = 1;

        public static final double kLeftTurnSpeed = 0.25;
        public static final double kRightTurnSpeed = -0.25;

        public static final double kLeftTurnSeconds = 3;
        public static final double kRightTurnSeconds = 3;

        public static final double kStartDriveSpeed = 0.5;

        public static final double kStartForwardSeconds = 2;

        public static final double kLeftDriveSpeed = 0.25;
        public static final double kRightDriveSpeed = 0.25;

        public static final double kLeftDriveSeconds = 3;
        public static final double kRightDriveSeconds = 3;

        public static final double kLeftFinalSpeed = 0.25;
        public static final double kRightFinalSpeed = 0.25;

        public static final double kLeftFinalSeconds = 1.5;
        public static final double kRightFinalSeconds = 1.5;
    }
}

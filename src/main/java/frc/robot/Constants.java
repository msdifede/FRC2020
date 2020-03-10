/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public class Constants {
    public static final int FR_FALCON = 0;
    public static final int FL_FALCON = 14;
    public static final int BR_FALCON = 1;
    public static final int BL_FALCON = 15;

    public static final int INTAKE_TALON = 3;

    public static final int CARWASH_VICTOR1 = 7;
    public static final int CARWASH_VICTOR2 = 6;

    public static final int PRESHOOTER_VICTOR1 = 9;
    public static final int PRESHOOTER_VICTOR2 = 8;

    
    public static final int INTAKE_DOUBLESOLENOID_FRONT_PISTONS1 = 2;
    public static final int INTAKE_DOUBLESOLENOID_FRONT_PISTONS2 = 3;
    public static final int DOUBLESOLENOID_DRIVE1 = 0;
    public static final int DOUBLESOLENOID_DRIVE2 = 1;

    public static final int LAUNCHER1 = 12;
    public static final int LAUNCHER2 = 13;


    public static final int DRIVER_PORT = 0;
    public static final int OPERATOR_PORT = 1;

    public static int DRIVER_LEFT_X = 0;
	public static int DRIVER_LEFT_Y = 1;
	public static int DRIVER_RIGHT_X = 4;
    public static int DRIVER_RIGHT_Y = 5;

    public static int OPERATOR_PINK = 3;
    public static int OPERATOR_PURPLE = 1;
    public static int OPERATOR_RED = 2;
    public static int OPERATOR_GREEN = 4;
    public static int OPERATOR_LEFT_TOP_GRAY = 5;
    public static int OPERATOR_RIGHT_TOP_GRAY = 6;
    public static int OPERATOR_LEFT_BOTTOM_GRAY = 9;
    public static int OPERATOR_RIGHT_BOTTOM_GRAY = 8;
    public static int LITTLE_GRAY_SHARE = 7;
    public static int LITTLE_GRAY_OPTION = 8;
    public static int LITTLE_GRAY_HOME = 13;

    public static final int A_BUTTON = 1;
    public static final int B_BUTTON = 2;
    public static final int X_BUTTON = 3;
    public static final int Y_BUTTON = 4;
    public static final int TRIGGER_LEFT = 5;
    public static final int TRIGGER_RIGHT = 6;

    public static final int TURRET = 10;

    public static final int OPERATOR_X = 0;



        public static final int kEncoderCPR = 8192; // 2048;//8192;
        public static final double kEncoderDistancePerPulse =
            // Distance units will be rotations
            1.0 / (double) kEncoderCPR;

    
        public static final double kShooterFreeRPS = 5300;
        public static final double kShooterTargetRPS = 4000;
        public static final double kShooterToleranceRPS = 50;
    
        // These are not real PID gains, and will have to be tuned for your specific robot.
        public static final double kP = 1;
        public static final double kI = 0;
        public static final double kD = 0;
    
        // On a real robot the feedforward constants should be empirically determined; these are
        // reasonable guesses.
        public static final double kSVolts = 0.05;
        public static final double kVVoltSecondsPerRotation =
            // Should have value 12V at free speed...
            12.0 / kShooterFreeRPS;
    
        public static final double kFeederSpeed = 0.5;
    


}

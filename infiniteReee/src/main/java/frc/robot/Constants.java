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
public final class Constants {
    /**
     * ROBOT MAP
     */

    // CAN IDs
    public final static int DRIVE_LEFT_ID = 2;
    public final static int DRIVE_RIGHT_ID = 1;

    // PWM PORTS
    public final static int WHEEL_SHOOTER_PORT = 0;
    public final static int KICKER_SHOOTER_PORT = 5;
    public final static int INTAKE_PORT = 1;
    public final static int INDEXER_PORT = 2;

    // WHEEL SHOOTER CONSTANTS
    public final static double SHOOT_SPEED = 0.9;
    public final static double KICK_SPEED = 0.5;


    // INTAKE CONSTANTS
    public final static double INTAKE_SPIT_SPEED = 0.5;
    public final static double INTAKE_SWALLOW_SPEED = -0.8;
}

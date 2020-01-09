/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.List;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.controller.RamseteController;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.RamseteCommand;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.util.Units;
import frc.robot.subsystems.Drivetrain.systemStates;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;
  private Drivetrain drivetrain = Drivetrain.getInstance();
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    drivetrain.resetEncoders();
    drivetrain.resetGyro();
    m_autonomousCommand = auto();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
    drivetrain.setSystemState(systemStates.OPEN_LOOP);
    
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    if(OI.getCalibrateKD()) {
      drivetrain.setSystemState(systemStates.CALIBRATIONKD);
    }
    else if (OI.getCalibrateKS()) {
      drivetrain.setSystemState(systemStates.CALIBRATIONKS);
    }
    else {
      drivetrain.setSystemState(systemStates.OPEN_LOOP);
    }
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  public static final double kTrackwidthMeters = Units.inchesToMeters(24);
    public static final double kMaxSpeedMetersPerSecond = 1;
    public static final double kMaxAccelerationMetersPerSecondSquared = 1.5;
     // Reasonable baseline values for a RAMSETE follower in units of meters and seconds
     public static final double kRamseteB = 2;
     public static final double kRamseteZeta = 0.7;
  public Command auto() {
    
    Drivetrain drivetrain = Drivetrain.getInstance();
    /**
     * Creates a new auto.
     */
   // Create a voltage constraint to ensure we don't accelerate too fast
   DifferentialDriveVoltageConstraint autoVoltageConstraint = new DifferentialDriveVoltageConstraint(drivetrain.getFeedForward(),
   drivetrain.getKinematics(),8.0);
  
  // Create config for trajectory
  TrajectoryConfig config = new TrajectoryConfig(kMaxSpeedMetersPerSecond,
                        kMaxAccelerationMetersPerSecondSquared)
       // Add kinematics to ensure max speed is actually obeyed
       .setKinematics(drivetrain.getKinematics())
       // Apply the voltage constraint
       .addConstraint(autoVoltageConstraint);
  
  // An example trajectory to follow.  All units in meters.
  Trajectory exampleTrajectory = TrajectoryGenerator.generateTrajectory(
   // Start at the origin facing the +X direction
   new Pose2d(0, 0, new Rotation2d(0)),
   // Pass through these two interior waypoints, making an 's' curve path
    List.of(
          // new Translation2d(1, 1),
          // new Translation2d(2, 0)
          // new Translation2d(3, -1)
          //new Translation2d(4,0)
          // new Translation2d(3,1),
          // new Translation2d(2,0),
          // new Translation2d(1,-1)

    ),
   // End 3 meters straight ahead of where we started, facing forward
   new Pose2d(3, 0, Rotation2d.fromDegrees(0)),
   // Pass config
   config
  );
  RamseteCommand ramseteCommand = new RamseteCommand( exampleTrajectory, drivetrain::getPose, new RamseteController(kRamseteB, kRamseteZeta),
   drivetrain.getFeedForward(), drivetrain.getKinematics(), drivetrain::getSpeeds, new edu.wpi.first.wpilibj.controller.PIDController(1.5, 0.0, 0.0), 
   new edu.wpi.first.wpilibj.controller.PIDController(1.5,0.0,0.0), drivetrain::tankVoltageDrive, drivetrain);
  
  
  // Run path following command, then stop at the end.
  return ramseteCommand.andThen(() -> drivetrain.tankVoltageDrive(0, 0));
  
  }
}

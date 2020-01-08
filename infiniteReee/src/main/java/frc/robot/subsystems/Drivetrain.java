/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.wpilibj.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import frc.robot.OI;

public class Drivetrain extends SubsystemBase {
  public static Drivetrain instance = new Drivetrain();
  private final CANSparkMax m_rightMotor;
  private final CANSparkMax m_leftMotor;
  private final DifferentialDrive dfDrive;
  private DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(Units.inchesToMeters(Units.inchesToMeters(24)));
  private DifferentialDriveOdometry odometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(0));
  private AHRS gyro; 
  private Pose2d pose = new Pose2d();
  private SimpleMotorFeedforward feedforward = new SimpleMotorFeedforward(.228, 5.0, .0144);
  public enum systemStates {
    NEUTRAL,
    OPEN_LOOP
  }
  
  private systemStates currentState;
  private systemStates requestedState;

  /**
   * Creates a new Drivetrain.
   */
  public Drivetrain() {
    m_rightMotor = new CANSparkMax(1, MotorType.kBrushless);
    m_leftMotor = new CANSparkMax(2, MotorType.kBrushless);
    dfDrive = new DifferentialDrive(m_leftMotor, m_rightMotor);
    currentState = systemStates.OPEN_LOOP;
    gyro = new AHRS(SPI.Port.kMXP);
    m_rightMotor.setInverted(true);
    m_leftMotor.setInverted(false);
    m_rightMotor.getEncoder().setPositionConversionFactor(1.0 / 8.45 * Math.PI * Units.inchesToMeters(4));
    m_leftMotor.getEncoder().setPositionConversionFactor(1.0 / 8.45 * Math.PI * Units.inchesToMeters(4));
    //m_rightMotor.getEncoder().setPositionConversionFactor(1.0);
    //m_leftMotor.getEncoder().setPositionConversionFactor(1.0);
    resetEncoders();
    dfDrive.setMaxOutput(.7);
  }
  
  
  /**
   * @return the instance of the drive tra
   * in to be used
   */
  public DifferentialDriveKinematics getKinematics() {
    return kinematics;
  }
  public SimpleMotorFeedforward getFeedForward() {
    return feedforward;
  }
  public static Drivetrain getInstance() {
    return instance;
  }
  public Rotation2d getHeading() {
    return  Rotation2d.fromDegrees(-gyro.getAngle());
  }
  public void resetGyro() {
    gyro.reset();
  }
  public DifferentialDriveWheelSpeeds getSpeeds() {
    return new DifferentialDriveWheelSpeeds(
      m_leftMotor.getEncoder().getVelocity() / 8.45 * Units.inchesToMeters(4) * Math.PI / 60.0,
      m_rightMotor.getEncoder().getVelocity() / 8.45 * Units.inchesToMeters(4) * Math.PI / 60.0  
    );
  }
  public double getRightDistance() {
    return m_rightMotor.getEncoder().getPosition();
  }
  public double getLeftDistance() {
    return m_leftMotor.getEncoder().getPosition();
  }
  public void resetEncoders() {
    m_leftMotor.getEncoder().setPosition(0);
    m_rightMotor.getEncoder().setPosition(0);
  }
  private void defaultStateChange() {
		if(requestedState!=currentState) {
			currentState = requestedState;
    }
  }

  public void setSystemState (systemStates state) {
    requestedState = state;
  }

  private void arcade() {
    dfDrive.arcadeDrive(OI.getDriveAmount(), OI.getSteerAmount());
  }
  public void tankVoltageDrive(double leftVoltage, double rightVoltage) {
    m_leftMotor.setVoltage(leftVoltage);
    m_rightMotor.setVoltage(rightVoltage);
    SmartDashboard.putNumber("leftVolts", leftVoltage);
    SmartDashboard.putNumber("rightVolts", rightVoltage);
  }

  public Pose2d getPose() {
    return odometry.getPoseMeters();
  }

  

  @Override
  public void periodic() {
    // switch(currentState){
    //   case NEUTRAL
    //     wantedX = 0.0;
    //     wantedzRotation = 0.0;
    //     tank();
    //     break;
    //   case OPEN_LOOP:
    //     tank();
    //     break;
    // }
    if(OI.getResetGyroButton()) {
      resetGyro();
    }
    pose = odometry.update(getHeading(), getLeftDistance(), getRightDistance());
    arcade();
    defaultStateChange();
    //System.out.println(getRightDistance());
    System.out.println(getPose().getTranslation().getX() + " , " + getPose().getTranslation().getY() 
      + " , " + getPose().getRotation().getDegrees());
  }
}

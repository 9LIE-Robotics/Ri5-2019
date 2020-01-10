/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class WheelShooter extends SubsystemBase {
  private final Victor m_wheelMotor;
  private final Victor m_kickerMotor;

  /**
   * Creates a new WheelShooter.
   */
  public WheelShooter() {
    m_wheelMotor = new Victor(Constants.WHEEL_SHOOTER_PORT);
    m_kickerMotor = new Victor(Constants.KICKER_SHOOTER_PORT);
  }

  public void periodic() {
    //m_wheelMotor.set(Constants.SHOOT_SPEED);
  }

  public void setKickerSpeed(double speed) {
    m_kickerMotor.set(speed);
  }

  public void setWheelSpeed(double speed) {
      m_wheelMotor.set(speed);
  }
}

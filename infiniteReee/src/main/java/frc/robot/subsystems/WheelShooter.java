/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class WheelShooter extends SubsystemBase {
  public static WheelShooter instance = new WheelShooter();
  private final CANSparkMax m_wheelMotor;

  /**
   * Creates a new WheelShooter.
   */
  public WheelShooter() {
    m_wheelMotor = new CANSparkMax(3, MotorType.kBrushless);
  }

  /**
   * @return the instance of the drive train to be used
   */
  public static WheelShooter getInstance() {
    return instance;
  }

  public void setSpeed(double speed) {
      m_wheelMotor.set(speed);
  }

  @Override
  public void periodic() {}
}

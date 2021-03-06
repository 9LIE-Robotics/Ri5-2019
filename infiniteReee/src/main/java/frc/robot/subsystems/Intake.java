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

public class Intake extends SubsystemBase {
  private final Victor m_intakeMotor;

  /**
   * Creates a new Intake.
   */
  public Intake() {
    m_intakeMotor = new Victor(Constants.INTAKE_PORT);
  }

  public void setSpeed(double speed) {
    m_intakeMotor.set(speed);
  }
}

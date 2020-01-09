/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.WheelShooter;

import frc.robot.subsystems.Intake;
import frc.robot.subsystems.WheelShooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class StopShooter extends CommandBase {
  private final WheelShooter m_wheelShooter;

  /**
   * Creates a new FireCells.
   *
   * @param subsystem The subsystem used by this command.
   */
  public StopShooter(WheelShooter m_wheelShooter2) {
    m_wheelShooter = m_wheelShooter2;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_wheelShooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_wheelShooter.setKickerSpeed(0.0);
    m_wheelShooter.setWheelSpeed(0.0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}

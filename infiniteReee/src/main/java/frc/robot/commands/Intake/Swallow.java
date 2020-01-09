/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Intake;

import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

/**
 * An example command that uses an example subsystem.
 */
public class Swallow extends CommandBase {
  private final Intake m_intake;

  /**
   * Creates a new Swallow.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Swallow(Intake intake) {
    m_intake = intake;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_intake.setSpeed(Constants.INTAKE_SWALLOW_SPEED);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}  
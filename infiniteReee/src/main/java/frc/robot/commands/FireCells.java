/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.WheelShooter;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

/**
 * An example command that uses an example subsystem.
 */
public class FireCells extends CommandBase {
  private final WheelShooter m_wheelShooter;
  private boolean speedSet;

  /**
   * Creates a new FireCells.
   *
   * @param subsystem The subsystem used by this command.
   */
  public FireCells(WheelShooter wheelShooter) {
    m_wheelShooter = wheelShooter;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(wheelShooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    speedSet = false;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_wheelShooter.setSpeed(Constants.SHOOT_SPEED);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    speedSet = true;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return speedSet;
  }
}

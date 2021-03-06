/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.WheelShooter;

import frc.robot.subsystems.WheelShooter;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

/**
 * An example command that uses an example subsystem.
 */
public class Fire extends CommandBase {
  private final WheelShooter m_wheelShooter;
  private double speed;

  /**
   * Creates a new Fire.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Fire(WheelShooter wheelShooter) {
    m_wheelShooter = wheelShooter;
    speed = 0.0;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_wheelShooter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    speed = 0;
    m_wheelShooter.setWheelSpeed(Constants.KICK_SPEED);
    m_wheelShooter.setWheelSpeed(speed);
  }

  @Override
  public void execute() {
    if( speed < .95) {
       speed += .05;
    }
    m_wheelShooter.setWheelSpeed(speed);
  }
  @Override
  public void end(boolean interrupted) {
    m_wheelShooter.setWheelSpeed(0.0);
    super.end(interrupted);
  }
 
}

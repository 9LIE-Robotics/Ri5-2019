/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Drivetrain;


import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.PixyCam;

public class VisionDrive extends CommandBase {
  private final Drivetrain m_drivetrain;
  private final PixyCam pixy;
  private PIDController visionController;
  /**
   * Creates a new VisionDrive.
   */
  public VisionDrive(Drivetrain drivetrain, PixyCam pixy) {
    m_drivetrain = drivetrain;
    this.pixy = pixy;
    visionController = new PIDController(2.0, 20.0, 0.0);
    visionController.setIntegratorRange(-.2 , .2);
    addRequirements(m_drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double output = visionController.calculate(pixy.getXpercent(), 0.53);
    SmartDashboard.putNumber("error", pixy.getXpercent() -.53);
    if (Math.abs(output) >= 0.75) {
      output = Math.copySign(0.75, output);
    }
    m_drivetrain.arcade(0.0, -output);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

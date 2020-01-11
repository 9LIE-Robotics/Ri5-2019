/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import frc.robot.subsystems.*;
import frc.robot.commands.WheelShooter.*;
import frc.robot.commands.Drivetrain.OpenLoop;
import frc.robot.commands.Drivetrain.ResetGyro;
import frc.robot.commands.Drivetrain.VisionDrive;
import frc.robot.commands.Indexer.ForwardIndex;
import frc.robot.commands.Indexer.ReverseIndex;
import frc.robot.commands.Intake.*;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  private final WheelShooter m_wheelShooter;
  private final Intake m_intake;
  private final Drivetrain m_drivetrain = Drivetrain.getInstance();
  private final PixyCam m_pixy = new PixyCam();
  private final Indexer m_indexer = new Indexer();


  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    
    m_intake = new Intake();
    m_wheelShooter =  new WheelShooter();
    m_drivetrain.setDefaultCommand(new OpenLoop(m_drivetrain));
    //m_intake.setDefaultCommand(new StopIntake(m_intake));
    configureButtonBindings();
    //m_wheelShooter.setDefaultCommand(new StopShooter(m_wheelShooter));
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    OI.getShootButton().whenHeld(new Fire(m_wheelShooter));

    OI.getResetGyroButton().whenHeld(new ResetGyro(m_drivetrain));
    OI.isVisionDriving().whenHeld(new VisionDrive(m_drivetrain, m_pixy));
    //new ParallelCommandGroup(new Swallow(m_intake),new ForwardIndex(m_indexer) )
    
    OI.getIntakeButton().whenHeld(new Swallow(m_intake));

    OI.getindexButton().whenHeld(new ForwardIndex(m_indexer));
    OI.getReverseIndexButton().whenHeld(new ReverseIndex(m_indexer));
  }
}

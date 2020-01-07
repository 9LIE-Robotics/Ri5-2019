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
import frc.robot.OI;

public class Drivetrain extends SubsystemBase {
  public static Drivetrain instance = new Drivetrain();
  private final CANSparkMax m_rightMotor;
  private final CANSparkMax m_leftMotor;
  private final DifferentialDrive dfDrive;
  
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
  }
  
  
  /**
   * @return the instance of the drive train to be used
   */
  public static Drivetrain getInstance() {
    return instance;
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
    arcade();
    defaultStateChange();
  }
}

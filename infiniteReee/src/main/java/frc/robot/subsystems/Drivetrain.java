/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  public static Drivetrain instance = new Drivetrain();
  private final Spark leftMotor;
  private final Spark rightMotor;
  private final DifferentialDrive dfDrive;
  private ControlPanel controlPanel;
  
  public enum systemStates {
    NEUTRAL,
    OPEN_LOOP
  }
  
  private systemStates currentState;
  private systemStates requestedState;
  private double wantedX;
  private double wantedzRotation;
  /**
   * Creates a new Drivetrain.
   */
  public Drivetrain() {
    leftMotor = new Spark(0);
    rightMotor = new Spark(1);
    dfDrive = new DifferentialDrive(leftMotor, rightMotor);
    controlPanel = ControlPanel.getInstance();
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
  public void drive(double wantedX, double wantedzRotation) {
    this.wantedX = wantedX;
    this.wantedzRotation = wantedzRotation;
  }
  private void tank() {
    dfDrive.arcadeDrive(controlPanel.getDriveAmount(), controlPanel.getSteerAmount());
  }
  @Override
  public void periodic() {
    switch(currentState){
      case NEUTRAL:
        wantedX = 0.0;
        wantedzRotation = 0.0;
        tank();
        break;
      case OPEN_LOOP:
        tank();
        break;
    }
    defaultStateChange();
  }
}

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

public class Indexer extends SubsystemBase {
  public static Indexer instance = new Indexer();
  private final Victor m_indexerMotor;

  /**
   * Creates a new Indexer.
   */
  public Indexer() {
    m_indexerMotor = new Victor(Constants.INDEXER_PORT);
  }

  /**
   * @return the instance of the drive train to be used
   */
  public static Indexer getInstance() {
    return instance;
  }

  public void setSpeed(double speed) {
    m_indexerMotor.set(speed);
  }
}

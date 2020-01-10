/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.pixy2api.Pixy2.LinkType;
import frc.robot.pixy2api.Pixy2;

public class PixyCam extends SubsystemBase {
  private Pixy2 pixy = Pixy2.createInstance(LinkType.I2C);
  /**
   * Creates a new pixyCam.
   */
  public PixyCam() {
    pixy.init();
  }

  @Override
  public void periodic() {
    pixy.getCCC().getBlocks(true,1,3);
    if(pixy.getCCC().getBlocks().size() > 0) {
      SmartDashboard.putNumber("X value", pixy.getCCC().getBlocks().get(0).getX());
    }
  }
}

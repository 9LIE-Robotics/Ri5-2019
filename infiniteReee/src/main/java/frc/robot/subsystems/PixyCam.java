/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ArrayList;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.pixy2api.Pixy2.LinkType;
import frc.robot.pixy2api.Pixy2CCC.Block;
import frc.robot.pixy2api.Pixy2;

public class PixyCam extends SubsystemBase {
  private Pixy2 pixy = Pixy2.createInstance(LinkType.I2C);
  ArrayList<Block> blocks = new ArrayList<>();
  Block target = pixy.getCCC().new Block(1, 0, 0, 0, 0, 0, 0, 0);
  /**
   * Creates a new pixyCam.
   */
  public PixyCam() {
    pixy.init();
  }

  @Override
  public void periodic() {
    pixy.getCCC().getBlocks(true,1,3);
    blocks = pixy.getCCC().getBlocks();
    if (isTargetInView()) {
      target = blocks.get(0);
    }
    SmartDashboard.putBoolean("is target lock" , isTargetInView());
    SmartDashboard.putNumber("Xpercent", getXpercent());
    SmartDashboard.putNumber("Ypercent", getYpercent());
    //sortTargets();
  }

  public Block getTarget() {
    return target;
  }

  private void sortTargets() {
    int maxSize = 0;
    Block b = null;
    for(int i = 0; i < blocks.size(); i++){
      if(blocks.get(i).getHeight() * blocks.get(i).getWidth() > maxSize) {
        b = blocks.get(i);
      }
    }
    if (b != null) {
      target = b;
    }
  }

  public double getXpercent() {
    return (double) target.getX()/ (double) pixy.getFrameWidth();
  }

  public double getYpercent() {
    return (double) target.getY()/ (double) pixy.getFrameHeight();
  }
  public double getDistanceFeet() {
    double y = getYpercent();
    return Math.pow(y, 2) * 20.22 + y *-3.3836  + 3.7753;
  }
  public boolean isTargetInView() {
    return blocks.size() > 0;
  }
}

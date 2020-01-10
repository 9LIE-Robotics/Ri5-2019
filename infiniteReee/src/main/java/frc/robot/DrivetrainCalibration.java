/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.Drivetrain;

/**
 * Add your docs here.
 */
public class DrivetrainCalibration {
    private Drivetrain drivetrain = Drivetrain.getInstance();
    private double kS = 0.0;
    private double kD = 0.0;
    private double kA = 0.0;
    private double voltageApplied = 0.0;
    private boolean hasCal = false;
    public void calibratekS() {
        double stepValue = 0.001;

        if(drivetrain.getWheelSpeeds().leftMetersPerSecond < 0.01 && !hasCal) {
            drivetrain.tankVoltageDrive(voltageApplied, voltageApplied);
            voltageApplied += stepValue;
        } else {
            hasCal = true;
            kS = voltageApplied;
            SmartDashboard.putNumber("kS", kS);
        }
    }

    private double oldSpeed = 0.0;
    public void calibratekD() {
        double testVoltage = 7.0;
        drivetrain.tankVoltageDrive(testVoltage, testVoltage);
        double deltaSpeed = drivetrain.getWheelSpeeds().leftMetersPerSecond - oldSpeed;
        if (deltaSpeed < .05) {
            SmartDashboard.putNumber("speed", oldSpeed);
        }
        oldSpeed = drivetrain.getWheelSpeeds().leftMetersPerSecond;
    }

}

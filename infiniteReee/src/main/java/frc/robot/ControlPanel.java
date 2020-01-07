package frc.robot;
import edu.wpi.first.wpilibj.Joystick;

public class ControlPanel {
    private static ControlPanel instance = new ControlPanel();
    private Joystick driveController;

    public ControlPanel() {
        driveController = new Joystick(0);
    }

    public static ControlPanel getInstance() {
        return instance;
    }

    public double getDriveAmount() {
        return driveController.getRawAxis(0);
    }

    public double getSteerAmount() {
        return driveController.getRawAxis(5);
    }
}
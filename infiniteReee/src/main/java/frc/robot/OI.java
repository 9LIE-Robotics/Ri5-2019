package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class OI {
    private static boolean usingXBoxController = true;
    private static Joystick driveController = new Joystick(0);
    private static Joystick steerController = (usingXBoxController) ? null : new Joystick(1);

    public static double getDriveAmount() {
        return (usingXBoxController) ? driveController.getRawAxis(5) : driveController.getY();
    }

    public static double getSteerAmount() {
        return (usingXBoxController) ? driveController.getRawAxis(0) : steerController.getX();
    }
    public static boolean getResetGyroButton() {
        return driveController.getRawButton(8);
    }

    public static boolean getCalibrateKS() {
        return driveController.getRawButton(7);
    }

    public static boolean getCalibrateKD() {
        return driveController.getRawButton(6);
    }

    public static JoystickButton getShootButton() {
        return new JoystickButton(driveController, 1);
    }

    public static JoystickButton getIntakeButton() {
        return new JoystickButton(driveController, 2);
    }
}
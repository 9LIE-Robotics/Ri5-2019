package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class OI {
    private static boolean usingXBoxController = false;
    private static Joystick driveController = new Joystick(0);
    private static Joystick steerController = (usingXBoxController) ? null : new Joystick(1);

    public static double getDriveAmount() {
        return (usingXBoxController) ? driveController.getRawAxis(0) * -0.5 : driveController.getY() * -1;
    }

    public static double getSteerAmount() {
        return (usingXBoxController) ? driveController.getRawAxis(5) * -0.5 : steerController.getX() * -1;
    }

    public static JoystickButton getShootButton() {
        return new JoystickButton(driveController, 1);
    }
}
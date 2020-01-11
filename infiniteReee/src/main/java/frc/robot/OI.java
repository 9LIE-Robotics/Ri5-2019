package frc.robot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

public class OI {
    private static boolean usingXBoxController = true;
    private static Joystick driveController = new Joystick(0);
    private static Joystick steerController = (usingXBoxController) ? null : new Joystick(1);

    public static double getDriveAmount() {
        return (usingXBoxController) ? driveController.getRawAxis(5) * -.8 : driveController.getY();
    }

    public static double getSteerAmount() {
        return (usingXBoxController) ? driveController.getRawAxis(0) * 0.6 : steerController.getX();
    }
    public static JoystickButton getResetGyroButton() {
        return new JoystickButton(driveController, 7);
    }

    

    public static JoystickButton getShootButton() {
        return new JoystickButton(driveController, 1);
    }

    public static JoystickButton getIntakeButton() {
        return new JoystickButton(driveController, 5);
    }

    public static JoystickButton isVisionDriving() {
        return new JoystickButton(driveController,3);
    }
    public static JoystickButton getindexButton() {
        return new JoystickButton(driveController, 6);
    }
    public static JoystickButton getReverseIndexButton() {
        return new JoystickButton(driveController, 2);
    }
}
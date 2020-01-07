public class ControlPanel {
    private static ControlPanel instance = new ControlPanel();
    private Joystick driveController; 

    public ControlPanel() {
        driveController = new Joystick(0);
    }

    public ControlPanel getInstance() {
        return instance;
    }

    public double getDriveAmount() {
        return driveController.getAxis(0).get();
    }

    public double getSteerAmount() {
        return steerStick.getAxis(5).get();
    }
}
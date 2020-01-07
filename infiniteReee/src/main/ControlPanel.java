public class ControlPanel {
    private static ControlPanel instance = new ControlPanel();
    private Joystick driveStick; // -1 to 1
    private Joystick steerStick; // -1 to 1

    public ControlPanel() {
        driveStick = new Joystick(0);
        steerStick = new Joystick(1);
    }

    public ControlPanel getInstance() {
        return instance;
    }

    public double getDriveAmount() {
        return driveStick.get();
    }

    public double getSteerAmount() {
        return steerStick.get();
    }
}
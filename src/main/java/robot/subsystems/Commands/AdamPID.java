package robot.subsystems.Commands;

public class AdamPID {
    private double kP;
    private double kI;
    private double kD;
    private double LoopTime;
    private double error;
    private double Integral = 0;
    private double lastError = error;
    private double derivative;
    private double setpoint;
    private double distance;
    public AdamPID (double input_kP, double input_kI, double input_kD, double input_LoopTime) {
        kP = input_kP;
        kI = input_kI;
        kD = input_kD;
        LoopTime = input_LoopTime;

    }

    public void UpdateKP(double kP) {
        this.kP = kP;
    }
    public void UpdateKI(double kI) {
        this.kI = kI;
    }
    public void UpdateKD(double kD) {
        this.kD = kD;
    }
    public void setSetpoint(double targetSetpoint) {
       setpoint = targetSetpoint;
    }
    public void update(double distance) {
        error = setpoint - distance; //proportional
        Integral += error * LoopTime;
        derivative = (error - lastError) / LoopTime;
        lastError = error;

    }
    public double getOutput() {
        System.out.println("kp" + kP);
        System.out.println("error" + error);

        return kP * error + kI * Integral + kD * derivative;

    }
    public boolean isFinished(){
       return error < 0.1 && error > -0.1;
    }
    public void reset(){
        Integral = 0;
        lastError = error;
    }
}

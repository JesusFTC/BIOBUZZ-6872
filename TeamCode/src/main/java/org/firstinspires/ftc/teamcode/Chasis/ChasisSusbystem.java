package org.firstinspires.ftc.teamcode.Chasis;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.controller.PIDController;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.ConfigureIMU;

public class ChasisSusbystem extends SubsystemBase {
    private final Motor fl_motor;
    private final Motor fr_motor;
    private final Motor bl_motor;
    private final Motor br_motor;
    private ConfigureIMU heading;
    private PIDController pidRotation;
    private double outputPID;

    // --- Variables para el Heading Lock ---
    private double targetHeading = 0;
    private boolean lockEnabled = false; // Indica si el bloqueo está activo
    // ---------------------------------------


    public ChasisSusbystem(HardwareMap hardwareMap, ConfigureIMU heading){

        this.heading = heading;

        fl_motor = new Motor(hardwareMap, "FLMotor");
        fr_motor = new Motor(hardwareMap, "FRMotor");
        bl_motor = new Motor(hardwareMap, "BLMotor");
        br_motor = new Motor(hardwareMap, "BRMotor");
        pidRotation = new PIDController(0.02,0,0);

        fl_motor.setInverted(true);
        fr_motor.setInverted(false);
        bl_motor.setInverted(true);
        br_motor.setInverted(false);

    }

    public void setLock(boolean enabled) {
        this.lockEnabled = enabled;
        if (enabled) {
            this.targetHeading = 0;
        }
    }

    public boolean isLockEnabled() {
        return this.lockEnabled;
    }

    public void motorConversions(double x, double y, double rx){

        double currentHeading = heading.getHeading(AngleUnit.DEGREES);
        double botHeading = Math.toRadians(currentHeading);

        // --- Lógica de Heading Lock ---
        if (lockEnabled) {
            double error = targetHeading - currentHeading;

            rx = 0;
            outputPID = pidRotation.calculate(heading.getHeading(AngleUnit.DEGREES));
        }
        else {
            outputPID = 0;
            pidRotation.setSetPoint(currentHeading);
        }
        // ------------------------------

        double rotx = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        double roty = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        double FLpower = roty + rotx - outputPID + rx;
        double BLpower = roty - rotx - outputPID + rx;
        double FRpower = roty - rotx + outputPID - rx;
        double BRpower = roty + rotx + outputPID - rx;

        double maxPower = Math.max(1.0,
                Math.max(Math.abs(FLpower),
                        Math.max(Math.abs(FRpower),
                                Math.max(Math.abs(BLpower), Math.abs(BRpower)))
                ));

        fl_motor.set(FLpower / maxPower);
        fr_motor.set(FRpower / maxPower);
        bl_motor.set(BLpower / maxPower);
        br_motor.set(BRpower / maxPower);
    }

    public double getOutPID(){

        return outputPID;
    }
    public void stop() {
        fl_motor.stopMotor();
        fr_motor.stopMotor();
        bl_motor.stopMotor();
        br_motor.stopMotor();
    }

    @Override
    public void periodic() {
    }
}
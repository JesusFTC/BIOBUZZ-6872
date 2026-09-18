package org.firstinspires.ftc.teamcode.Intake;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;

public class IntakeSubsystem extends SubsystemBase {
    private final Motor IntakeMotor;

    public IntakeSubsystem(HardwareMap hardwareMap){
        IntakeMotor = new Motor(hardwareMap, "IntakeMotor");
    }

    public void setPower(double power) {
        IntakeMotor.set(power);
    }

    public void stop() {
        IntakeMotor.stopMotor();
    }
}

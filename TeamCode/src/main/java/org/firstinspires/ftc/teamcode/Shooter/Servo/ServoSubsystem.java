package org.firstinspires.ftc.teamcode.Shooter.Servo;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;

public class ServoSubsystem extends SubsystemBase {
    private ServoEx servoEx;
    public ServoSubsystem(HardwareMap hwMap){
        servoEx = new ServoEx(hwMap, "ShooterServo", 0,360);
    }

    public void left(){
        servoEx.set(0);
    }
    public void right(){
        servoEx.set(180);
    }

    @Override
    public void periodic() {
        super.periodic();
    }
}
package org.firstinspires.ftc.teamcode.Shooter;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.seattlesolvers.solverslib.command.SubsystemBase;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.motors.MotorEx;

public class ShooterSubsystem extends SubsystemBase {
    private MotorEx shooterMotor;
    public ShooterSubsystem(HardwareMap hwMap){
        shooterMotor = hwMap.get(MotorEx.class, "ShooterMotor");
    }
    public void start(){
        shooterMotor.set(1);
    }
    public void stop(){
        shooterMotor.stopMotor();
    }
    @Override
    public void periodic() {
        super.periodic();
    }
}
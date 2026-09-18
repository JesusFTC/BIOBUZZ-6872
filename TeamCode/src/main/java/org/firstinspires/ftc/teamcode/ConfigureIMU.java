package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;
import com.seattlesolvers.solverslib.command.SubsystemBase;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class ConfigureIMU extends SubsystemBase {

    private IMU imu;

    public ConfigureIMU(HardwareMap hwMap){
        imu = hwMap.get(IMU.class, "IMU");

        RevHubOrientationOnRobot RevOrient = new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.BACKWARD
        );

        imu.initialize(new IMU.Parameters(RevOrient));
    }

    public double getHeading(AngleUnit angleUnit){
        return  imu.getRobotYawPitchRollAngles().getYaw(angleUnit);
    }

    public void resetImu(){
        imu.resetYaw();
    }

    @Override
    public void periodic() {
        super.periodic();
    }
}

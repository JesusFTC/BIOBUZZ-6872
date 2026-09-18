package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.seattlesolvers.solverslib.command.CommandOpMode;
import com.seattlesolvers.solverslib.command.InstantCommand;
import com.seattlesolvers.solverslib.command.button.Trigger;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Chasis.ChasisCommand;
import org.firstinspires.ftc.teamcode.Chasis.ChasisSusbystem;
import org.firstinspires.ftc.teamcode.Intake.IntakeCommand;
import org.firstinspires.ftc.teamcode.Intake.IntakeSubsystem;
import org.firstinspires.ftc.teamcode.Shooter.Servo.ServoCommand;
import org.firstinspires.ftc.teamcode.Shooter.Servo.ServoSubsystem;
import org.firstinspires.ftc.teamcode.Shooter.ShooterCommand;
import org.firstinspires.ftc.teamcode.Shooter.ShooterSubsystem;
//import org.firstinspires.ftc.teamcode.Intake.Servos.ServoSubsystem;
//import org.firstinspires.ftc.teamcode.Intake.Servos.ServosCommand;

@TeleOp
public class MainController extends CommandOpMode {
    ChasisSusbystem chasisSusbystem;
    ConfigureIMU configureIMU;
    GamepadEx driverController;
    GamepadEx mechanismController;
    IntakeSubsystem intakeSubsystem;
    ShooterSubsystem shooterSubsystem;
    ServoSubsystem servoSubsystem;
    @Override
    public void initialize() {

        driverController = new GamepadEx(gamepad1);
        mechanismController = new GamepadEx(gamepad2);

        configureIMU = new ConfigureIMU(hardwareMap);
        chasisSusbystem = new ChasisSusbystem(hardwareMap, configureIMU);
        intakeSubsystem = new IntakeSubsystem(hardwareMap);
        shooterSubsystem = new ShooterSubsystem(hardwareMap);
        servoSubsystem = new ServoSubsystem(hardwareMap);

        //----------------------------/IMU/----------------------
        new Trigger(() ->
                driverController.wasJustPressed(GamepadKeys.Button.X)
        ).whenActive(new InstantCommand(() -> configureIMU.resetImu()));

        //----------------------------/Heading lock (Y)/----------------------
        new Trigger(() ->
                driverController.wasJustPressed(GamepadKeys.Button.Y)
        ).whenActive(new InstantCommand(() -> {
            boolean newState = !chasisSusbystem.isLockEnabled();
            chasisSusbystem.setLock(newState);
        }));

        chasisSusbystem.setDefaultCommand(
                new ChasisCommand(chasisSusbystem, driverController));

        new Trigger(() -> //Intake
                mechanismController.getTrigger(GamepadKeys.Trigger.LEFT_TRIGGER) > 0.1
        ).whileActiveContinuous(new IntakeCommand(intakeSubsystem, mechanismController));

        new Trigger(() -> //Intake Second button
                mechanismController.getButton(GamepadKeys.Button.A)
        ).whileActiveContinuous(new IntakeCommand(intakeSubsystem, mechanismController));

        new Trigger(() -> //Servo Movement
                mechanismController.getButton(GamepadKeys.Button.RIGHT_BUMPER)
        ).whileActiveContinuous(new ServoCommand(servoSubsystem));


        //----------------------------/Shooter/----------------------

        new Trigger(() ->
                mechanismController.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER) > 0.1
        ).whileActiveContinuous(new ShooterCommand(shooterSubsystem));

    }

    public void run() {
        driverController.readButtons();
        mechanismController.readButtons();

        telemetry.addData("Heading Lock", chasisSusbystem.isLockEnabled() ? "ACTIVADO" : "DESACTIVADO");
        telemetry.addData("Heading", configureIMU.getHeading(AngleUnit.DEGREES));
        telemetry.addData("RX", chasisSusbystem.getOutPID());
        telemetry.update();

        super.run();
    }
}
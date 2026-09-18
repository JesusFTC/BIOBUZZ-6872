package org.firstinspires.ftc.teamcode.Intake;

import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;
import com.seattlesolvers.solverslib.gamepad.GamepadKeys;


public class IntakeCommand extends CommandBase {
    private final IntakeSubsystem intakeSubsystem;
    private GamepadEx controller;


    public IntakeCommand(IntakeSubsystem intakeSubsystem, GamepadEx mechanismController) {
        this.intakeSubsystem = intakeSubsystem;
        this.controller = mechanismController;
        addRequirements(intakeSubsystem);
    }


    @Override
    public void initialize() {
    }
    @Override
    public void execute(){
        intakeSubsystem.setPower(1.0);
    }
    @Override
    public void end(boolean interrupted) {
        intakeSubsystem.stop();
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}

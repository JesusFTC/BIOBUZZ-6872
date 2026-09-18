package org.firstinspires.ftc.teamcode.Chasis;


import com.seattlesolvers.solverslib.command.CommandBase;
import com.seattlesolvers.solverslib.gamepad.GamepadEx;

public class ChasisCommand extends CommandBase {
    private final ChasisSusbystem chasisSusbystem;
    private GamepadEx controller;

    public ChasisCommand(ChasisSusbystem subsystem, GamepadEx controller){
        this.chasisSusbystem = subsystem;
        this.controller = controller;
        addRequirements(chasisSusbystem);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void execute() {
        chasisSusbystem.motorConversions(controller.getLeftX(),
                                            controller.getLeftY(),
                                                controller.getRightX());
    }

    @Override
    public void end(boolean interrupted) {
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}

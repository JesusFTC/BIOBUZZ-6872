package org.firstinspires.ftc.teamcode.Shooter.Servo;

import com.seattlesolvers.solverslib.command.CommandBase;

public class ServoCommand extends CommandBase {
    ServoSubsystem servoSubsystem;
    public ServoCommand(ServoSubsystem servoSubsystem){
        this.servoSubsystem = servoSubsystem;
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        super.execute();
        servoSubsystem.right();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        servoSubsystem.left();
    }
}
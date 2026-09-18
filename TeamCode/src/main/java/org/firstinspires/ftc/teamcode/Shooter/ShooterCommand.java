package org.firstinspires.ftc.teamcode.Shooter;

import com.seattlesolvers.solverslib.command.CommandBase;

import org.firstinspires.ftc.teamcode.Shooter.ShooterSubsystem;

public class ShooterCommand extends CommandBase {
    ShooterSubsystem shooterSubsystem;
    public ShooterCommand(ShooterSubsystem shooterSubsystem){
        this.shooterSubsystem = shooterSubsystem;
    }

    @Override
    public void initialize() {
        super.initialize();
    }

    @Override
    public void execute() {
        super.execute();
        shooterSubsystem.start();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        shooterSubsystem.stop();
    }
}

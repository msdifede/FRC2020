/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Turret;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class SetTurretAngle extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Turret turret;
  private int currentAngle;
  private final int targetAngle;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public SetTurretAngle(Turret turret, int targetAngle){
    this.turret = turret;
    this.targetAngle = targetAngle;

   // this.currentAngle = turret.getPosition();
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(turret);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize(){
    this.currentAngle = turret.getPosition();
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    currentAngle = turret.getPosition();
    if(targetAngle > currentAngle){
      turret.move(-.6);
    }
    if(targetAngle < currentAngle){
      turret.move(.6);
    }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    turret.move(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(targetAngle==currentAngle){
      return true;

    }
    return false;
  }
}

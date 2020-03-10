/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.CarWash;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Launcher;
import frc.robot.subsystems.PreShooter;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class Vomit extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Launcher launcher;
  private final PreShooter preShooter;
  private final CarWash carWash;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Vomit(Launcher launcher, PreShooter preShooter, CarWash carWash) {
    this.launcher = launcher;
    this.preShooter = preShooter;
    this.carWash = carWash;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(launcher);
    addRequirements(preShooter);
    addRequirements(carWash);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    launcher.spin(.2);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      preShooter.moveExitMotor(1);
      preShooter.moveEntryMotor(.5);
      carWash.move();
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    preShooter.stop();
    carWash.stop();
    launcher.stop();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

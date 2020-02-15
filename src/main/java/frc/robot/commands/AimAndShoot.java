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
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.PreShooter;
import frc.robot.subsystems.Turret;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class AimAndShoot extends CommandBase {
  @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
  private final Launcher launcher;
  private final PreShooter preShooter;
  private final CarWash carWash;
  private final Turret turret;
  private final LimeLight limelight;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public AimAndShoot(Launcher launcher, PreShooter preShooter, CarWash carWash, Turret turret,LimeLight limelight) {
    this.launcher = launcher;
    this.preShooter = preShooter;
    this.carWash = carWash;
    this.turret = turret;
    this.limelight = limelight;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(launcher);
    addRequirements(preShooter);
    addRequirements(carWash);

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    launcher.spin();
    AimTurret at = new AimTurret(turret, limelight);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    limelight.setLedMode(3);
    if( launcher.getSpeed() >= 10.8 && limelight.isAimed() ){
      preShooter.moveExitMotor(.5);
      preShooter.moveEntryMotor(1.0);
      carWash.move();
    }else{
      //AimTurret at = new AimTurret(turret, limelight);


      preShooter.moveEntryMotor(0);
      carWash.stop();
    }
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

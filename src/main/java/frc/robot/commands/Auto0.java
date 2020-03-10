/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.CarWash;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Launcher;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.PreShooter;
import frc.robot.subsystems.Turret;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/**
 * An example command that uses an example subsystem.
 */
public class Auto0 extends SequentialCommandGroup {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public Auto0(DriveSub drive, Launcher launcher, PreShooter preShooter, CarWash carWash, Turret turret,
      LimeLight limelight) {

    addCommands(
      
      new InstantCommand(drive::resetGyro,drive),
        new ParallelCommandGroup(
          new RunCommand(() -> limelight.setPipeline(0), limelight).withTimeout(.1), 
          new InstantCommand(launcher::spin, launcher) 
        ).withTimeout(.5), 
      new AimTurret(turret, limelight),
      new ShootBalls( launcher, preShooter, carWash).withTimeout(4),
      new DriveCommand(drive).withTimeout(.5)
    );
    // Use addRequirements() here to declare subsystem dependencies.
    //addRequirements(subsystem);
  }

}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Launcher;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final DriveSub drivetrain = new DriveSub(new TalonFX(Constants.FR_FALCON), new TalonFX(Constants.FL_FALCON),
      new TalonFX(Constants.BR_FALCON), new TalonFX(Constants.BL_FALCON));

  private final Encoder shooterEncoder = new Encoder( 1, 2, 3, true );


  private final Launcher launcher = new Launcher( new TalonSRX( Constants.LAUNCHER1) , new TalonSRX( Constants.LAUNCHER2),  shooterEncoder);
  
  private final Joystick driver =  new Joystick(Constants.DRIVER_PORT);

  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {

    //Allows the robot to move when the driver moves the joysticks in teleop
    drivetrain.setDefaultCommand(
      new RunCommand(() -> drivetrain.TeleOpDrive(
        driver.getRawAxis(Constants.DRIVER_LEFT_Y),
        driver.getRawAxis(Constants.DRIVER_RIGHT_Y)), drivetrain
    ));
    
  
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    JoystickButton driver_A = new JoystickButton(driver, Constants.A_BUTTON);

    //Move motor at 100% speed when A is pressed, move at 0 when released
    driver_A.whenPressed( new RunCommand(() -> launcher.spin(
      1), launcher));
    driver_A.whenReleased( new RunCommand(() -> launcher.spin(
      0), launcher));

  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}

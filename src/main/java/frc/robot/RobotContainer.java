/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.CarWash;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Launcher;
import frc.robot.subsystems.PreShooter;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
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

 // private final Shooter shooter = new Shooter( new TalonSRX( Constants.LAUNCHER1) , new TalonSRX( Constants.LAUNCHER2),  shooterEncoder);

  private final CarWash carWash = new CarWash(new VictorSPX( Constants.CARWASH_VICTOR1), new VictorSPX(Constants.CARWASH_VICTOR2));

  private final Intake intake = new Intake(new DoubleSolenoid(Constants.INTAKE_DOUBLESOLENOID_LEFT1, Constants.INTAKE_DOUBLESOLENOID_LEFT2 ), new DoubleSolenoid(Constants.INTAKE_DOUBLESOLENOID_RIGHT1, Constants.INTAKE_DOUBLESOLENOID_RIGHT2), new TalonSRX(Constants.INTAKE_TALON));

  private final PreShooter preShooter = new PreShooter(new VictorSPX(Constants.PRESHOOTER_VICTOR1), new VictorSPX(Constants.PRESHOOTER_VICTOR2));

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

    // JoystickButton driver_A = new JoystickButton(driver, Constants.A_BUTTON);

    // //Move motor at 100% speed when A is pressed, move at 0 when released
    // driver_A.whenPressed( new RunCommand(() -> launcher.spin(
    //   1), launcher));
    // driver_A.whenReleased( new RunCommand(() -> launcher.spin(
    //   0), launcher));
    JoystickButton driver_A = new JoystickButton( driver, Constants.A_BUTTON);
    JoystickButton driver_B = new JoystickButton( driver, Constants.B_BUTTON);
    JoystickButton driver_X = new JoystickButton( driver, Constants.X_BUTTON);
    JoystickButton driver_Y = new JoystickButton( driver, Constants.Y_BUTTON);


   driver_A.whileHeld(new InstantCommand(launcher::spin, launcher));
   driver_A.whenReleased(new InstantCommand(launcher::stop, launcher));

   driver_B.whileHeld(new InstantCommand(intake::move, intake));
   driver_B.whenReleased(new InstantCommand(intake::stop, intake));

   driver_X.whileHeld(new InstantCommand(carWash::move, carWash));
   driver_X.whenReleased(new InstantCommand(carWash::stop, carWash));

   driver_Y.whileHeld(new InstantCommand(preShooter::move, preShooter));
   driver_Y.whenReleased(new InstantCommand(preShooter::stop, preShooter));


    // new JoystickButton( driver, Constants.B_BUTTON).whileHeld( new InstantCommand(intake::move, intake));
 
    // new JoystickButton(driver, Constants.Y_BUTTON).whenHeld(new InstantCommand(preShooter::move, preShooter));


    // new JoystickButton(driver, Constants.X_BUTTON).whileHeld(new InstantCommand(carWash::move, carWash));

    new JoystickButton(driver, Constants.TRIGGER_LEFT).whileHeld(new InstantCommand(intake::open, intake));

    new JoystickButton(driver, Constants.TRIGGER_RIGHT).whileHeld(new InstantCommand(intake::close, intake));




  // Turn off the shooter when the 'B' button is pressed
  // new JoystickButton(driver, Constants.B_BUTTON)
  //     .whenPressed(new InstantCommand(shooter::disable, shooter));

  // Run the feeder when the 'X' button is held, but only if the shooter is at speed
  // new JoystickButton(driver, Constants.X_BUTTON).whenPressed(new ConditionalCommand(
  //     // Run the feeder
  //     new InstantCommand(shooter::runFeeder, shooter),
  //     // Do nothing
  //     new InstantCommand(),
  //     // Determine which of the above to do based on whether the shooter has reached the
  //     // desired speed
  //     shooter::atSetpoint)).whenReleased(new InstantCommand(shooter::stopFeeder, shooter));





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

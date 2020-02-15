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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.AimAndShoot;
import frc.robot.commands.AimTurret;
import frc.robot.commands.Auto;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.MoveFowardAuto;
import frc.robot.commands.ShootBalls;
import frc.robot.subsystems.CarWash;
import frc.robot.subsystems.DriveSub;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Launcher;
import frc.robot.subsystems.LimeLight;
import frc.robot.subsystems.PreShooter;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Turret;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
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
      new TalonFX(Constants.BR_FALCON), new TalonFX(Constants.BL_FALCON),
      new DoubleSolenoid(Constants.DOUBLESOLENOID_DRIVE1, Constants.DOUBLESOLENOID_DRIVE2));

  private final Encoder shooterEncoder = new Encoder(1, 2, 3, true);

  // private final Shooter shooter = new Shooter( new TalonSRX(
  // Constants.LAUNCHER1) , new TalonSRX( Constants.LAUNCHER2), shooterEncoder);

  private final CarWash carWash = new CarWash(new VictorSPX(Constants.CARWASH_VICTOR1),
      new VictorSPX(Constants.CARWASH_VICTOR2));

  private final Intake intake = new Intake(new DoubleSolenoid(Constants.INTAKE_DOUBLESOLENOID_FRONT_PISTONS1,
      Constants.INTAKE_DOUBLESOLENOID_FRONT_PISTONS2), new TalonSRX(Constants.INTAKE_TALON));
  private final Turret turret = new Turret(new TalonSRX(Constants.TURRET));
  private final PreShooter preShooter = new PreShooter(new VictorSPX(Constants.PRESHOOTER_VICTOR1),
      new VictorSPX(Constants.PRESHOOTER_VICTOR2));

  private final Launcher launcher = new Launcher(new TalonSRX(Constants.LAUNCHER1), new TalonSRX(Constants.LAUNCHER2),
      shooterEncoder);

  private final Joystick driver = new Joystick(Constants.DRIVER_PORT);
  private final Joystick operator = new Joystick(Constants.OPERATOR_PORT);
  private final LimeLight limelight = new LimeLight();

  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  //private final MoveFowardAuto m_autoCommand = new MoveFowardAuto(drivetrain);


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

    limelight.setDefaultCommand(new RunCommand(() -> limelight.readValues(), limelight));

    turret.setDefaultCommand(
      new RunCommand(() -> turret.move(
        -operator.getRawAxis(Constants.OPERATOR_X)), turret ));
    
  
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
    JoystickButton right_TRIGGER = new JoystickButton(driver, Constants.TRIGGER_RIGHT);
    JoystickButton left_TRIGGER = new JoystickButton(driver, Constants.TRIGGER_LEFT);


    JoystickButton pinkButton = new JoystickButton(operator, Constants.OPERATOR_PINK);
    JoystickButton purpleButton = new JoystickButton(operator, Constants.OPERATOR_PURPLE);
    JoystickButton greenButton = new JoystickButton(operator, Constants.OPERATOR_GREEN);
    JoystickButton redButton = new JoystickButton(operator, Constants.OPERATOR_RED);
    JoystickButton leftTopGrayButton = new JoystickButton(operator, Constants.OPERATOR_LEFT_TOP_GRAY);
    JoystickButton rightTopGrayButton = new JoystickButton(operator, Constants.OPERATOR_RIGHT_TOP_GRAY);
    JoystickButton littleGrayShareButton = new JoystickButton(operator, Constants.LITTLE_GRAY_SHARE);
    JoystickButton littleGrayOptionButton = new JoystickButton(operator, Constants.LITTLE_GRAY_OPTION);
    JoystickButton littleGrayHomeButton = new JoystickButton(operator, Constants.LITTLE_GRAY_HOME);



  //  driver_A.whileHeld(new InstantCommand(launcher::spin, launcher));
  //  driver_A.whenReleased(new InstantCommand(launcher::stop, launcher));

  //  driver_A.whenPressed(new ShootBalls(launcher, preShooter, carWash).withTimeout(8) );

  //  driver_B.whileHeld(new InstantCommand(intake::move, intake));
  //  driver_B.whenReleased(new InstantCommand(intake::stop, intake));

  //  driver_X.whileHeld(new InstantCommand(carWash::move, carWash));
  //  driver_X.whenReleased(new InstantCommand(carWash::stop, carWash));

  //  driver_Y.whileHeld(new InstantCommand(preShooter::move, preShooter));
  //  driver_Y.whenReleased(new InstantCommand(preShooter::stop, preShooter));

  //  left_TRIGGER.whileHeld(new InstantCommand(intake::open, intake));

  //  right_TRIGGER.whileHeld(new InstantCommand(intake::close, intake));

   greenButton.whileHeld(new InstantCommand(intake::move, intake));
   greenButton.whenReleased(new InstantCommand(intake::stop, intake));

   pinkButton.whileHeld(new InstantCommand(intake::open, intake));
   purpleButton.whileHeld(new InstantCommand(intake::close, intake));

    redButton.whenPressed(new SequentialCommandGroup( new RunCommand(() -> limelight.setPipeline(0), limelight).withTimeout(.1), new InstantCommand(launcher::spin, launcher), new AimTurret( turret, limelight).withTimeout(1.5), new ShootBalls( launcher, preShooter, carWash).withTimeout(8), new RunCommand(() -> limelight.setPipeline(1), limelight).withTimeout(.1)  ));
   //redButton.whenPressed(new AimTurret(turret, limelight));
   //redButton.whenPressed(new ShootBalls(launcher, preShooter, carWash).withTimeout(8));

   leftTopGrayButton.whileHeld(new InstantCommand(carWash::move, carWash));
   leftTopGrayButton.whenReleased(new InstantCommand(carWash::stop, carWash));

   littleGrayShareButton.whenPressed(new InstantCommand(drivetrain::setHighGear, drivetrain));
   littleGrayOptionButton.whenPressed(new InstantCommand(drivetrain::setLowGear, drivetrain));


    // new JoystickButton( driver, Constants.B_BUTTON).whileHeld( new InstantCommand(intake::move, intake));
 
    // new JoystickButton(driver, Constants.Y_BUTTON).whenHeld(new InstantCommand(preShooter::move, preShooter));


    // new JoystickButton(driver, Constants.X_BUTTON).whileHeld(new InstantCommand(carWash::move, carWash));





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
    return new Auto(drivetrain, launcher, preShooter, carWash, turret, limelight);
  }

  public Command getTeleopInitCommand() {
    // An ExampleCommand will run in autonomous
    return new RunCommand(() -> limelight.setPipeline(1), limelight);
  }

  public void postToSmartDashboard(){
    SmartDashboard.putNumber("shooter voltage", launcher.getSpeed());
    SmartDashboard.putNumber("turret encoder", turret.getPosition());
  }
}

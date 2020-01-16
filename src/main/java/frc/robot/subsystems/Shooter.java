package frc.robot.subsystems;

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.PIDSubsystem;
import frc.robot.Constants;

public class Shooter extends PIDSubsystem {
  
private final SimpleMotorFeedforward m_shooterFeedforward =
      new SimpleMotorFeedforward(Constants.kSVolts,
                                 Constants.kVVoltSecondsPerRotation);

   private final TalonSRX motor1;
   private final TalonSRX motor2;
   private final Encoder shaftEncoder;
                                 
   public Shooter(TalonSRX m1, TalonSRX m2, Encoder e1) {
    super(new PIDController(Constants.kP, Constants.kI, Constants.kD));
    getController().setTolerance(Constants.kShooterToleranceRPS);
    e1.setDistancePerPulse(Constants.kEncoderDistancePerPulse);
    setSetpoint(Constants.kShooterTargetRPS);

      motor1 = m1;
      motor2 = m2;
      shaftEncoder = e1;
                                 
      motor2.follow(m1);
      motor1.setInverted(false);
      motor2.setInverted(InvertType.FollowMaster);                          
   }

  @Override
  public void useOutput(double output, double setpoint) {
    motor1.set(ControlMode.PercentOutput, output + m_shooterFeedforward.calculate(setpoint));
  }

  @Override
  public double getMeasurement() {
    SmartDashboard.putNumber("Shooter Encoder: ", shaftEncoder.getRate());
    return shaftEncoder.getRate();

  }

  public boolean atSetpoint() {
    return m_controller.atSetpoint();
  }

  public void runFeeder() {
   // m_feederMotor.set(Constants.kFeederSpeed);
   System.out.println("run feeder");
  }

  public void stopFeeder() {
  //  m_feederMotor.set(0);
  System.out.println("stop feeder");
  }
}

/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Launcher extends SubsystemBase {

  private TalonSRX motor1;
  private TalonSRX motor2;
  private double setpoint;
  
  public Launcher(TalonSRX m1, TalonSRX m2) {
    motor1 = m1;
    motor2 = m2;
    setpoint = 9; 
  
    motor2.follow(motor1);
    motor1.setInverted(false);
    motor2.setInverted(InvertType.OpposeMaster);
  }

  public void spin(double speed){
    motor1.set(ControlMode.PercentOutput, speed);
  }

  public void spin(){
    motor1.set(ControlMode.PercentOutput, 1);
  }

  public void stop(){
    motor1.set(ControlMode.PercentOutput, 0);
  }

  public double getSpeed(){
    return motor1.getMotorOutputVoltage();
  }

  public boolean atSetpoint(){
    return motor1.getMotorOutputVoltage() > setpoint; 
  }

  public void changeSetpoint( double d ){
    setpoint += d;
  }

  public double getSetpoint(){
    return setpoint;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

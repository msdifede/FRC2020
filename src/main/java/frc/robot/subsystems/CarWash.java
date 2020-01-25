/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class CarWash extends SubsystemBase {
  private VictorSPX motor1;
  private VictorSPX motor2;
  public CarWash(VictorSPX m1, VictorSPX m2) {
    motor1 = m1;
    motor2= m2;

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void move(double speed){
    motor1.set(ControlMode.PercentOutput, speed);
    motor2.set(ControlMode.PercentOutput, -speed);
  }
 
  public void move(){
    motor1.set(ControlMode.PercentOutput, .5);
    motor2.set(ControlMode.PercentOutput, -.5);
  }

  public void stop(){
    motor1.set(ControlMode.PercentOutput, 0);
    motor2.set(ControlMode.PercentOutput, 0);
  }

}

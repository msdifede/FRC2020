/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
  WPI_TalonFX motor;
  public Climber( WPI_TalonFX m) {
    motor = m;

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void move(double speed){
    motor.set(ControlMode.PercentOutput, speed);
  }

  public void move(){
    motor.set(ControlMode.PercentOutput, .8);
  }//changed

  public void stop(){
    motor.set(ControlMode.PercentOutput, 0);
  }


}

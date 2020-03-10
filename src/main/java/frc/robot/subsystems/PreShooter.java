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

public class PreShooter extends SubsystemBase {
  private VictorSPX entryMotor;
  //private VictorSPX exitMotor;
  
  public PreShooter(VictorSPX m1, VictorSPX m2) {
    entryMotor = m1;
    //exitMotor = m2;
    entryMotor.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void move(double speed){
    entryMotor.set(ControlMode.PercentOutput, -speed);
   // exitMotor.set(ControlMode.PercentOutput, speed);
  }

  public void moveEntryMotor( double speed ){
    entryMotor.set(ControlMode.PercentOutput, -speed);
  }

  public void moveExitMotor( double speed ){
    //exitMotor.set(ControlMode.PercentOutput, speed);
  }

  public void move(){
    entryMotor.set(ControlMode.PercentOutput, -1);
   // exitMotor.set(ControlMode.PercentOutput, .5);
  }

  public void stop(){
    entryMotor.set(ControlMode.PercentOutput, 0);
    //exitMotor.set(ControlMode.PercentOutput, 0);
  }
}

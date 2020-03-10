/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Turret extends SubsystemBase {
  private TalonSRX motor;
  
  /**
   * Creates a new ExampleSubsystem.
   */
  public Turret( TalonSRX motor1) {
    motor = motor1;
    
    //motor.setControlMode(ControlMode.Position); //Change control mode of talon, default is PercentVbus (-1.0 to 1.0)
    //talon.setFeedbackDevice(FeedbackDevice.QuadEncoder); //Set the feedback device that is hooked up to the talon
  
    //talon.setPID(0.5, 0.001, 0.00, 0.00, 360, 36, 0); //Set the PID constants (p, i, d)
    
    //talon.enableControl(); //Enable PID control on the talon

  }

  public int getPosition(){
    //return motor.getActiveTrajectoryPosition();
   return (int)(motor.getSelectedSensorPosition()/85.33);
   // return 1;
  }


  public void move(double speed){
    motor.set(ControlMode.PercentOutput, .25*speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

}

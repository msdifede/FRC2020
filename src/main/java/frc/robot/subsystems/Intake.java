/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
  private DoubleSolenoid pistons;
  private TalonSRX rollyBar;
  private boolean isOpen;
  

  public Intake(DoubleSolenoid p, TalonSRX rb) {
    pistons = p;
    rollyBar = rb;
    isOpen = false;

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void move(double speed){
    //System.out.println("speed:" + speed);
    rollyBar.set(ControlMode.PercentOutput, -speed);
  }

  public void move(){
    //System.out.println("speed:" + speed);
    rollyBar.set(ControlMode.PercentOutput,-.75);
  }

  public void stop(){
    rollyBar.set(ControlMode.PercentOutput, 0);
  }

  public void open() {
    pistons.set(DoubleSolenoid.Value.kForward);
    isOpen = true;
    
	}
	
	public void close() {
    pistons.set(DoubleSolenoid.Value.kReverse);
    isOpen = false;
    
	}
	
	public void off() {
    pistons.set(DoubleSolenoid.Value.kOff);
  }
  
  public boolean getIsOpen(){
    return isOpen;
  }




}

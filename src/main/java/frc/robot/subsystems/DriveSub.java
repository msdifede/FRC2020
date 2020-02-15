/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSub extends SubsystemBase {

  private TalonFX frontRightMotor;
  private TalonFX frontLeftMotor;
  private TalonFX backRightMotor;
  private TalonFX backLeftMotor;
  private DoubleSolenoid shifters;
  private Boolean isHighGear;
  private AHRS ahrs;

  /**
   * Creates a new ExampleSubsystem.
   */
  public DriveSub(TalonFX fr, TalonFX fl, TalonFX br, TalonFX bl, DoubleSolenoid s) {
    super();
    frontRightMotor = fr;
    frontLeftMotor = fl;
    backLeftMotor = bl;
    backRightMotor = br;
    shifters = s;

    backRightMotor.follow(frontRightMotor);
    backLeftMotor.follow(frontLeftMotor);

    frontLeftMotor.setInverted(true);
    frontRightMotor.setInverted(false);

    backRightMotor.setInverted(InvertType.FollowMaster);
    backLeftMotor.setInverted(InvertType.FollowMaster);

    setHighGear();

    try{
      ahrs = new AHRS(SPI.Port.kMXP);
    } catch (RuntimeException ex ){
     // DriverStation.reportError("Error instantiating nvX " + ex.getMessage(), true);
    }
  }

  public void TeleOpDrive(double left, double right){
    frontLeftMotor.set(ControlMode.PercentOutput, left);
    frontRightMotor.set(ControlMode.PercentOutput, right);
    SmartDashboard.putNumber("Gyro", getAngle());
  }


  public void setHighGear() {
    shifters.set(DoubleSolenoid.Value.kForward);
    isHighGear = true;
    
	}
	
	public void setLowGear() {
    shifters.set(DoubleSolenoid.Value.kReverse);
    isHighGear = false;
    
	}

  
  public boolean getIsHighGear(){
    return isHighGear;
  }

  public void resetGyro(){
    ahrs.reset();
  }

  public double getAngle(){
    return ahrs.getAngle();
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

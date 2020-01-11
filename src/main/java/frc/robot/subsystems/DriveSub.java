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

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSub extends SubsystemBase {

  private TalonFX frontRightMotor;
  private TalonFX frontLeftMotor;
  private TalonFX backRightMotor;
  private TalonFX backLeftMotor;

  /**
   * Creates a new ExampleSubsystem.
   */
  public DriveSub(TalonFX fr, TalonFX fl, TalonFX br, TalonFX bl) {
    super();
    frontRightMotor = fr;
    frontLeftMotor = fl;
    backLeftMotor = bl;
    backRightMotor = br;

    backRightMotor.follow(frontRightMotor);
    backLeftMotor.follow(frontLeftMotor);

    frontLeftMotor.setInverted(true);
    frontRightMotor.setInverted(false);

    backRightMotor.setInverted(InvertType.FollowMaster);
    backLeftMotor.setInverted(InvertType.FollowMaster);

  }

  public void TeleOpDrive(double left, double right){
    frontLeftMotor.set(ControlMode.PercentOutput, left);
    frontRightMotor.set(ControlMode.PercentOutput, right);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

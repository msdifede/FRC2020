/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimeLight extends SubsystemBase {
  NetworkTable table;
  NetworkTableEntry tx, ty, tv, ta, ts;
  double x, y, v, area, s;
  /**
   * Creates a new ExampleSubsystem.
   */
  public LimeLight() {
    table = NetworkTableInstance.getDefault().getTable("limelight");
    tx = table.getEntry("tx");
    ty = table.getEntry("ty");
    tv = table.getEntry("tv");
    ta = table.getEntry("ta");
    ts = table.getEntry("ts");

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  //  setDefaultCommand(new LimelightCommand());
  }

  public void readValues(){
    x = tx.getDouble(0.0);
    y = ty.getDouble(0.0);
    v = tv.getDouble(0.0);
    area = ta.getDouble(0.0);
    s = ts.getDouble(0.0);

  }

  public void postToDashboard(){
    SmartDashboard.putNumber("LimelightX", x);
    SmartDashboard.putNumber("LimelightY", y);
    SmartDashboard.putNumber("LimelightX", v);
    SmartDashboard.putNumber("LimelightArea", area);
    SmartDashboard.putNumber("LimelightRotation", s);
  }

  public double getX(){
    readValues();
    return x;
  }

  public double getY(){
    readValues();
    return y;
  }

  public double getV(){
    readValues();  
    return v;
  }

  public double getArea(){
    readValues();
    return area;
  }

  public double getRotation(){
    readValues();
    return s;
  }

  public boolean seesTarget(){
    readValues(); 
    if( v == 0 )
      return false;
    else  
      return true;
  }
}

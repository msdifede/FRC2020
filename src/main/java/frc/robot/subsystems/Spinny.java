// /*----------------------------------------------------------------------------*/
// /* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
// /* Open Source Software - may be modified and shared by FRC teams. The code   */
// /* must be accompanied by the FIRST BSD license file in the root directory of */
// /* the project.                                                               */
// /*----------------------------------------------------------------------------*/

// package frc.robot.subsystems;

// import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.can.VictorSPX;
// import edu.wpi.first.wpilibj.I2C;

// import edu.wpi.first.wpilibj.Encoder;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import edu.wpi.first.wpilibj.util.Color;

// import com.revrobotics.ColorSensorV3;
// import com.revrobotics.ColorMatchResult;
// import com.revrobotics.ColorMatch;
 

// public class Spinny extends SubsystemBase {
//   private VictorSPX motor;
//   private Encoder encoder;
//   private ColorSensorV3 colorSensor = new ColorSensorV3(I2C.Port.kOnboard); 
//   private final ColorMatch m_colorMatcher = new ColorMatch();

//   /**
//    * Note: Any example colors should be calibrated as the user needs, these
//    * are here as a basic example.
//    */
//   private final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
//   private final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
//   private final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
//   private final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

//   /**
//    * Creates a new ExampleSubsystem.
//    */
//   public Spinny(VictorSPX m, Encoder e) {
//     motor = m;
//     encoder = e;
//     m_colorMatcher.addColorMatch(kBlueTarget);
//     m_colorMatcher.addColorMatch(kGreenTarget);
//     m_colorMatcher.addColorMatch(kRedTarget);
//     m_colorMatcher.addColorMatch(kYellowTarget);
//   }

//   @Override
//   public void periodic() {
//     // This method will be called once per scheduler run
//   }
  
//   public String getColor(){
//     Color detectedColor = colorSensor.getColor();

//     /**
//      * Run the color match algorithm on our detected color
//      */
//     String colorString;
//     ColorMatchResult match = m_colorMatcher.matchClosestColor(detectedColor);

//     if (match.color == kBlueTarget) {
//       colorString = "Blue";
//     } else if (match.color == kRedTarget) {
//       colorString = "Red";
//     } else if (match.color == kGreenTarget) {
//       colorString = "Green";
//     } else if (match.color == kYellowTarget) {
//       colorString = "Yellow";
//     } else {
//       colorString = "Unknown";
//     }
//     return colorString;
//   }

//   public double getRotations(){
//     return encoder.getDistance();

//   }

//   public void move(double speed){
//     motor.set(ControlMode.PercentOutput, speed);
//   }
// }


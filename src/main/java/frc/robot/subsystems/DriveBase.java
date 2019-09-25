/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import frc.robot.Robot;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
/**
 * Add your docs here.
 */

public class DriveBase extends Subsystem {
  private TalonSRX driveFrontL;//left front motor
  private TalonSRX driveFrontR;//right front motor
  private VictorSPX driveRearL;//left rear motor
  private VictorSPX driveRearR;//right rear motor
  private double peak;
  public DriveBase()
  {
    peak = .5;
    driveFrontL = new TalonSRX(2);
    driveFrontR = new TalonSRX(3);
    driveRearL= new VictorSPX(0);
    driveRearR = new VictorSPX(1);
    driveRearL.follow(driveFrontL);
    driveRearR.follow(driveFrontR);
    driveFrontR.setInverted(true);
    driveRearR.setInverted(true);
    Robot.initMotor(driveFrontL, peak);
    Robot.initMotor(driveFrontR, peak);
    Robot.initMotor(driveRearL, peak);
    Robot.initMotor(driveRearR, peak);
  }
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public void move(double speedL, double speedR)
  {
    driveFrontL.set(ControlMode.PercentOutput, speedL);
    driveFrontR.set(ControlMode.PercentOutput, speedR);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}

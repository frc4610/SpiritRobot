/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
/**
 * Add your docs here.
 */

public class DriveBase extends Subsystem {
  private VictorSPX driveFrontL;
  private VictorSPX driveFrontR;
  private TalonSRX driveRearL;
  private TalonSRX driveRearR;

  public DriveBase()
  {
    driveRearR = new TalonSRX(2);
    driveRearL = new TalonSRX(3);
    driveFrontR = new VictorSPX(0);
    driveFrontL = new VictorSPX(1);
    driveRearL.follow(driveFrontL);
    driveRearR.follow(driveFrontR);
    driveFrontR.setInverted(true);
    driveRearR.setInverted(true);
    driveFrontL.setNeutralMode(NeutralMode.Brake);
    driveFrontR.setNeutralMode(NeutralMode.Brake);
    driveRearL.setNeutralMode(NeutralMode.Brake);
    driveRearR.setNeutralMode(NeutralMode.Brake);
    
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

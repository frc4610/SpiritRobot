/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Shooter extends Subsystem {
  private VictorSPX shootL;//left main motor
  private VictorSPX shootR;//right main motor
  private VictorSPX shootLF;//Left follower motor
  private VictorSPX shootRF;//right follower motor
  public Shooter()
  {
    shootL = new VictorSPX(8);
    shootR = new VictorSPX(9);
    shootLF = new VictorSPX(10);
    shootRF = new VictorSPX(11);
    shootL.setInverted(true);
    shootLF.setInverted(true);
    shootL.follow(shootR);
    shootLF.follow(shootR);
    shootRF.follow(shootR);
    shootL.setNeutralMode(NeutralMode.Brake);
    shootR.setNeutralMode(NeutralMode.Brake);
  }

  public void shoot(double speed)
  {
    shootR.set(ControlMode.PercentOutput, speed);
  }
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}

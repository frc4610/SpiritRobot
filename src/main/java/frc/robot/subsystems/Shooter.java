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
public class Shooter extends Subsystem {
  private VictorSPX shootL;
  private VictorSPX shootR;
  private VictorSPX shootLF;
  private VictorSPX shootRF;
  private TalonSRX aim;
  public Shooter()
  {
    shootL = new VictorSPX(0);
    shootR = new VictorSPX(1);
    shootLF = new VictorSPX(2);
    shootRF = new VictorSPX(3);
    aim = new TalonSRX(0);
    shootL.setInverted(true);
    shootLF.setInverted(true);
    shootL.follow(shootR);
    shootLF.follow(shootR);
    shootRF.follow(shootR);
    shootL.setNeutralMode(NeutralMode.Brake);
    shootR.setNeutralMode(NeutralMode.Brake);
    aim.setNeutralMode(NeutralMode.Brake);
    aim.setSelectedSensorPosition(0,0,10);
    aim.selectProfileSlot(0, 0);
    aim.config_kF(0, .25, 10);//.2 - .3 to start off
    aim.config_kP(0, .2, 10);//((Desired speed * 1023)/TargetErrItShouldBeAtDesiredSpeed)
    aim.config_kI(0, .002, 10);//if too short, start w/ p/100 or p/10
    aim.config_kD(0, 2, 10);//if too fast, p*10 to start
  }

  public void shoot(double speed)
  {
    shootR.set(ControlMode.PercentOutput, speed);
  }
  public void aim(ControlMode mode, double value)
  {
    //for forward / pos move, simply state new pos, if below do pos-spos? testing required
    aim.set(mode, value);
  }
  public int aEnc()
  {
    return aim.getSelectedSensorPosition();
  }
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}

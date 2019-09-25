/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Shooter extends Subsystem {
  private CANSparkMax shootL;//left main motor
  private CANSparkMax shootR;//right main motor
  private CANEncoder encoderL;
  //private CANSparkMax shootLF;//Left follower motor
 // private CANSparkMax shootRF;//right follower motor
  //test 1
  public Shooter()
  { 
    shootL = new CANSparkMax(8, MotorType.kBrushless);// all shooting motors are brushless
    shootR = new CANSparkMax(9, MotorType.kBrushless);
    encoderL = new CANEncoder(shootL);
    encoderL.setPosition(0);
   // shootLF = new CANSparkMax(10, MotorType.kBrushless);
    //shootRF = new CANSparkMax(11, MotorType.kBrushless);
    
  //  shootLF.setInverted(true);
    shootL.follow(shootR, true);
    //shootLF.follow(shootR);
    //shootRF.follow(shootR);
    shootL.setIdleMode(IdleMode.kBrake);//same as neutral mode brake, makes motors hard stop rather than be in neutral
    shootR.setIdleMode(IdleMode.kBrake);
    //shootLF.setIdleMode(IdleMode.kBrake);
    //shootRF.setIdleMode(IdleMode.kBrake);
    /*shootR.setOpenLoopRampRate(1);// should be repleacment function to ramp towards full speed, test later after other functionalities work
    shootRF.setOpenLoopRampRate(1);
    shootL.setOpenLoopRampRate(1);
    shootLF.setOpenLoopRampRate(1);*/
  }

  public void shoot(double speed)
  {
    shootR.set(speed);//control mode is no longer a parameter, pid may be harder. 
  }
  public double tempCheck()
  {
    return shootR.getMotorTemperature();// finds the motor temperature, needs testing
  }
  public boolean isRunning()
  {
    return shootR.get() != 0;
  }
  public double encRPM()
  {
    return encoderL.getVelocity();
  }
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}

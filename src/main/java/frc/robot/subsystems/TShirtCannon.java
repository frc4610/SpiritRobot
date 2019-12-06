/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class TShirtCannon extends Subsystem {
  private Solenoid cannon;
  private double launchPulse = .5;//set the cannon to launch for .5 seconds
  public TShirtCannon()
  {
    cannon = new Solenoid(9, 7);//9 is pcm id, 7 is port the solenoid is plugged in to
    cannon.setPulseDuration(launchPulse);//set the cannon launch duration
  }
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public void launch()
  {
    cannon.startPulse();//initiate launch
  }
  public void retract()
  {
    cannon.set(false);//failsafe to turn off solenoid in worst case scenario
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}

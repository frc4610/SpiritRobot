/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;



import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Aim extends Command {
  private int sPos;
  private int pos;
  public Aim(int Pos) {
    requires(Robot.shooter);
    pos = Pos;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    sPos = Robot.shooter.aEnc();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(pos > sPos)
    {
      Robot.shooter.aim(ControlMode.Position, pos);
    }
    else if (sPos > pos)
    {
      Robot.shooter.aim(ControlMode.Position, pos-sPos);
    }
    else
    {
      Robot.shooter.aim(ControlMode.PercentOutput, 0);
    }
    
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(pos > sPos)
    {
    return Robot.shooter.aEnc() >= pos;
    }
    else if(pos < sPos)
    {
      return Robot.shooter.aEnc() <= pos;
    }
    else
    {
      return true;
    }

  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.shooter.aim(ControlMode.PercentOutput, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.shooter.aim(ControlMode.PercentOutput, 0);
  }
}

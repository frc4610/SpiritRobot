/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Drive extends Command {
  public Drive() {
    requires(Robot.driveBase);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.tank)
    {
      Robot.driveBase.move(-Robot.m_oi.CON.getRawAxis(1), -Robot.m_oi.CON.getRawAxis(3));//tank drive
    }
    else
    {
      Robot.driveBase.move(-(Robot.m_oi.CON.getRawAxis(3)-Robot.m_oi.CON.getRawAxis(2)), -(Robot.m_oi.CON.getRawAxis(3)+Robot.m_oi.CON.getRawAxis(2)));//arcade drive
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.driveBase.move(0,0);
  }
}

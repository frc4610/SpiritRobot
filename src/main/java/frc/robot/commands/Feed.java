/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class Feed extends Command {
  private int speed;//speed to shoot at
  private double runTime;//how long it has gone so far
  private double runToTime;// how long it should run
  //a runToTime of zero is running indefinitly
  public Feed(int Speed, double RunToTime) {
    requires(Robot.feeder);
    speed = Speed;
    runToTime = RunToTime;
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    runTime = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.feeder.reload(speed);
    runTime += .02;//"timer function"
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(runToTime == 0)
    {
      return false;// time of 0 means indefinite run
    }
    else
    {
      return runTime >= runToTime;//stops at set time
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.feeder.reload(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.feeder.reload(0);
  }
}

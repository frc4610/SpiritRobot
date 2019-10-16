/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.driveModeSwitch;
import frc.robot.commands.Feed;
import frc.robot.commands.Shoot;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  public Joystick CON = new Joystick(0);//driver
  public Joystick OP = new Joystick(1);//operator
  public Button op1 = new JoystickButton(OP, 1);
  public Button op2 = new JoystickButton(OP, 2);
  public Button op3 = new JoystickButton(OP, 3);
  public Button op4 = new JoystickButton(OP, 4);
  public Button op5 = new JoystickButton(OP, 5);
  public Button op6 = new JoystickButton(OP, 6);
  public Button op7 = new JoystickButton(OP, 7);
  public Button op8 = new JoystickButton(OP, 8);
  public OI()
  {
    //see various functions
    op1.whenPressed(new driveModeSwitch());
    op2.whileHeld(new Feed(1,0));
    op3.whileHeld(new Feed(-1,0));
    op4.whenPressed(new Feed(1,.2));
    op5.whileHeld(new Shoot(.4));
    op6.whileHeld(new Shoot(.6));
    op7.whileHeld(new Shoot(.8));
    op8.whileHeld(new Shoot(Robot.lSpeed()));
  }
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());
}

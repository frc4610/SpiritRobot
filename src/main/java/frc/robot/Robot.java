/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.Drive;
import frc.robot.subsystems.DriveBase;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Feed;
import frc.robot.subsystems.Shooter;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  //https://stackoverflow.com/questions/46877667/how-to-push-a-new-initial-project-to-github-using-vs-code
  public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
  public static DriveBase driveBase;
  public static Feed feeder;
  public Drive drive;
  private static double launchSpeed;
  public static Shooter shooter;
  public static OI m_oi;
  public static boolean tank;//tank drive if true, arcade if not
  Command m_autonomousCommand;
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    SmartDashboard.putNumber("Max Drive Speed", .5);
    SmartDashboard.putNumber("Launch Speed", 1);
    launchSpeed = 1;
    //basic setters
    feeder = new Feed();
    shooter = new Shooter();
    tank = true;//default to tank
    driveBase = new DriveBase();
    drive = new Drive();
    m_oi = new OI();
    //m_chooser.setDefaultOption("Default Auto", new ExampleCommand());
    //chooser.addOption("My Auto", new MyAutoCommand());
    //SmartDashboard.putData("Auto mode", m_chooser);
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() {
    //m_autonomousCommand = m_chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    /*if (m_autonomousCommand != null) {
      m_autonomousCommand.start();
    }*/
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    drive.start();//start driving
    driveBase.setMotors(.5);//s et max speed of 50%
    launchSpeed = 1;
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    if(SmartDashboard.getNumber("Max Drive Speed", .5) != .5)
    {
      driveBase.setMotors(SmartDashboard.getNumber("Max Drive Speed", .5));
    }
    if(SmartDashboard.getNumber("Launch Speed", 1) != 1)
    {
      launchSpeed = SmartDashboard.getNumber("Launch Speed", 1);
    }
    SmartDashboard.putBoolean("Tank Drive?", tank);//give the value to the driver/operator of control mode
    SmartDashboard.putNumber("Motor Temp (R)", shooter.tempCheck());// this should find the temperature of motors for overheating purposes.
    if(shooter.encRPM() > .05)
    {
      System.out.println(shooter.encRPM());
    }
    Scheduler.getInstance().run();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  public static void initMotor(TalonSRX motor, double peak)
  {
    motor.configPeakOutputForward(peak);
    motor.configPeakOutputReverse(-peak);
    motor.setNeutralMode(NeutralMode.Brake);
  }
  public static void initMotor(VictorSPX motor, double peak)
  {
    motor.configPeakOutputForward(peak);
    motor.configPeakOutputReverse(-peak);
    motor.setNeutralMode(NeutralMode.Brake);
  }
  public static double lSpeed()
  {
    double lSpeed = launchSpeed;
    return lSpeed;
  }

}

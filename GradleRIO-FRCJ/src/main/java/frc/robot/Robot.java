/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.libs.XboxController;
import com.ctre.phoenix.unmanaged.UnmanagedJNI; 
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.kauailabs.navx.frc.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {
	
	// Sets constants that describe the IDs of each motor controller
    private static final int RIGHT_DRIVE_SLAVE = 4; 
    private static final int RIGHT_DRIVE_MASTER = 3;
    private static final int LEFT_DRIVE_SLAVE = 2; 
	private static final int LEFT_DRIVE_MASTER = 1;
	
	// Instantiates each motor controller as a new CANSparkMax, using its ID as a parameter
    private CANSparkMax rightDriveSlave = new CANSparkMax(RIGHT_DRIVE_SLAVE, MotorType.kBrushless);
    private CANSparkMax rightDriveMaster = new CANSparkMax(RIGHT_DRIVE_MASTER, MotorType.kBrushless);
    private CANSparkMax leftDriveSlave = new CANSparkMax(LEFT_DRIVE_SLAVE, MotorType.kBrushless);
    private CANSparkMax leftDriveMaster = new CANSparkMax(LEFT_DRIVE_MASTER, MotorType.kBrushless);

	// Instantiates a new xbox controller, which has the ID of 0
	private XboxController xbox = new XboxController(0);
	
	// Instantiates a new NavX, which is a sensor that measures heading (direction/angles) and acceleration
    private AHRS navx = new AHRS();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
  }

  /**
   * This function is run once each time the robot enters autonomous mode.
   */
  @Override
  public void autonomousInit() {
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    UnmanagedJNI.JNI_FeedEnable(100); // Enable Phoenix CAN Devices for 100 Milliseconds (don't worry about this)
  }

  /**
   * This function is called once each time the robot enters teleoperated mode.
   */
  @Override
  public void teleopInit() {
	// setFollowerMode makes the slave motors copy exactly what the master motor does
	leftDriveSlave.follow(leftDriveMaster);
	rightDriveSlave.follow(rightDriveMaster);
  }

  /**
   * This function is called periodically during teleoperated mode.
   */
  @Override
  public void teleopPeriodic() {
    UnmanagedJNI.JNI_FeedEnable(100); // Enable Phoenix CAN Devices for 100 Milliseconds (some vmx-pi requirement-- don't worry about this)
    
    double leftSpeed = xbox.getMainY(); // gets left joystick value and sets it to "leftSpeed"
	double rightSpeed = xbox.getAltY(); // gets right joystick value and sets it to "rightSpeed"
	
	// Sets the speed of a motor controller by specifying a value between -1 (full power backward) and 1 (full power forward)
	leftDriveMaster.set(leftSpeed); // Sets the left motors to the speed specified by the left joystick
	rightDriveMaster.set(rightSpeed); // Sets the right motors to the speed specified by the right joystick

    double yaw = navx.getYaw(); // Gets the "yaw" angle, which measures the amount turned clockwise/counterclockwise by the robot
    SmartDashboard.putNumber("yaw", yaw); // Pushes the "yaw" angle to SmartDashboard, where we can read its display
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

}

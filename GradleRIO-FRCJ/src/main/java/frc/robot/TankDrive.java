/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
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
public class TankDrive extends TimedRobot {
    private static final int RightBackMotorID = 4; 
    private static final int RightFrontMotorID = 3;
    private static final int LeftBackMotorID = 2; 
    private static final int LeftFrontMotorID = 1;
    private CANSparkMax RightBackMotor;
    private CANSparkMax RightFrontMotor;
    private CANSparkMax LeftBackMotor;
    private CANSparkMax LeftFrontMotor;

    private DifferentialDrive m_drive;
    private Joystick Xbox_controller;
    private AHRS navx;
    //\\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/\/private MecanumDrive mcDrive;



  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {

    RightBackMotor = new CANSparkMax(RightBackMotorID, MotorType.kBrushless);
    RightFrontMotor = new CANSparkMax(RightFrontMotorID, MotorType.kBrushless);
    LeftBackMotor = new CANSparkMax(LeftBackMotorID, MotorType.kBrushless);
    LeftFrontMotor = new CANSparkMax(LeftFrontMotorID, MotorType.kBrushless);
   
    Xbox_controller = new Joystick(0);
    navx = new AHRS();

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
    UnmanagedJNI.JNI_FeedEnable(100); // Enable Phoenix CAN Devices for 100 Milliseconds
  }
  /**
   * This function is called once each time the robot enters teleoperated mode.
   */
  @Override
  public void teleopInit() {
    SpeedControllerGroup leftMotors = new SpeedControllerGroup(LeftBackMotor, LeftFrontMotor);
    SpeedControllerGroup rightMotors = new SpeedControllerGroup(RightBackMotor, RightFrontMotor);
    m_drive = new DifferentialDrive(leftMotors, rightMotors);
  }

  /**
   * This function is called periodically during teleoperated mode.
   */
  @Override
  public void teleopPeriodic() {
    UnmanagedJNI.JNI_FeedEnable(100); // Enable Phoenix CAN Devices for 100 Milliseconds (some vmx-pi requirement)
    
    double leftSpeed = -Xbox_controller.getRawAxis(1);//left joystick
    double rightSpeed = -Xbox_controller.getRawAxis(5);//right joystick

    if (Xbox_controller.getRawButton(2)) {
      navx.zeroYaw();
    }

    double yaw = navx.getYaw();

    SmartDashboard.putNumber("yaw", yaw);

    m_drive.tankDrive(leftSpeed*leftSpeed*Math.signum(leftSpeed), rightSpeed*rightSpeed*Math.signum(rightSpeed));

  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
    
  }

  public double hype (double x, double y) {
    return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
  }

}

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.HardwareAdapter;

public class Drivetrain extends Subsystem implements HardwareAdapter, Constants {
	private AHRS gyro = new AHRS();

	// This is the CONSTRUCTOR of the Drivetrain class
	// It has the exact same name (including capitalization!) as the class
	// This method is called when this class is INSTANTIATED
	public Drivetrain() {
		setFollowers();
	}

	// Method to arcade drive the robot
	public void arcadeDrive(double throttle, double heading) {
		leftDriveMaster.set(throttle - heading);
		rightDriveMaster.set(throttle + heading);
	}

	// Sets followers/slaves, meaning slave motors copy exactly what the master does
	public void setFollowers() {
		leftDriveSlave.follow(leftDriveMaster);
		rightDriveSlave.follow(righttDriveMaster);
	}

	public double getYaw() {
		return gyro.getYaw();
	}

	public void zeroYaw() {
		gyro.resetYaw();
	}

  	@Override
  	public void initDefaultCommand() {
  	 	// Set the default command for a subsystem here.
 	  	// setDefaultCommand(new MySpecialCommand());
  	}
}	

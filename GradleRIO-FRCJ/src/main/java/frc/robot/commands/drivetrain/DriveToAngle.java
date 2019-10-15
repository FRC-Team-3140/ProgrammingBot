package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 * Turns the robot until it reaches a certain angle
 */
public class DriveToAngle extends Command {
  double angle;

  public DriveToAngle(double angleToTurn) {
	angle = angleToTurn;
    requires(Robot.dt);
  }

  // Called just before this Command runs the first time. Resets gyro sensor
  @Override
  protected void initialize() {
	  Robot.dt.resetYaw();
  }

  // Called repeatedly when this Command is scheduled to run. 
  // Moves the one side of the wheel forward and the other side backward, depending on sign of the angle
  @Override
  protected void execute() {
	  if(angle >= 0) {
		  Robot.dt.arcadeDrive(0, -.5);
	  } 
	  else 
	  	Robot.dt.arcadeDrive(0, -0.5);
  }

  // Make this return true when it is time for the command to stop.
  // When it returns true, execute will stop running and end will run once.
  // Returns true if the actual angle of the robot is close to the targeted angle (5 degrees of error)
  @Override
  protected boolean isFinished() {
	  return Math.abs(Robot.dt.getYaw() - angle) < 5;
  }

  // Called once after timeout. Stops the robot.
  @Override
  protected void end() {
	  Robot.dt.arcadeDrive(0,0);
  }
}

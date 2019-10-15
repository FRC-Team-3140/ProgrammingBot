package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.TimedCommand;

/**
 * This command drives the robot straight forward for a specified period of time
 */
public class DriveForwardTimed extends TimedCommand {

  public DriveForwardTimed(double timeout) {
    super(timeout);
	requires(Robot.dt);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
	  Robot.dt.arcadeDrive(.7, 0);
  }
}

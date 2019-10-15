/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drivetrain;

import edu.wpi.first.wpilibj.command.Command;

public class ArcadeDrive extends Command {
  public ArcadeDrive() {
    requires(Robot.dt);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
	  Robot.dt.arcadeDrive(Robot.oi.xbox.getMainY(), Robot.oi.xbox.getAltX());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

}

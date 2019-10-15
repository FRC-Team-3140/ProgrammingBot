package frc.robot;

public interface HardwareAdapter extends Constants {

	public CANSparkMax rightDriveMaster = new CANSparkMax(RIGHT_DRIVE_MASTER, MotorType.kBrushless);
	public CANSparkMax leftDriveMaster = new CANSparkMax(LEFT_DRIVE_MASTER, MotorType.kBrushless);
	public CANSparkMax rightDriveSlave = new CANSparkMax(RIGHT_DRIVE_SLAVE, MotorType.kBrushless);
	public CANSparkMax leftDriveSlave = new CANSparkMax(LEFT_DRIVE_SLAVE, MotorType.kBrushless);

	public AHRS gyro = new AHRS();

}

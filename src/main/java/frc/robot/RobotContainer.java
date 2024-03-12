package frc.robot;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.robot.commands.teleop.TeleopDrive;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class RobotContainer {
	private final Drivetrain drivetrain = new Drivetrain();
	private final Intake intake = new Intake();
	private SendableChooser<String> autoChooser;

	public RobotContainer() {
		// Configure the button bindings
		OI.configureButtonBindings();

		// Add all autos to the auto chooser
		Path autoPath = Filesystem.getDeployDirectory().toPath().resolve("/home/lvuser/deploy/pathplanner/");

		autoChooser = new SendableChooser<>();

		try (Stream<Path> list = Files.list(autoPath)) {
			list.filter(Files::isRegularFile)
					.map(Path::getFileName)
					.map(Path::toString)
					.forEach((String autoFileName) -> {
						autoChooser.addOption(autoFileName, autoFileName);
					});
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Configure default commands
		drivetrain.setDefaultCommand(new TeleopDrive(drivetrain, intake));
		intake.setDefaultCommand(new TeleopDrive(drivetrain, intake));
	}
}

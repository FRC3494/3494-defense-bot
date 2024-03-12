package frc.robot.subsystems;

import com.revrobotics.CANSparkLowLevel;
import com.revrobotics.CANSparkMax;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Intake extends SubsystemBase {
    CANSparkMax intakeMotor;

    public Intake() {
        intakeMotor = new CANSparkMax(Constants.Subsystems.Intake.INTAKE_MOTOR_PORT,
                CANSparkLowLevel.MotorType.kBrushless);
    }

    @Override
    public void periodic() {
    }

    public void setPower(double intakePower) {
        intakeMotor.set(Constants.Subsystems.Intake.INTAKE_SPEED * intakePower);
    }
}

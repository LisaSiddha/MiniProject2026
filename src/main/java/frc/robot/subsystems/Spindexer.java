package frc.robot.subsystems;
 
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SpindexerConstants;
 
public class Spindexer extends SubsystemBase {
  private final TalonFX motor = new TalonFX(SpindexerConstants.SPINDEXER_MOTOR_ID);
 
  public Spindexer() {
    TalonFXConfiguration config = new TalonFXConfiguration();
    config.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    config.CurrentLimits.StatorCurrentLimit = SpindexerConstants.CURRENT_LIMIT_AMPS;
    config.CurrentLimits.StatorCurrentLimitEnable = true;
    motor.getConfigurator().apply(config); 
  }
 
  public void setSpeed(double speed) {
    motor.set(speed);
  }
 
  public void setStop() {
    motor.stopMotor();
  }
 
  public Command spinCommand() {
    return runEnd(() -> setSpeed(SpindexerConstants.INDEX_SPEED), this::setStop);
  }
 
  public Command reverseCommand() {
    return runEnd(() -> setSpeed(SpindexerConstants.REVERSE_SPEED), this::setStop);
  }
 
  @Override
  public void periodic() {
    SmartDashboard.putNumber("Spindexer/Output", motor.getDutyCycle().getValueAsDouble());
    SmartDashboard.putNumber("Spindexer/CurrentAmps", motor.getStatorCurrent().getValueAsDouble());
  }
}
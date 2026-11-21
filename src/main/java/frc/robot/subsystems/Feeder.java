
package frc.robot.subsystems;
 
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.FeederConstants;
 
public class Feeder extends SubsystemBase {
  private final TalonFX motor = new TalonFX(FeederConstants.FEEDER_MOTOR_ID);
 
  public Feeder() {
    TalonFXConfiguration settings = new TalonFXConfiguration();
    settings.MotorOutput.NeutralMode = NeutralModeValue.Brake;
    settings.CurrentLimits.StatorCurrentLimit = FeederConstants.CURRENT_LIMIT_AMPS;
    settings.CurrentLimits.StatorCurrentLimitEnable = true;
    motor.getConfigurator().apply(settings); 
  }
 
  public void setVolts(double volts) {
    motor.setVoltage(volts);
  }
 
  public void feederStop() {
    motor.stopMotor();
  }
 
  
  public Command feederFeed() {
    return startEnd(() -> setVolts(FeederConstants.FEEDER_MOTOR_VOLTS), this::feederStop);
  }
 
 
  public Command feederReverse() {
    return startEnd(() -> setVolts(FeederConstants.FEEDER_REVERSE_VOLTS), this::feederStop);
  }
}
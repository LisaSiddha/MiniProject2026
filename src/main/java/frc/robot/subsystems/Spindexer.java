package frc.robot.subsystems;
 
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SpindexerConstants;
 
public class Spindexer extends SubsystemBase {
  private final SparkMax motor = new SparkMax(SpindexerConstants.SPINDEXER_MOTOR_ID, MotorType.kBrushless);
 
  public Spindexer() {
    SparkMaxConfig config = new SparkMaxConfig();
    config.idleMode(IdleMode.kBrake).smartCurrentLimit(SpindexerConstants.CURRENT_LIMIT_AMPS);
    motor.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
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
    SmartDashboard.putNumber("Spindexer/Output", motor.getAppliedOutput());
    SmartDashboard.putNumber("Spindexer/CurrentAmps", motor.getOutputCurrent());
  }
}
 
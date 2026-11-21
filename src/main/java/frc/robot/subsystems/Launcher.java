package frc.robot.subsystems;
 
import java.util.function.DoubleSupplier;
 
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.NeutralOut;
import com.ctre.phoenix6.controls.VelocityTorqueCurrentFOC;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
 
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.LauncherConstants;
 

public class Launcher extends SubsystemBase {
            
 
  private final TalonFX leftMotor = new TalonFX(LauncherConstants.LAUNCHER_LEADER_ID);
  private final TalonFX rightMotor = new TalonFX(LauncherConstants.LAUNCHER_FOLLOWER_ID);
 
  private final VelocityTorqueCurrentFOC spinAtVelocity = new VelocityTorqueCurrentFOC(0); 
  private final NeutralOut turnOff = new NeutralOut();                                 
 
  private double targetRps = 0;
 
  public Launcher() {
    TalonFXConfiguration settings = new TalonFXConfiguration();
    settings.MotorOutput.NeutralMode = NeutralModeValue.Coast;                      
    settings.TorqueCurrent.PeakForwardTorqueCurrent = 80;
    settings.TorqueCurrent.PeakReverseTorqueCurrent = 0;           
    settings.CurrentLimits.StatorCurrentLimit = 80;               
    settings.CurrentLimits.StatorCurrentLimitEnable = true;
 
   
    settings.MotorOutput.Inverted = InvertedValue.CounterClockwise_Positive;
    leftMotor.getConfigurator().apply(settings);
    settings.MotorOutput.Inverted = InvertedValue.Clockwise_Positive;
    rightMotor.getConfigurator().apply(settings);
  }
 
  private double getRealRps() {
    return leftMotor.getVelocity().getValueAsDouble();
  }
 
 
  public boolean atSpeed() {
    return targetRps > 0 && Math.abs(getRealRps() - targetRps) < LauncherConstants.CLOSE_ENOUGH_RPS;
  }
 
  
  public Command launch(DoubleSupplier distanceMeters) {
    return startEnd(
        () -> {
          targetRps = LauncherConstants.BASE_RPS + LauncherConstants.RPS_PER_METER * distanceMeters.getAsDouble();
          leftMotor.setControl(spinAtVelocity.withVelocity(targetRps));
          rightMotor.setControl(spinAtVelocity.withVelocity(targetRps));
        },
        () -> {
          targetRps = 0;
          leftMotor.setControl(turnOff);
          rightMotor.setControl(turnOff);
        });
  }
 
  @Override
  public void periodic() {
    SmartDashboard.putNumber("Launcher target rps", targetRps);
    SmartDashboard.putNumber("Launcher real rps", getRealRps());
  }
}

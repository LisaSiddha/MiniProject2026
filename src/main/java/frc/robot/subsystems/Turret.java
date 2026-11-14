package frc.robot.subsystems;
 
import java.util.function.Supplier;
 
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;
 
import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.TurretConstants;

public class Turret extends SubsystemBase {
 
 
  private static final Translation2d BLUE_HUB = new Translation2d(0.0, 0.0); //TODOTODO!!
  private static final Translation2d RED_HUB = new Translation2d(0.0, 0.0); //TODOTODO!!
 
  private final TalonFX motor = new TalonFX(TurretConstants.TURRET_ID);
  private final MotionMagicVoltage goToSpot = new MotionMagicVoltage(0); 
 
  private double targetDegrees = 0;
 
  public Turret() {
    TalonFXConfiguration settings = new TalonFXConfiguration();
    settings.MotorOutput.NeutralMode = NeutralModeValue.Brake;         
    settings.Feedback.SensorToMechanismRatio = TurretConstants.GEARING;                
    settings.Slot0.kP = 40.0;                                          
    settings.MotionMagic.MotionMagicCruiseVelocity = 1.0;             
    settings.MotionMagic.MotionMagicAcceleration = 3.0;               
   
    settings.SoftwareLimitSwitch.ForwardSoftLimitEnable = true;
    settings.SoftwareLimitSwitch.ForwardSoftLimitThreshold = TurretConstants.RIGHT_LIMIT_DEGREES / 360.0;
    settings.SoftwareLimitSwitch.ReverseSoftLimitEnable = true;
    settings.SoftwareLimitSwitch.ReverseSoftLimitThreshold = TurretConstants.LEFT_LIMIT_DEGREES / 360.0;
    motor.getConfigurator().apply(settings);
 
    motor.setPosition(0); 
  }
 
  private boolean onRedAlliance() {
    return DriverStation.getAlliance().orElse(Alliance.Blue) == Alliance.Red;
  }
 
  private Translation2d getHub() {
    if (onRedAlliance()) {
      return RED_HUB;
    }
    return BLUE_HUB;
  }
 
  private double getDegrees() {
    return motor.getPosition().getValueAsDouble() * 360.0; //degrees
  }
 
 
  public double distanceToHub(Pose2d robotPose) {
    return robotPose.getTranslation().getDistance(getHub());
  }
 
  
  public boolean atTarget() {
    return Math.abs(getDegrees() - targetDegrees) < TurretConstants.CLOSE_ENOUGH_DEGREES;
  }
 
  
  public Command trackHub(Supplier<Pose2d> robotPose) {
    return run(() -> {
      Pose2d pose = robotPose.get();
      Translation2d hub = getHub();
 
      double hubX = hub.getX() - pose.getX();
      double hubY = hub.getY() - pose.getY();
      double hubDirection = Math.toDegrees(Math.atan2(hubY, hubX));     // direction to the hub on the field
 
      targetDegrees = hubDirection - pose.getRotation().getDegrees();   
      targetDegrees = MathUtil.inputModulus(targetDegrees, -180, 180);  
      targetDegrees = MathUtil.clamp(targetDegrees, TurretConstants.LEFT_LIMIT_DEGREES, TurretConstants.RIGHT_LIMIT_DEGREES);
 
      motor.setControl(goToSpot.withPosition(targetDegrees / 360.0));   
    });
  }
 
  @Override
  public void periodic() {
    SmartDashboard.putNumber("Turret degrees", getDegrees());
    SmartDashboard.putNumber("Turret target degrees", targetDegrees);
  }
}
 
// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.units.measure.Voltage;
import frc.robot.generated.TunerConstants;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  private Constants() {}
 
  public static final class OperatorConstants {
    public static final int DRIVER_CONTROLLER_PORT = 0;
  }
 
  public static final class SpindexerConstants {
    public static final int SPINDEXER_MOTOR_ID = 30; // TODO: set to CAN ID
    public static final int CURRENT_LIMIT_AMPS = 30;
    public static final double INDEX_SPEED = 0.5;
    public static final double REVERSE_SPEED = -0.3;
  }

  public static final class FeederConstants{
    public static final int CANRANGE_CAN_ID = 25;
    public static final double IS_HOPPER_EMPTY_BUFFER_TIME = 1.1; // 2 TODO: try to shorten
    public static final int FEEDER_MOTOR_ID = 1; //TODO
    public static final double CURRENT_LIMIT_AMPS = 1.2;
    public static final double FEEDER_MOTOR_VOLTS = 1.2;
    public static final double FEEDER_REVERSE_VOLTS = 1.2;
 
    
  }

  public static final class IntakeConstants{
  
    public static final double ROLLER_VOLTS = 3.0; // tune this later
    public static final double PIVOT_GEARING = (38.0 / 12.0) * (50.0 / 16.0) * (44.0 / 12.0);

     //pivot degs
    public static final double STOWED_DEGS = 60;
    public static final double DEPLOYED_DEGS = 120;
    //public static final double PIVOT_GEAR_RATIO = 34.5;   // 34.5:1 reduction
    //public static final double DEGS_TO_MOTOR_ROTS = PIVOT_GEAR_RATIO / 360.0;
 
  //canids
    public static final int PIVOT_LEFT_ID = 30;
   // public static final int PIVOT_RIGHT_ID = 31;
    public static final int ROLLER_FOLLOWER_ID = 32;
    public static final int ROLLER_LEADER_ID = 31;


 
  }

  public static final class TurretConstants{
    public static final int TURRET_ID = 26;                       // TODO
    public static final double GEARING = 30.0;                    // TODO
    public static final double LEFT_LIMIT_DEGREES = -200;         // TODO
    public static final double RIGHT_LIMIT_DEGREES = 200;         // TODO
    public static final double CLOSE_ENOUGH_DEGREES = 2.0;       
  
  }

  public static final class LauncherConstants{
    public static final int LAUNCHER_LEFT_ID = 23;                         // TODO
    public static final int LAUNCHER_RIGHT_ID = 24;                        // TODO
    public static final double BASE_RPS = 35;
    public static final double RPS_PER_METER = 7;
    public static final double CLOSE_ENOUGH_RPS = 2.0; 
  }

  public static final class DriveConstants{
    public static final double DRIVE_GEARING = 6.75;
    public static final double STEER_GEARING = 150.0 / 7.0;
    public static final double WHEEL_DISTANCE_PER_TURN = Math.PI * Units.inchesToMeters(4.0); 

    public static final double MAX_SPEED = 4.5;                  // meters per second. TODO
    public static final double MAX_TURN_SPEED = 4.7;             // radians per second. TODO
    public static final double ROBOT_WIDTH = Units.inchesToMeters(22.5);  // wheel to wheel, left to right. TODO
    public static final double ROBOT_LENGTH = Units.inchesToMeters(22.5); // wheel to wheel, front to back. TODO
    public static final int GYRO_ID = 10; 

   
    private static final double DEADBAND = 0.1;
    //private static final double ANGLE_KP = 7.0;
    //private static final double ANGLE_KD = 0.4;
    private static final double ANGLE_MAX_VELOCITY = Units.degreesToRadians(540);
    private static final double ANGLE_MAX_ACCELERATION = Units.degreesToRadians(720);
    private static final double FF_START_DELAY = 2.0; // Secs
    private static final double FF_RAMP_RATE = 0.1; // Volts/Sec
    private static final double WHEEL_RADIUS_MAX_VELOCITY = 0.25; // Rad/Sec
    private static final double WHEEL_RADIUS_RAMP_RATE = 0.05; // Rad/Sec^2


    
  }
}
 

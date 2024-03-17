// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

public class Robot extends TimedRobot {
  private final CANSparkMax m_leftDrive2 = new CANSparkMax(1, MotorType.kBrushed);
  private final CANSparkMax m_leftDrive1 = new CANSparkMax(2, MotorType.kBrushed);
  private final CANSparkMax m_rightDrive1 = new CANSparkMax(3, MotorType.kBrushed);
  private final CANSparkMax m_rightDrive2 = new CANSparkMax(4, MotorType.kBrushed);
  private static final boolean REVERSED = false; 

  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftDrive1, m_rightDrive1);
  private final XboxController m_Controller = new XboxController(0);
  private final Timer m_timer = new Timer();



  @Override
  public void robotInit() {
    m_leftDrive1.restoreFactoryDefaults();
    m_leftDrive2.restoreFactoryDefaults();
    m_rightDrive1.restoreFactoryDefaults();
    m_rightDrive2.restoreFactoryDefaults();

    m_leftDrive2.follow(m_leftDrive1);
    m_leftDrive1.setInverted(true);
    m_rightDrive2.follow(m_rightDrive1);
    m_rightDrive1.setInverted(true);

  
}
    
  

  @Override
  public void autonomousInit() {
    m_timer.reset();
    m_timer.start();
  }

  @Override
  public void autonomousPeriodic() 
  {
   if (m_timer.get() > 6.2) {
      m_robotDrive.stopMotor(); // stop robot
    }
    
    else if (m_timer.get() > 0.0) {
      m_robotDrive.arcadeDrive(0.5, 0); // drive forwards half speed
    } 
  }
  
  //** This function is called once each time the robot enters teleoperated mode. */
  @Override
  public void teleopInit()
  {
  }

  /** This function is called periodically during teleoperated mode. */

  @Override
  public void teleopPeriodic() {
  m_robotDrive.arcadeDrive(m_Controller.getRawAxis(2), m_Controller.getRawAxis(1));

  
  }


  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }
  }

  
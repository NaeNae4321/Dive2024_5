package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class RobotOdometry {
    public DcMotor horizontalEncoder;
    public DcMotor verticalEncoder;

    private HardwareMap hardwareMap;

    //Constants
    final static double R = 0;

    public void initEncoders()
    {
        horizontalEncoder = hardwareMap.dcMotor.get("frontLeftMotor");
        verticalEncoder = hardwareMap.dcMotor.get("frontRightMotor");

        horizontalEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        horizontalEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        verticalEncoder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        verticalEncoder.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void resetEncoders()
    {

    }
}

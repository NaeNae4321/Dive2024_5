package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.PinpointDrive;

import org.firstinspires.ftc.teamcode.tuning.TuningOpModes;

@Autonomous

public class AutoRoadRunnerTest extends LinearOpMode {

    @Override

    public void runOpMode() {
        Pose2d beginPose = new Pose2d(0, 0, Math.toRadians(180));
        if (TuningOpModes.DRIVE_CLASS.equals(PinpointDrive.class)) {
            PinpointDrive drive = new PinpointDrive(hardwareMap, beginPose);

            waitForStart();

            Actions.runBlocking(
                    drive.actionBuilder(beginPose)
                            .setTangent(0)
                            .splineToConstantHeading(new Vector2d(55.0, -55.0),-Math.PI / 2)
                            .splineToConstantHeading(new Vector2d(3.0, -104.5), Math.PI)
                            .turn(Math.toRadians(180))
                            .waitSeconds(1)
                            .strafeToLinearHeading(new Vector2d(50.0, -104.5), Math.toRadians(180))
                            .strafeToLinearHeading(new Vector2d(50.0, 0.0), Math.toRadians(180))
                            .strafeToLinearHeading(new Vector2d(0.0, 0.0), Math.toRadians(180))
                            .build());

        }
    }
}
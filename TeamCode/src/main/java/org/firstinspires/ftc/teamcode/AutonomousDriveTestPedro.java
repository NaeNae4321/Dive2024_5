package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierCurve;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierPoint;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.PathChain;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Point;
import org.firstinspires.ftc.teamcode.pedroPathing.util.Timer;

@Autonomous
public class AutonomousDriveTestPedro extends OpMode {
    private Follower follower;
    private Timer pathTimer;
    private int pathState;

    private Pose startPose = new Pose(0, 0, Math.toRadians(180));
    private Pose point1 = new Pose(55, -55,Math.toRadians(180));
    private Pose point2 = new Pose(5.2, -103.9,Math.toRadians(180));

    private PathChain driveToBox, driveBack;

    public void BuildPaths()
    {
        driveToBox = follower.pathBuilder()
                .addPath(new BezierCurve(new Point(startPose), new Point(point1.getX(), startPose.getY()), new Point(point1)))
                .setLinearHeadingInterpolation(startPose.getHeading(), point1.getHeading())
                .addPath(new BezierCurve(new Point(point1), new Point(point1.getX(), point2.getY()), new Point(point2)))
                .setLinearHeadingInterpolation(point1.getHeading(), point2.getHeading())
                .build();
    }

    public void AutoPathUpdate()
    {
        switch (pathState)
        {
            case 1:
                follower.followPath(driveToBox, true);
                setPathState(2);
                break;

            case 2:
                if(!follower.isBusy())
                {
                    setPathState(3);
                }
                break;

            case 3:
                break;
        }
    }

    @Override
    public void loop() {
        // These loop the actions and movement of the robot
        follower.update();
        AutoPathUpdate();

        // Feedback to Driver Hub
        telemetry.addData("path state", pathState);
        telemetry.addData("x", follower.getPose().getX());
        telemetry.addData("y", follower.getPose().getY());
        telemetry.addData("heading", follower.getPose().getHeading());
        telemetry.addData("follower is busy", follower.isBusy());
        telemetry.update();
    }

    @Override
    public void init() {
        pathTimer = new Timer();

        follower = new Follower(hardwareMap);
        follower.setStartingPose(startPose);
    }

    @Override
    public void start() {
        BuildPaths();

        setPathState(1);
    }

    public void setPathState(int pState) {
        pathState = pState;
        pathTimer.resetTimer();
        AutoPathUpdate();
    }
}

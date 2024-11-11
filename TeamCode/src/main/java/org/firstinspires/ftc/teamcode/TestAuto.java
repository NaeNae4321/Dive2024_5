package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.pedroPathing.follower.Follower;
import org.firstinspires.ftc.teamcode.pedroPathing.localization.Pose;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.BezierLine;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.PathChain;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Point;
import org.firstinspires.ftc.teamcode.pedroPathing.pathGeneration.Path;

@Autonomous
public class TestAuto extends OpMode {

    private Follower follower;

    private PathChain move;

    public void BuildPaths()
    {
        Pose start = new Pose(0, 0, Math.toRadians(90));
        Pose end = new Pose(10, 0, Math.toRadians(90));

        Path line1 = new Path(new BezierLine(new Point(start), new Point(end)));
        line1.setLinearHeadingInterpolation(start.getHeading(), end.getHeading());
        line1.setPathEndTimeoutConstraint(0);


        move = follower.pathBuilder()
                .addPath(line1)
                .build();
    }

    @Override
    public void loop()
    {
        follower.update();
        follower.followPath(move);
    }

    @Override
    public void init() {
        follower = new Follower(hardwareMap);
        follower.setStartingPose(new Pose(0,0,0));
    }

    @Override
    public void start() {
        BuildPaths();
    }
}

package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Dimension;
import java.awt.Toolkit;

public class ScratchClass {
    final static Pose2d startPos = new Pose2d(-34, 65, 0);
    final static Pose2d dropOffPos = new Pose2d(-27, 23.5, Math.toRadians(180));
    final static Pose2d carouselSpinPos = new Pose2d(-61, 51, Math.toRadians(270));
    final static Pose2d parkPos = new Pose2d(-60, 35.5, Math.toRadians(90));
    final static Pose2d warehouseCrossPos = new Pose2d(11, 46, 0);

    static final MeepMeep meepMeep;
    static final RoadRunnerBotEntity BlueCRPath;

    static {
        final Dimension windowSize = Toolkit.getDefaultToolkit().getScreenSize();
        if (windowSize.height < windowSize.width) {
            meepMeep = new MeepMeep(windowSize.height);
        } else {
            meepMeep = new MeepMeep(windowSize.width);
        }

        BlueCRPath = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(startPos)
                                .lineToConstantHeading(parkPos.plus(new Pose2d(0, 5)).vec())
                                // Cross Box
                                .splineToConstantHeading(parkPos.plus(new Pose2d(0, -5)).vec(), 0)
                                //Approach Goal
                                .splineToSplineHeading(dropOffPos, 0)
                                // Cross Box
                                .lineToLinearHeading(new Pose2d(parkPos.getX(), parkPos.getY() - 5))

                                // To Carousel Spinner
                                .lineToLinearHeading(carouselSpinPos)
                                .back(2)
                                .lineToLinearHeading(warehouseCrossPos)
                                .forward(36)
                                .waitSeconds(1000)
                                .build()
                );
    }

    public static void main(String[] args) {
        meepMeep.setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(BlueCRPath)
                .start();
    }

}

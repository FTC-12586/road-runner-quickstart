package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class RedWarehouseMeepMeep {

    final static Pose2d startPos = new Pose2d(7, -65, 0);
    final static Pose2d dropOffPos= new Pose2d(-12, -38, Math.toRadians(-90));
    final static Pose2d whEntryPos = new Pose2d(20, -65, Math.toRadians(0));




    final static MeepMeep meepMeep = new MeepMeep(800);

    final static RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
            .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
            .followTrajectorySequence(drive ->
                    // this first section of movement is the initial block placement.

                    drive.trajectorySequenceBuilder(startPos)
                            .strafeLeft(5)
                            .lineToSplineHeading(dropOffPos)
                            //This path should not be replicated for additional freight since it is inefficient.

                            /* the following sequence is the path our robot takes to
                            do cycles of freight. Because this would ideally be looped in our
                            opmode, this section should be a pre-built Trajectory that is looped in Run()
                              */
                            .waitSeconds(1)
                            .addDisplacementMarker(() ->{
                                //subsystem movement in here
                            })
                            .splineToSplineHeading(whEntryPos, Math.toRadians(0))

                            .forward(10)
                            // this is where we will pick up additional freight
                            .addDisplacementMarker(() ->{
                                //subsystem movement in here
                            })
                            .back(10)
                            .setReversed(true)
                            .splineToSplineHeading(dropOffPos, Math.toRadians(90))
                            .setReversed(false)

                            .build()


            );


    public static void main(String[] args) {



        meepMeep.setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}

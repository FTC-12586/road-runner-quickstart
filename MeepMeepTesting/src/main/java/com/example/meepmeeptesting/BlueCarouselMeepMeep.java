package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class BlueCarouselMeepMeep {


    final static Pose2d startPos = new Pose2d(-34, 65, 0);
    final static Pose2d dropOffPos= new Pose2d(-12, 38, Math.toRadians(90));
    final static Pose2d carouselSpinPos = new Pose2d(-61, 51, Math.toRadians(90));
    final static Pose2d parkPos = new Pose2d(-60, 35.5, Math.toRadians(90));

    static final MeepMeep meepMeep = new MeepMeep(800);

    static final RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
            // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
            .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10)
            .followTrajectorySequence(drive ->
                    drive.trajectorySequenceBuilder(startPos)
                            .lineToLinearHeading(dropOffPos)
                            .addDisplacementMarker(() ->{
                                //subsystem movement in here
                            })
                            .splineToLinearHeading(carouselSpinPos, Math.toRadians(180))
                            .addDisplacementMarker(() ->{
                                //subsystem movement in here
                            })
                            .lineToLinearHeading(parkPos)
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
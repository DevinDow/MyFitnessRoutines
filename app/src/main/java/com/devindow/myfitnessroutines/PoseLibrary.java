package com.devindow.myfitnessroutines;

import java.util.Dictionary;
import java.util.Hashtable;

/**
 * Created by Devin on 1/29/2018.
 */

public class PoseLibrary {


	// Constants
	public static final String DONE = "Done!";
	public static final String REST = "Rest";
	public static final String PUSH_UPS = "Push-Ups";
	public static final String JUMPING_JACKS = "Jumping Jacks";
	public static final String WALL_SIT = "Wall Sit";
	public static final String CRUNCHES = "Crunches";
	public static final String STEP_UPS = "Step-Ups";
	public static final String SQUATS = "Squats";
	public static final String CHAIR_DIPS = "Chair Dips";
	public static final String PLANK = "Plank";
	public static final String HIGH_KNEES = "High Knees";
	public static final String LUNGES = "Lunges";
	public static final String PUSH_UP_ROTATE = "Push-Up & Rotate";
	public static final String SIDE_PLANK = "Side Plank";
	public static final String DOWN_DOG = "Down Dog";
	public static final String LOTUS = "Lotus";


	// Public Static Fields
	public static Dictionary<String, Pose> poses = new Hashtable<String, Pose>();


	// Public Static Methods
	public static void generatePoses() {
		Pose pose;

		// Done
		pose = new FrontalPose(DONE, Category.NONE);
		pose.rHandX = 19; pose.lHandX = -19; pose.rHandY = pose.lHandY = 73;
		pose.rElbowX = 17f; pose.lElbowX = -17f; pose.rElbowY = pose.lElbowY = 59f;
		pose.rFootX = 4; pose.lFootX = -4;
		poses.put(pose.name, pose);

		// Jumping Jacks
		pose = new FrontalPose(JUMPING_JACKS, Category.CARDIO);
		pose.rHandX = -25; pose.lHandX = 25; pose.rHandY = pose.lHandY = 70;
		pose.rFootX = -15; pose.lFootX = 15;
		poses.put(pose.name, pose);


		// Wall Sit
		pose = new ProfilePose(WALL_SIT, Category.LIFTING);
		pose.headX = pose.waistX = pose.lHandX = pose.rHandX = -pose.legSegmentLength/2;
		pose.waistY = pose.rKneeY = pose.lKneeY = pose.rHandY = pose.lHandY = pose.legSegmentLength;
		pose.headY = pose.waistY + pose.torsoLength + pose.torsoThickness/2 + pose.headSize/2;
		pose.rFootX = pose.lFootX = pose.rKneeX = pose.lKneeX = -pose.waistX;
		pose.rFootY = pose.lFootY = 0;
		pose.prop = new Wall(pose.waistX - pose.torsoThickness/2);
		poses.put(pose.name, pose);

		// Squats
		pose = new ProfilePose(SQUATS, Category.LIFTING);
		pose.headX = pose.waistX = -pose.legSegmentLength/2;
		pose.waistY = pose.rKneeY = pose.lKneeY = pose.legSegmentLength;
		pose.headY = pose.waistY + pose.torsoLength + pose.torsoThickness/2 + pose.headSize/2;
		pose.rHandX = pose.lHandX = pose.headX + pose.armSegmentLength * 2;
		pose.generateCoords();
		pose.rHandY = pose.lHandY = pose.collarY;
		pose.rFootX = pose.lFootX = pose.rKneeX = pose.lKneeX = -pose.waistX;
		pose.rFootY = pose.lFootY = 0;
		poses.put(pose.name, pose);

		// Chair Dips
		pose = new ProfilePose(CHAIR_DIPS, Category.LIFTING);
		pose.headX = pose.waistX = -pose.legSegmentLength/2;
		pose.waistY = pose.rKneeY = pose.lKneeY = pose.legSegmentLength;
		pose.headY = pose.waistY + pose.torsoLength + pose.torsoThickness/2 + pose.headSize/2;
		pose.rFootX = pose.lFootX = pose.rKneeX = pose.lKneeX = -pose.waistX;
		pose.rFootY = pose.lFootY = 0;
		float chairX = pose.waistX - pose.torsoThickness/2 - 2;
		float chairY = pose.legSegmentLength;
		pose.prop = new Ledge(chairX, chairX - 12, chairY);
		pose.rHandX = pose.lHandX = pose.rElbowX = pose.lElbowX = chairX - pose.armThickness/2;
		pose.rHandY = pose.lHandY = chairY + pose.armThickness/2;
		pose.rElbowY = pose.lElbowY = chairY + pose.armThickness/2 + pose.armSegmentLength;
		poses.put(pose.name, pose);

		// Step-Ups & High Knees
		pose = new ProfilePose(STEP_UPS, Category.LIFTING);
		float x = -pose.legSegmentLength/2;
		pose.headX = pose.waistX = pose.lFootX = pose.lHandX = pose.rHandX = x;
		pose.rFootX = pose.rKneeX = x + pose.legSegmentLength;
		pose.rFootY = pose.legSegmentLength + pose.legThickness/2;
		pose.rKneeY = pose.legSegmentLength*2;
		pose.prop = new Ledge(pose.rFootX - pose.legThickness/2, pose.rFootX + 12, pose.legSegmentLength);
		poses.put(pose.name, pose);

		pose = new ProfilePose(HIGH_KNEES, Category.LIFTING);
		pose.headX = pose.waistX = pose.lFootX = pose.lHandX = pose.rHandX = x;
		pose.rFootX = pose.rKneeX = x + pose.legSegmentLength;
		pose.rFootY = pose.legSegmentLength + pose.legThickness/2;
		pose.rKneeY = pose.waistY;
		poses.put(pose.name, pose);

		// Lunges
		pose = new ProfilePose(LUNGES, Category.LIFTING);
		pose.waistY = pose.rHandY = pose.lHandY = pose.legSegmentLength;
		pose.headY = pose.waistY + pose.torsoLength + pose.torsoThickness/2 + pose.headSize/2;
		pose.lHandX = pose.rHandX = 0;
		pose.rFootX = pose.rKneeX = pose.legSegmentLength;
		pose.rFootY = pose.lFootY = pose.lKneeY = pose.legThickness/2;
		pose.rKneeY = pose.legSegmentLength;
		pose.lKneeX = -4f;
		pose.lFootX = pose.lKneeX - pose.legSegmentLength;
		poses.put(pose.name, pose);


		// Push-ups
		pose = new ProfilePose(PUSH_UPS, Category.LIFTING);
		pose.headX = 30; pose.headY = 23;
		pose.waistX = 1; pose.waistY = 14;
		pose.rHandX = pose.lHandX = 25; pose.rHandY = pose.lHandY = pose.armThickness/2;
		pose.rFootX = pose.lFootX = -33; pose.rFootY = pose.lFootY = pose.legThickness/2;
		poses.put(pose.name, pose);

		// Plank
		pose = new ProfilePose(PLANK, Category.LIFTING);
		pose.headX = 30; pose.headY = 16;
		pose.waistX = 1; pose.waistY = 10;
		pose.rHandY = pose.lHandY = pose.rElbowY = pose.lElbowY = pose.armThickness/2;
		pose.generateCoords();
		pose.rElbowX = pose.lElbowX = pose.collarX;
		pose.rHandX = pose.lHandX = pose.rElbowX + pose.armSegmentLength;
		pose.rFootX = pose.lFootX = -30; pose.rFootY = pose.lFootY = pose.legThickness/2;
		poses.put(pose.name, pose);

		// Push-Up & Rotate
		pose = new FrontalPose(PUSH_UP_ROTATE, Category.LIFTING);
		pose.headX = 30; pose.headY = pose.armSegmentLength*2 + pose.torsoThickness/2 + pose.headSize/2;
		pose.waistX = 1; pose.waistY = .6f * pose.headY;
		pose.lFootX = -33;
		pose.rFootX = pose.lFootX - 2;
		pose.lFootY = pose.legThickness/2;
		pose.rFootY = pose.lFootY + pose.legThickness;
		pose.lHandX = 25; pose.lHandY = pose.armThickness/2;
		pose.generateCoords();
		pose.rHandX = ((FrontalPose)pose).rShoulderX - pose.armThickness;
		pose.rHandY = ((FrontalPose)pose).rShoulderY + pose.armSegmentLength*2;
		poses.put(pose.name, pose);

		// Side Plank
		pose = new FrontalPose(SIDE_PLANK, Category.LIFTING, true);
		pose.headX = 30; pose.headY = pose.armSegmentLength + pose.torsoThickness/2 + pose.headSize/2;
		pose.waistX = 1; pose.waistY = .65f * pose.headY;
		pose.lFootX = -30;
		pose.rFootX = pose.lFootX - 1;
		pose.lFootY = pose.legThickness/2;
		pose.rFootY = pose.lFootY + pose.legThickness;
		pose.generateCoords();
		pose.lElbowX = ((FrontalPose) pose).lShoulderX;
		pose.lHandX = pose.lElbowX - 2;
		pose.lElbowY = pose.lHandY = pose.armThickness/2;
		pose.rHandX = ((FrontalPose)pose).rShoulderX - pose.armSegmentLength*2;
		pose.rHandY = ((FrontalPose)pose).rHipY + pose.armThickness;
		poses.put(pose.name, pose);


		// Crunches
		pose = new ProfilePose(CRUNCHES, Category.LIFTING);
		pose.headY = pose.waistY = pose.torsoThickness/2;
		pose.headX = -20;
		pose.waistX = pose.rKneeX = pose.lKneeX = pose.headX + pose.headSize/2 + pose.torsoThickness/2 + pose.torsoLength;
		pose.rFootX = pose.lFootX = pose.waistX + pose.legSegmentLength;
		pose.rKneeY = pose.lKneeY = pose.rFootY = pose.lFootY = pose.legSegmentLength;
		pose.generateCoords();
		pose.rHandX = pose.lHandX = pose.collarX + 10;
		pose.rHandY = pose.lHandY = pose.armSegmentLength * 2;
		poses.put(pose.name, pose);

		// Down Dog
		pose = new ProfilePose(DOWN_DOG, Category.YOGA);
		pose.headX = 30; pose.headY = 13;
		pose.waistX = 0; pose.waistY = 35;
		pose.rHandX = pose.lHandX = 25; pose.rHandY = pose.lHandY = 0;
		pose.rFootX = pose.lFootX = -24; pose.rFootY = pose.lFootY = 0;
		poses.put(pose.name, pose);


		// Lotus & Rest
		pose = new FrontalPose(LOTUS, Category.YOGA);
		pose.headX = 0; pose.headY = 33;
		pose.waistX = 0; pose.waistY = 3;
		pose.rHandX = 15; pose.lHandX = -15; pose.rHandY = pose.lHandY = 7;
		pose.rElbowX = 9f; pose.lElbowX = -9f; pose.rElbowY = pose.lElbowY = 10f;
		pose.rFootX = 5; pose.lFootX = -5; pose.rFootY = pose.lFootY = 2;
		pose.rKneeX = 15f; pose.lKneeX = -15f; pose.rKneeY = pose.lKneeY = 5f;
		poses.put(pose.name, pose);

		pose = new FrontalPose(REST, Category.NONE);
		pose.headX = 0; pose.headY = 33;
		pose.waistX = 0; pose.waistY = 3;
		pose.rHandX = 15; pose.lHandX = -15; pose.rHandY = pose.lHandY = 7;
		pose.rElbowX = 9f; pose.lElbowX = -9f; pose.rElbowY = pose.lElbowY = 10f;
		pose.rFootX = 5; pose.lFootX = -5; pose.rFootY = pose.lFootY = 2;
		pose.rKneeX = 15f; pose.lKneeX = -15f; pose.rKneeY = pose.lKneeY = 5f;
		poses.put(pose.name, pose);

	}

}

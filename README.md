# My Fitness Routines
Android app to guide you through fitness routines.

## Steps to release from *master* branch
1. Pull Request & Merge to *master*
1. File Changes
    - **TODO.txt** up to date
    - Commit these on *master*:
        - Version in Module's **build.gradle**
            - *versionCode* incremented (every App Store submission must be higher)
            - *versionString* (what's displayed to users)
        - Generate Signed AAB then Signed APK (building second type deletes first type)
        - comment = *versionCode #18 = "v1.0.8"*
1. Publish GitHub Release
    - Attach Free-AAB & Full-APK
    - v1.0.8-feature
1. Upload Free-AAB to App Store
1. Reverse-Merge *master* to *develop*

### Plan for using Release and being able to Debug
Install Free-AAB from App Store, Debug using fullDebug

## Object-Oriented Design
### Generic (abstract for Routine, Video)
- **name**
- **description**
#### Video : Generic
- **url**
#### Routine : Generic
- **category**
- **tasks**
- *getDuration()*
#### Task
- **move**
- **moveSeconds**
- **restSeconds**
#### Move (MoveWithPose, LadderMove, SoccerMove)
- **name**
- **description**
- **category**
- **twoSides**
- *getBitmap()*
##### MoveWithPose : Move
- **pose**
###### Pose
- **torso**
- **rLeg**
- **lLeg**
- **rArm**
- **lArm**
- **prop**
#### LadderMove : Move
- **ladderSteps**
##### LadderStep (OnePointLadderStep, TwoPointLadderStep)
- *draw()*
- *hasLeft()*, *hasRight()*, *hasBoth()*
- *getLeft()*, *getRight()*
###### OnePointLadderStep
- **step**
###### TwoPointLadderStep
- **left**
- **right**
##### SoccerMove : Move
- **ball**
- **motions**
###### Motion (SoccerTouch, SoccerStep)
- *draw()*
####### SoccerTouch : Motion
- **arrow**
####### SoccerStep : Motion
- **step**
### Session
- **date** & **timestamp**
- **routineName**
- **durationSeconds**

## Architecture
### Create Moves & Routines
- *App.onCreate()* in **RELEASE** to create once or *TabbedActivity.onCreate()* in **DEBUG** to recreate each time
    - *RoutineLibrary.generate()*
        - *MoveLibrary.generate()*
        - *SampleRoutines.generate()*
            - *SampleRoutines.generateXXX()*
### TabbedActivity
- sets up Tab Pager
- in **DEBUG**: regenerates RoutineLibrary
- handles Floating Action Button
#### TabPagerAdapter
- creates Tabs
#### GenericFragment
- fills *ListView* of **Generics** based on *FLAVOR* & *Tab Section Number*
- handles onItemClick
#### GenericAdapter
- *ArrayAdapter* for **Generics** in *lstGenerics*
### PlayRoutineActivity
- *onCreate()*
    - **TextToSpeech**
    - keep **Screen ON**
    - **PlayRoutineTaskFragment**
    - *displayTask()*
        - *displayInstructions()*
        - *displayMove()*
        - etc.
#### PlayRoutineTaskFragment
- *setMove(*), *cancelTimer()*, *resetSecondsRemaining()*
- *pause()*, *play()*, *next()*, *prev()*, *restart()*
- *runXXXTimer()*
- *isPaused()*, *getCurrentTask()*, *getInstructions()*, *getNextTask()*, *getTasksRemaining()*, *getSecondsRemaining()*, *isSecondSide()*

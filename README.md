# blrkotlinworkshop
Skeleton Project for BlrKotlin Workshop September 2018

## Requirements (IDE and Tool setup)
1. Android Studio 3.1.4 (Latest stable version)
2. Kotlin Plugin 1.2.61
3. Spek Plugin needs to be installed. Go to `IDE Settings -> Plugins -> Browse Repositories`, search for "Spek", and install it.
4. Have at least one emulator set up (with Google APIs since we might integrate location services) or have a device available for running code during the workshop.

## Setup before coming for the workshop
1. Clone the project.
2. Import the project into Android Studio. You might need to disable configure on demand. To do this, go to `IDE Settings -> Build, Execution, Deployment -> Compiler` and uncheck the "Configure On Demand" option.
3. The project comes bundled with a few sample tests. Run `./gradlew app:testDebugUnitTest` or `./gradlew app:tDUT` once to ensure that they run. You'll see a basic test report in the command line once they are done.
4. Deploy the project on a device. You should see the app load with a blank screen.
5. If all checks out, you are set for the workshop. Remember to put gradle in offline mode before coming to the workshop. You can do this by going to `IDE Settings -> Build, Execution, Deployment -> Gradle` and check the "Offline work" checkbox.

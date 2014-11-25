zombie-survival
===============

a survival game developed between friends (see google doc for the game content)

-----------
HOW TO USE:
-----------

You don't need to install gradle in order to use the project. But if you want to install it, the version we use is gradle-2.2.1

In all cases, I strongly recommend using the gradle wrapper (the gradlew file). Here is the main command you might need

./gradlew eclipse
this command will generate the necessary eclipse project file, to run the first time you copy this project, then import the project into eclipse to start developing.

./gradlew build
build the sources, execute tests and create a jar
To see the results of the build effort, take a look in the build folder. Therein you’ll find several directories, including these three notable folders:
	classes. The project’s compiled .class files.
	reports. Reports produced by the build (such as test reports).
	libs. Assembled project libraries (usually JAR and/or WAR files).

./gradlew run
build and run the project

./gradlew tasks
see what tasks are available to you

If by any chance you need to change the build.gradle file (need to add dependencies, ...), please regenerate the wrapper by executing:
gradle wrapper
(note that you'll need to have gradle installed on your computer to do that)

-----------
COMMIT MSG
-----------

Please try to use good commit messages when commiting to master branch, it's easier to understand and read history without having to check all the code to see what the commit actually does

I personally like this convention: prefix every commit message with
[ADD] - add files or functionality
or [FIX] - fix bug or incorrect method
or [REM] - remove files or functionality
or [REF] - refactor code
or [WIP] - work in progress, may not work yet
or [MERGE] - merge branch into master

and after the prefix a small sentence to say what was done in the commit.

If you want to work on a feature and don't want to pollute history with lot of commit and changes, you can work on a branch and then merge your branch when it is done
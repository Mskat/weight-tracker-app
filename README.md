# weight-tracker-app

## General info
The project has been created in cooperation with the participants of the Facebook group which 
I was part of. The group's goal was to learn programming in Java and to acquire cooperation skills 
in a team.<br><br>
The application is used to track users' weight over a specific period of time.<br>
It allows you to save user's:
<li>name
<li>weight
<li>weighting date<br><br>
Text files with the given data will be saved in the location you specify. The application allows 
you to save different weighting dates, for the same user, in one file. If you create a different user, 
a new text file will be created.<br>

## Technologies
Java 8+

## Setup
You should define the "path" variable before running the program. It allows you to choose where 
your files will be saved. You can do that in WeightTrackerLauncher class in methods:<br>

    saveDataToFile()
    showUser()
Next go to weight-tracker-app\src\main\java directory.<br>
Run the commands:

    javac com\zur\tracker\WeightTrackerApp.java
    java com.zur.tracker.WeightTrackerApp


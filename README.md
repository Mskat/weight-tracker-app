# Weight Tracker App
<p>The application is used to track user's weight over a specific period of time.</p>

 ## Table of Contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Sources](#sources)

## General info
<p>The project has been created in cooperation with the participants of the Facebook group which 
I was part of. The group's goal was to learn programming in Java and to acquire cooperation skills 
in a team.</p>

## Technologies
<p>
    <li>Java 8+</li>
    <li>Maven</li>
</p>

## Setup
<p>You should define the "path" variable before running the program. It allows you to choose 
the location of saving/reading your files. You can do that in WeightTrackerLauncher class in methods:</p>

    saveDataToFile()
    showUser()
    
<p>Next go to weight-tracker-app/src/main/java directory.<br>
Run the commands:</p>

    javac com/zur/tracker/WeightTrackerApp.java
    java com.zur.tracker.WeightTrackerApp

## Features
<p>
<ol>
    <li>Saving user's:
        <ul>
            <li>name</li>
            <li>weight</li>
            <li>weighting date</li>
        </ul>
    </li>
    <li>Saving data in separate text files for each user.
    <li>Saving user's data in any location.
    <li>Viewing saved data.
</ol>
</p>

## Sources
<p>The inspiration to create the application was found at www.flaviocopes.com</p>

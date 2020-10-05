package com.zur.tracker;

import com.zur.tracker.view.WeightTrackerLauncher;

import java.io.IOException;

public class WeightTrackerApp {
    public static void main(String[] args) throws IOException {
        WeightTrackerLauncher weightTrackerLauncher = new WeightTrackerLauncher();
        weightTrackerLauncher.showMenu();
    }
}

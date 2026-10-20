package com.scc;

import com.scc.dao.DatabaseConnection;
import com.scc.ui.ScenarioMenu;

public class Main {
    public static void main(String[] args) {
        // Initialize SQLite database table & PRAGMA settings
        DatabaseConnection.initializeDatabase();

        // Launch the interactive console UI menu layer
        ScenarioMenu menu = new ScenarioMenu();
        menu.displayMenu();
    }
}
package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        try {
            FileFilter filter = new FileFilter();
            filter.processArguments(args);
            filter.processFiles();
            filter.printStatistics();
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
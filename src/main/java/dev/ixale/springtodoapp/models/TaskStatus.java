package dev.ixale.springtodoapp.models;

public enum TaskStatus {
    PENDING("Pending"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed");

    private final String value;

    // create a constructor that takes a string and returns a TaskStatus
    TaskStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}

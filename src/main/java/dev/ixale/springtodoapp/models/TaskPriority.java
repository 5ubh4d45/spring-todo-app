package dev.ixale.springtodoapp.models;

public enum TaskPriority {
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High");

    private final String value;

    // create a constructor that takes a string and returns a TaskPriority
    TaskPriority(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}

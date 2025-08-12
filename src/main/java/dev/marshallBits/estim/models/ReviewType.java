package dev.marshallBits.estim.models;

public enum ReviewType {
    OVERWHELMINGLY_POSITIVE("Overwhelmingly Positive"),
    VERY_POSITIVE("Very Positive"),
    POSITIVE("Positive"),
    MOSTLY_POSITIVE("Mostly Positive"),
    MIXED("Mixed"),
    MOSTLY_NEGATIVE("Mostly Negative"),
    NEGATIVE("Negative"),
    VERY_NEGATIVE("Very Negative"),
    OVERWHELMINGLY_NEGATIVE("Overwhelmingly Negative");

    private final String displayName;

    ReviewType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

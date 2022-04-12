package me.alexconnell;

import java.time.LocalDate;

public class VetInspectons {
    LocalDate when;
    String notes;

    public VetInspectons(LocalDate when, String notes) {
        this.when = when;
        this.notes = notes;
    }
}

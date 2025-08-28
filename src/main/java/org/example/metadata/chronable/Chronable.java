package org.example.metadata.chronable;

import java.time.LocalDate;

public interface Chronable {

    LocalDate getStartDate();
    LocalDate getEndDate();
}

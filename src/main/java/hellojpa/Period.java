package hellojpa;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Embeddable
public class Period {

    private LocalDate startDate;
    private LocalDate endDate;

    public Period() {
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }
}

import java.time.LocalDate;
import java.time.LocalTime;

interface Calendar {
    LocalTime getTime();
    LocalDate getDate();
    String getUrl();
}
import java.sql.Time;
import java.util.Date;
import java.util.List;

public class ClassroomBooking extends RemoteReservation {
    private String classroomType;

    public ClassroomBooking(Time time, Date date, String url, String roomName, String location, String classroomType){
        super(time, date, url, roomName, location);
        this.classroomType=classroomType;
    }

    public String getClassroomType() {
        return classroomType;
    }
}

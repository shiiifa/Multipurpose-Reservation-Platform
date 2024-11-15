class ClassroomBooking extends RemoteReservation {
    private String classroomType;

    public ClassroomBooking(String roomName, String location, String url, String classroomType) {
        super(roomName, location, url);
        this.classroomType = classroomType;
    }

    public String getClassroomType() {
        return classroomType;
    }
}
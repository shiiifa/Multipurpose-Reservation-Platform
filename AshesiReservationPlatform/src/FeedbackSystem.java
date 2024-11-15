import java.time.LocalDateTime;

class FeedbackSystem implements FeedbackNotificationSystem {
    private String feedback;
    private String receipt;
    private String notificationMessage;

    public FeedbackSystem() {
        this.feedback = "";
        this.receipt = "";
        this.notificationMessage = "";
    }

    @Override
    public String getFeedBack() {
        return feedback;
    }

    @Override
    public String displayReceipt() {
        return receipt;
    }

    @Override
    public String message() {
        return notificationMessage;
    }

    @Override
    public String getNotification() {
        return notificationMessage;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public void generateReceipt(String details) {
        this.receipt = "Receipt: " + details + "\nTime: " + LocalDateTime.now();
    }

    public void setNotification(String message) {
        this.notificationMessage = message;
    }
}

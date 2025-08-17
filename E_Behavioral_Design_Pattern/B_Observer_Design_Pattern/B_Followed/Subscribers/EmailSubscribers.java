package E_Behavioral_Design_Pattern.B_Observer_Design_Pattern.B_Followed.Subscribers;

import E_Behavioral_Design_Pattern.B_Observer_Design_Pattern.B_Followed.Interface.Subscriber;

public class EmailSubscribers implements Subscriber {
    private String email;

    public EmailSubscribers(String email) {
        this.email = email;
    }

    @Override
    public void update(String content) {
        System.out.println("Email sent to " + email + " with content: " + content);
    }

    public String getEmail() {
        return email;
    }
}

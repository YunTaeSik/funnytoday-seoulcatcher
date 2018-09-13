package todday.funny.seoulcatcher.model.messageModel;

import todday.funny.seoulcatcher.model.Call;

public class Message {
    private String topic;
    //   private MessageNotification notification =new MessageNotification();
    private Call data;

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
/*
    public MessageNotification getNotification() {
        return notification;
    }

    public void setNotification(MessageNotification notification) {
        this.notification = notification;
    }*/

    public Call getData() {
        return data;
    }

    public void setData(Call data) {
        this.data = data;
    }
}

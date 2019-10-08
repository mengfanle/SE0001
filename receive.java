
import com.amazonaws.services.sqs.*;
import com.amazonaws.services.sqs.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class receive {
    private static String QUEUE_NAME = "MyQueue";

    public static String receivemsg() {
        AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
        String queueUrl = sqs.getQueueUrl(QUEUE_NAME).getQueueUrl();
        List<Message> messages = sqs.receiveMessage(queueUrl).getMessages();
        for (Message m : messages) {
            sqs.deleteMessage(queueUrl, m.getReceiptHandle());
            return m.getBody();
        }
        return "没收到消息";
    }

    public static void main(String[] args) {
        Frame f = new Frame("接受布局");
        Button btn2 = new Button("接受");

        f.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 70));

        btn2.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, receivemsg());
        });
        f.add(btn2);
        f.setLocationRelativeTo(null);
        //f.pack();
        f.setVisible(true);
        f.setSize(450, 278);

    }
}
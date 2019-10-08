import com.amazonaws.services.sqs.*;
import com.amazonaws.services.sqs.model.*;

import javax.swing.*;
import java.awt.*;

public class send {
    private static String QUEUE_NAME = "MyQueue";

    public static void sendmsg(String msg) {
        AmazonSQS sqs = AmazonSQSClientBuilder.defaultClient();
        String queueUrl = sqs.getQueueUrl(QUEUE_NAME).getQueueUrl();
        SendMessageRequest send_msg_request = new SendMessageRequest().withQueueUrl(queueUrl).withMessageBody(msg);
        sqs.sendMessage(send_msg_request);
    }

    public static void main(String[] args) {
        Frame f = new Frame("发送布局");
        JTextField jtf1 = new JTextField(15);
        Button btn1 = new Button();
        f.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 70));
        f.add(jtf1);
        f.setLocationRelativeTo(null);
        btn1.addActionListener(e -> {
            sendmsg(jtf1.getText());
            JOptionPane.showMessageDialog(null, "发送成功");
        });
        f.add(btn1);
        f.setVisible(true);
        f.setSize(450, 278);
        //f.pack();

    }
}
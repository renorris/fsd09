package com.reese.fsd.mailbox;

import com.reese.fsd.user.UserAPI;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class MailboxAPI {

    public UserAPI userAPI;
    public BlockingQueue<Message> messageQueue;

    public MailboxAPI(UserAPI userAPI) {
        this.userAPI = userAPI;
        this.messageQueue = new PriorityBlockingQueue<>(500);
    }

    private void start() throws InterruptedException {
        while (true) {
            Message message = this.messageQueue.take();
        }
    }

}

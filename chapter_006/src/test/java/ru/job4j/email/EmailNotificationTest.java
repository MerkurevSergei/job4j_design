package ru.job4j.email;

import org.junit.Test;

import static org.mockito.Mockito.*;

public class EmailNotificationTest {

    @Test
    public void emailTo() throws InterruptedException {
        final EmailNotification notificator = spy(new EmailNotification());
        for (int i = 0; i < 15; i++) {
            notificator.emailTo(new User("user", "user@mail.ru"));
        }
        Thread.sleep(100);
        notificator.close();
        verify(notificator, times(15)).
                send("Notification user to email user@mail.ru",
                        "Add a new event to user",
                        "user@mail.ru");
    }
}
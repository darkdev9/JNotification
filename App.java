package org.darkdev.notification;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class App {
    public static void main(String[] args) throws AWTException, InterruptedException {
        Notification.NotificationBuilder builder = new Notification.NotificationBuilder("JNotification",
                "Notification created successfully");

        builder.setMessageType(TrayIcon.MessageType.INFO)
                .build()
                .show()
                .getTrayIcons()[0]
                .addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("clicked");
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        System.out.println("pressed");
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        System.out.println("released");
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        System.out.println("entered");
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        System.out.println("exited");
                    }
                });



    }
}

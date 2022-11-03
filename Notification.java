package org.darkdev.notification;

import java.awt.TrayIcon.MessageType;
import java.awt.*;

/**
 * @author darkDev8
 */
public class Notification {
	private String title;
	private String description;
	private int delay;
	private MessageType messageType;
	private Image icon;

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public int getDelay() {
		return delay;
	}

	public MessageType getMessageType() {
		return messageType;
	}

	public Image getIcon() {
		return icon;
	}

	private Notification(NotificationBuilder notificationBuilder) {
		title = notificationBuilder.title;
		description = notificationBuilder.description;
		delay = notificationBuilder.delay;

		messageType = notificationBuilder.messageType;
		icon = notificationBuilder.icon;
	}

	/**
	 * Shows the notification.
	 *
	 * @throws AWTException         AwtException if the system failed to show the
	 *                              notification.
	 * @throws InterruptedException InterruptedException happens if the system can't
	 *                              delay to start the notification.
	 * @return The created SystemTray from notification.
	 */
	public SystemTray show() throws AWTException, InterruptedException {
		if (!SystemTray.isSupported()) {
			throw new UnsupportedOperationException("The operating system doesn't support JNotification.");
		}

		SystemTray tray = SystemTray.getSystemTray();
		TrayIcon trayIcon = new TrayIcon(icon, "Notification (darkDev8)");

		trayIcon.setImageAutoSize(true);
		tray.add(trayIcon);

		Thread.sleep(delay);
		trayIcon.displayMessage(title, description, messageType);
		return tray;
	}

	/**
	 * @author darkDev8
	 */
	public static class NotificationBuilder {
		private String title;
		private String description;
		private int delay;
		private MessageType messageType;
		private Image icon;

		public NotificationBuilder(String title, String description) {
			this.title = title;
			this.description = description;

			delay = 0;
			messageType = MessageType.INFO;
			icon = Toolkit.getDefaultToolkit()
					.createImage(getClass().getResource("/org/darkdev/notification/icon/notification.png"));
		}

		/**
		 * Set the notification title.
		 *
		 * @param title The notification title.
		 * @return Current builder object.
		 */
		public NotificationBuilder setTitle(String title) {
			this.title = title;
			return this;
		}

		/**
		 * Set the notification description.
		 *
		 * @param description The notification description.
		 * @return Current builder object.
		 */
		public NotificationBuilder setDescription(String description) {
			this.description = description;
			return this;
		}

		/**
		 * Set the notification delay.
		 *
		 * @param delay The notification delay before it shows up.
		 * @return Current builder object.
		 */
		public NotificationBuilder setDelay(int delay) {
			this.delay = delay;
			return this;
		}

		/**
		 * Set the notification message type.
		 *
		 * @param messageType The notification message type.
		 * @return Current builder object.
		 */
		public NotificationBuilder setMessageType(MessageType messageType) {
			this.messageType = messageType;
			return this;
		}

		/**
		 * Set the notification icon in the system tray.
		 *
		 * @param icon The notification tray icon.
		 * @return Current builder object.
		 */
		public NotificationBuilder setIcon(Image icon) {
			this.icon = icon;
			return this;
		}

		/**
		 * Creates the notification.
		 *
		 * @return The created notification by the builder.
		 */
		public Notification build() {
			return new Notification(this);
		}
	}
}

package com.Prosper.request.model;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ChatMessage {
	@Getter @Setter
    public String senderName;
	@Getter @Setter
	public String receiverName;
	@Getter @Setter
	public String message;
	@Getter @Setter
	public String date;
	@Getter @Setter
	public ChatStatus status;
	public String getReceiverName() {
		return receiverName;
	}
	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
	// I guess need to put courseTitle here...
}
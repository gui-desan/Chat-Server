package room;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Message implements Serializable {
	
	private String sender;
	private String message;
	
	public Message(String message) {
		this.sender = Account.name;
		this.message = message;
	}

	public String getSender() {
		return sender;
	}

	public String getMessage() {
		return message;
	}
}

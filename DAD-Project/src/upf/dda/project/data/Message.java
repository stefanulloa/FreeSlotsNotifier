package upf.dda.project.data;

public class Message {

	private long chat_id;
	private String text;
	
	public Message(){
		this.chat_id = 0;
		this.text = "";
	}
	
	public Message(long chat_id, String text){
		this.chat_id = chat_id;
		this.text = text;
	}

	public long getChat_id() {
		return chat_id;
	}

	public void setChat_id(long chat_id) {
		this.chat_id = chat_id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	public String toString() {
		return "Message [chat_id=" + chat_id + ", text=" + text + "]";
	}
}

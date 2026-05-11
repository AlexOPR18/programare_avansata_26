package server;

public class Question {
	private String text;
	private String answer;
	
	public Question(String text, String answer) {
		this.text=text;
		this.answer=answer;
	}
	
	public String getText() {return this.text;}
	public String getAnswer() {return this.answer;}
}

package xyz.peasfultown.entity;

public class Answer {
	private static int numberOfAnswers;
	static {
		numberOfAnswers = 0;
	}
	private int id;
	private String content;
	private Question question;
	private boolean isCorrect;
	{
		this.id = numberOfAnswers++;
	}
	public Answer(String content, Question question, boolean isCorrect) {
		this.content = content;
		this.question = question;
		this.isCorrect = isCorrect;
	}
	
	public static int getNumberOfAnswer() {
		return numberOfAnswers;
	}

	public int getId() {
		return id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", content=" + content + ", question=" + question + ", isCorrect=" + isCorrect
				+ "]";
	}
}

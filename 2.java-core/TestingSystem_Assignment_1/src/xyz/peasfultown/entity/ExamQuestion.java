package xyz.peasfultown.entity;

public class ExamQuestion {
	private int id;
	private Exam exam;
	private Question question;
	public ExamQuestion(int id, Exam exam, Question question) {
		super();
		this.id = id;
		this.exam = exam;
		this.question = question;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Exam getExam() {
		return exam;
	}
	public void setExam(Exam exam) {
		this.exam = exam;
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	@Override
	public String toString() {
		return "ExamQuestion [id=" + id + ", exam=" + exam + ", question=" + question + "]";
	}
}

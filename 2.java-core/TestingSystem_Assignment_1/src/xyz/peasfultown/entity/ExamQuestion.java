package xyz.peasfultown.entity;

public class ExamQuestion {
	private static int numberOfRecords;
	static {
		numberOfRecords = 0;
	}
	private int id;
	private Exam exam;
	private Question question;
	{
		this.id = numberOfRecords++;
	}

	public ExamQuestion(Exam exam, Question question) {
		this.exam = exam;
		this.question = question;
	}

	public int getId() {
		return id;
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

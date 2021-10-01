package utils;

import java.io.Serializable;

public class Answer implements Serializable, Comparable<Answer> {

	private static final long SERIAL_VERSION_UID = 8011134311799971130L;

	private String answer;
	private boolean correct;

	/**
	 * An answer contains both a string and a boolean.
	 * 
	 * @param answer  This is the text of the answer
	 * @param correct This is whether this answer is correct or no
	 */
	public Answer(String answer, boolean correct) {
		this.answer = answer;
		this.correct = correct;
	}

	/**
	 * 
	 * @return String This returns the text within the answer
	 */
	public String getAnswer() {
		return answer;
	}

	/**
	 * 
	 * @return boolean This returns whether the answer is correct
	 */
	public boolean isCorrect() {
		return correct;
	}

	/**
	 * 
	 * @param answer This is the text of the answer
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}

	/**
	 * This method with change whether the answer is correct or not
	 */
	public void toggleCorrect() {
		correct = !correct;
	}

	@Override
	public String toString() {
		return answer + ": " + correct;
	}

	@Override
	public int compareTo(Answer answer) {
		if (answer.answer == this.answer)
			return 0;
		else
			return 1;
	}
}

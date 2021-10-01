package utils;

import java.io.Serializable;

import exceptions.TooManyAnswersException;

public class Question implements Serializable, Comparable<Question> {

	private static final long SERIAL_VERSION_UID = 7979862246992799441L;

	private String question;
	private Answer[] answers;

	/**
	 * A question has several answers as well as a text.
	 * 
	 * @param question This is the text of the question
	 * @param answers  This is an array of answers for the question
	 */
	public Question(String question, Answer[] answers) {
		this.question = question;
		this.answers = answers;
	}

	/**
	 * This method returns an array of containing the answers belonging to this
	 * question
	 * 
	 * @return Answer[] This returns the possible answers for the question
	 */
	public Answer[] getAnswers() {
		return answers;
	}

	/**
	 * This method returns the text of the question
	 * 
	 * @return String This returns the text of the question
	 */
	public String getQuestion() {
		return question;
	}

	/**
	 * This method changes the text of the question
	 * 
	 * @param question This is the text of the question
	 */
	public void setQuestion(String question) {
		this.question = question;
	}

	/**
	 * This method will add an answer to the array of answers in this question
	 * 
	 * @param answer This is the answer to add
	 */
	public void addAnswer(Answer answer) {
		Answer[] answers = new Answer[this.answers.length + 1];
		for (int i = 0; i < this.answers.length; i++) {
			answers[i] = this.answers[i];
		}
		answers[this.answers.length] = answer;
		this.answers = answers;
	}

	/**
	 * This method will remove an answer from the array of answers in this question
	 * 
	 * @param answer This is the answer to remove
	 */
	public void removeAnswer(Answer answer) {
		Answer[] answers = new Answer[this.answers.length - 1];
		int x = 0;
		for (int i = 0; i < this.answers.length; i++) {
			if (this.answers[i].compareTo(answer) != 0) {
				answers[x] = this.answers[i];
				x++;
			}
		}
		this.answers = answers;
	}

	/**
	 * This method will return the correct answer for this question
	 * 
	 * @return This will return null if no answer is correct or there is more than
	 *         one correct answer
	 * @throws TooManyAnswersException if more than one answer is correct
	 */
	public Answer getCorrect() throws TooManyAnswersException {
		Answer answer = null;
		for (Answer answer2 : answers) {
			if (answer2.isCorrect())
				if (answer == null)
					answer = answer2;
				else
					throw new TooManyAnswersException();
		}
		return answer;
	}

	/**
	 * This method returns whether the answer is correct
	 * 
	 * @param s This is the text of the answer
	 * @return This returns true if the answer is correct, otherwise false
	 * @throws TooManyAnswersException This is thrown if there are too many correct
	 *                                 answers
	 */
	public boolean isCorrect(String s) throws TooManyAnswersException {
		return getCorrect().getAnswer() == s;
	}

	/**
	 * This method returns whether the answer is correct
	 * 
	 * @param ans This is the answer
	 * @return This returns true if the answer is correct, otherwise false
	 * @throws TooManyAnswersException This is thrown if there are too many correct
	 *                                 answers
	 */
	public boolean isCorrect(Answer ans) throws TooManyAnswersException {
		return isCorrect(ans.getAnswer());
	}

	@Override
	public int compareTo(Question q) {
		if (q.question == this.question)
			return 0;
		else
			return 1;
	}

	@Override
	public String toString() {
		try {
			return question + ": " + getCorrect().getAnswer();
		} catch (TooManyAnswersException e) {
			e.printStackTrace();
			return null;
		}
	}
}
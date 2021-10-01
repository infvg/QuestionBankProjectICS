package utils;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class QuestionStack {

	private ArrayList<Question> questions = new ArrayList<>();

	public QuestionStack() {
		open();
	}

	public ArrayList<Question> getQuestions() {
		return questions;
	}

	/**
	 * Reads the questions from the binary file
	 */
	private void open() {
		try {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("QuestionBank.dat"));
			try {
				while (true)
					questions.add((Question) ois.readObject());
			} catch (EOFException e) {

			}
			ois.close();
		} catch (FileNotFoundException e) {

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * Writes the questions to the binary file
	 */
	public void update() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("QuestionBank.dat"));
			for (Question q : questions) {
				oos.writeObject(q);
			}
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean hasDuplicate(String text) {
		for (Question q : questions) {
			if (q.getQuestion().equalsIgnoreCase(text))
				return true;
		}
		return false;
	}
}

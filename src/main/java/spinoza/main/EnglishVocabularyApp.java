package spinoza.main;
import spinoza.controller.Controller;
import spinoza.model.EnglishVocabulary;
import spinoza.view.View;

/*******************************************
 * File Name: EnglishVocabularyApp.java
 * Purpose: Demo of English Vocabulary Application
 * Programmer: Gabriel.Espinoza
 * Last Update: 12/03/2020
 *
*******************************************/

public class EnglishVocabularyApp {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws Exception {

		View myView = new View();
		EnglishVocabulary myModel = new EnglishVocabulary();
		Controller myController = new Controller(myView,myModel);

	} // End Main Method

} // End Class
package fr.epita.quiz.exception;

/**
 * @author lafik
 *
 */
public class CreateFailedException extends DataAccessException{

	
	public CreateFailedException(Object beanThatWasNotCreated) {
		super(beanThatWasNotCreated);
	}
	
	public CreateFailedException(Object beanThatWasNotCreated, Exception initialCause) {
		super(beanThatWasNotCreated, initialCause);
	}

}

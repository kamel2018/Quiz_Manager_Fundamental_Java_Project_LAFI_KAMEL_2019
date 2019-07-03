package fr.epita.quiz.logging;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lafik
 *
 */
public class QuizLogger {

	private String loggingEntity;
	
	public QuizLogger(String loggingEntity) {
		this.loggingEntity = loggingEntity;
	}
	
	
	public void log(String message, String level) {
		Date currentDate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss.SSS");
		System.out.println(sdf.format(currentDate) + " - " + loggingEntity + " - [" + level + "] --- " + message );
	}
	public void logInfo(String message) {
		log(message, "INFO");
	}
	public void logError(String message) {
		log(message, "ERROR");
	}
	public void logDebug(String message) {
		log(message, "DEBUG");
	}
	public void logWarn(String message) {
		log(message, "WARN");
	}
	
	
}

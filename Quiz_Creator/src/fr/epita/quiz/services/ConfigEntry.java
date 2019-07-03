package fr.epita.quiz.services;

/**
 * @author lafik
 *
 */
public enum ConfigEntry {
	
	DB_QUERIES_QUIZ_SEARCHQUERY("db.queries.quiz.searchQuery"),
	DB_URL("jdbc:mysql://localhost:3306/quiz"),
	DB_USERNAME("root"),
	DB_PASSWORD("dbpass"),
;
	private String key;
	
	public String getKey() {
		return key;
	}

	private ConfigEntry(String key) {
		this.key = key;
	}

}

package fr.epita.quiz.datamodel;

/**
 * @author lafik
 *
 */
public enum Difficulty {
	VERY_EASY(1),
	EASY(2),
	MEDIUM(3),
	HARD(4),
	VERY_HARD(5),
	EXTREMELY_HARD(6),
	
	;
	
	private Integer numericDifficulty;
	
	
	private Difficulty(Integer difficulty) {
		this.numericDifficulty = difficulty;
	}
	
	public Integer getDifficulty() {
		return this.numericDifficulty;
	}

}

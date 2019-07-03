package fr.epita.quiz.datamodel;

/**
 * @author lafik
 *
 */


public class Question {
        int no;
	private String content;
	private String  topic;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Integer getDiff() {
        return diff;
    }

    public void setDiff(Integer diff) {
        this.diff = diff;
    }
	private Integer diff;
	private String Ans,wrong1,wrong2;

    public Question(String content,  String Ans) {
        this.content = content;
      
        this.Ans = Ans;
    }

    public Question(String content, Integer diff, String Ans ,int n) {
        this.content = content;
        this.diff = diff;
        this.Ans = Ans;
        no =n;
    }

    public Question(String content, Integer diff, String Ans, String wrong1, String wrong2,int n) {
        this.content = content;
        this.diff = diff;
        this.Ans = Ans;
        this.wrong1 = wrong1;
        this.wrong2 = wrong2;
        no =n;
    }

    public Question() {
    }

    public Question(String content, String Ans, String wrong1, String wrong2) {
        this.content = content;
       
        this.Ans = Ans;
        this.wrong1 = wrong1;
        this.wrong2 = wrong2;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAns() {
        return Ans;
    }

    public void setAns(String Ans) {
        this.Ans = Ans;
    }

    public String getWrong1() {
        return wrong1;
    }

    public void setWrong1(String wrong1) {
        this.wrong1 = wrong1;
    }

    public String getWrong2() {
        return wrong2;
    }

    public void setWrong2(String wrong2) {
        this.wrong2 = wrong2;
    }

	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTopics() {
		return topic;
	}
	public void setTopics(String topics) {
		this.topic = topics;
	}
	public Integer getDifficulty() {
		return diff;
	}
	public void setDifficulty(Integer difficulty) {
		this.diff = difficulty;
	}


	@Override
	public String toString() {
		return "Question [content=" + content + ", topics=" + topic + ", difficulty=" + diff
				+ "]";
	}
	
	
	
	
	
	

}

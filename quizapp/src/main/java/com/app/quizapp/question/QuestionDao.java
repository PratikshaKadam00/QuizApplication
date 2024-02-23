package com.app.quizapp.question;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer>{
   
	public List<Question>findByCategory(String category);

	@Query(value = "Select * from Question q where q.category=:category order by random() limit :5", nativeQuery = true)
	public List<Question> getRandomQuestions(String category, int noOfQuestions);
}

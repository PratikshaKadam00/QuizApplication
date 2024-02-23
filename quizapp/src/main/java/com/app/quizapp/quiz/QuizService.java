package com.app.quizapp.quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.quizapp.question.Question;
import com.app.quizapp.question.QuestionDao;
import com.app.quizapp.question.QuestionWrapper;

@Service
public class QuizService {
	
	@Autowired
	QuizDao quizDao;

	@Autowired
	QuestionDao questionDao;
	
	public ResponseEntity<Quiz> createQuiz(String category, int noOfQuestions, String title) {
		
		Quiz quiz= new Quiz();
		quiz.setTitle(title);
		
		List<Question> questions=questionDao.getRandomQuestions(category, noOfQuestions);
		
		quiz.setQuestions(questions);
		
		quizDao.save(quiz);
		
		return new ResponseEntity<>(quizDao.save(quiz), HttpStatus.CREATED);
		
		
	}

	public ResponseEntity<List<QuestionWrapper>> findById(Integer id) {
		
		Optional<Quiz> quiz= quizDao.findById(id);
		List<Question> questionFromDB = quiz.get().getQuestions();
		
		List<QuestionWrapper> questionsForUser=new ArrayList<QuestionWrapper>();
		for(Question q : questionFromDB)
		{
			QuestionWrapper qw= new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(), q.getOption3(), q.getOption4());
			questionsForUser.add(qw);
			
		}
		return new ResponseEntity<List<QuestionWrapper>>(questionsForUser, HttpStatus.OK);
		
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		
		Optional<Quiz> quiz= quizDao.findById(id);
		List<Question> question=quiz.get().getQuestions();
		int right=0;
		int i=0;
		for(Response response : responses)
		{
			if(response.getResponse().equals(question.get(i).getRightAnswer()))
			{
				right++;
			}
			i++;
		}
		return new ResponseEntity<>(right, HttpStatus.OK);
	}

}

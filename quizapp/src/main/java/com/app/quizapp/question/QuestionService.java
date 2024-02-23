package com.app.quizapp.question;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Service
public class QuestionService {
	
	@Autowired
	private QuestionDao qdao;
	
	public ResponseEntity<List<Question>> getAllQuestions()
	{
		try
		{
			return new ResponseEntity<>(qdao.findAll(), HttpStatus.OK);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		 
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
		
		try
		{
			return new ResponseEntity<>(qdao.findByCategory(category), HttpStatus.OK);
			
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		 
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<Question> saveQuestion(Question question) {
	
		 Question saved= qdao.save(question);
		 
		 return new ResponseEntity<>(saved, HttpStatus.CREATED);
		 
	}

	public ResponseEntity<Question> getQuestionById(int id) {
		
		Optional<Question> q=qdao.findById(id);
	try{
	      return new ResponseEntity<>(q.get(), HttpStatus.OK);
	      
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return new ResponseEntity <>( HttpStatus.NOT_FOUND);
	}

}

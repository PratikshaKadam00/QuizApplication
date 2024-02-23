package com.app.quizapp.question;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/question")
public class QuestionController {
	
	@Autowired
	QuestionService qservice;
	
	@GetMapping("/allQuestions")
	public ResponseEntity<ResponseEntity<List<Question>>> getAllQuestions()
	{
		return ResponseEntity.ok(qservice.getAllQuestions());
	}
	
	@GetMapping("/category/{category}")
	public ResponseEntity<ResponseEntity<List<Question>>> getQuestionByCategory(@PathVariable String category)
	{
		return ResponseEntity.ok(qservice.getQuestionsByCategory(category));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseEntity<Question>> getQuestionById(@PathVariable int id)
	{
		return ResponseEntity.ok(qservice.getQuestionById(id));
	}
	
	@PostMapping
	public ResponseEntity<Question> addQuestion(@RequestBody Question question)
	{
		ResponseEntity<Question> savedQuestion= qservice.saveQuestion(question);
		
		URI location =ServletUriComponentsBuilder.fromCurrentRequest()
				      .path("/{id}")
				      .buildAndExpand(savedQuestion.getBody().getId())
				      .toUri();
		return ResponseEntity.created(location).build();
	}

}

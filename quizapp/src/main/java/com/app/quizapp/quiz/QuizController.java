package com.app.quizapp.quiz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.quizapp.question.Question;
import com.app.quizapp.question.QuestionWrapper;

@RequestMapping("/quiz")
@RestController
public class QuizController {
	
	@Autowired
	QuizService quizService;
	
	
	@PostMapping("/create")
	public ResponseEntity<Quiz> createQuiz(@RequestParam String category, @RequestParam int noOfQuestions, @RequestParam String title)
	{
		
		
		return quizService.createQuiz(category, noOfQuestions, title);
		
	}
	
	@GetMapping("/get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id)
	{
		return quizService.findById(id);
	}
	
	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> response )
	{
		return quizService.calculateResult(id, response);
	}

}

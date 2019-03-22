package philfound;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import philfound.Question;
import philfound.QuestionRepository;

import philfound.Answer;
import philfound.AnswerRepository;

@Controller    // This means that this class is a Controller
@RequestMapping(path="/api") // This means URL's start with /demo (after Application path)
public class MainController {
	@Autowired
    private QuestionRepository questionRepository;
	
    @Autowired
    private AnswerRepository answerRepository;

	@GetMapping(path="/add/question") // Map ONLY GET Requests
	public @ResponseBody String addNewQuestion (@RequestParam String question) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		
		Question n = new Question();
		n.setQuestion(question);
		questionRepository.save(n);
		return "Saved question " + question + " in database";
	}

	@GetMapping(path="/add/answer") 
	public @ResponseBody String addNewAnswer (@RequestParam Integer questionID, @RequestParam String answer) {
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		Question q = questionRepository.findById(questionID).orElse(new Question());
        Answer n = new Answer();
		n.setAnswer(answer);
		q.addAnswer(n);
        answerRepository.save(n);
		return "Saved answer " + answer + " in database";
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Question> getAllQuestions() {
		// This returns a JSON or XML with the users
		return questionRepository.findAll();
	}
}

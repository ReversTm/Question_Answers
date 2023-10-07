package com.example.springbootpages.Controller;

import com.example.springbootpages.Entity.Answer;
import com.example.springbootpages.Entity.Client;
import com.example.springbootpages.Entity.Question;
import com.example.springbootpages.Service.AnswerService;
import com.example.springbootpages.Service.ClientService;
import com.example.springbootpages.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;


    @RequestMapping("/")
    public String firstView() {
        return "first-view";
    }


    @RequestMapping("/showAllQuestions")
    public String showAllQuestions(Model model) {

//        List<Client> allClients = clientService.getAllClients();
//        model.addAttribute("allClients", allClients);
//        List<Question> questionList = clientService.getAllQuestions();
//        model.addAttribute("allQuestions", questionList);
//
//        return "showAllQuestions";

        List<Client> allClients = clientService.getAllClients();
        model.addAttribute("allClients", allClients);
        Map<Client, List<Question>> userQuestionsMap = new HashMap<>();

        for (Client client : allClients) {
            List<Question> userQuestions = questionService.getAllQuestions(client.getId());
            userQuestionsMap.put(client, userQuestions);
        }

        model.addAttribute("userQuestionsMap", userQuestionsMap);

        return "showAllQuestions";
    }

    @RequestMapping("/answer")
    public String answerClient(@RequestParam int clientId,
            @RequestParam int questionId, Model
            model) {
        Question question = questionService.getQuestion(questionId);
        Client client = clientService.getClientById(clientId);
        List<Answer> answers = answerService.getByQuestion(question);

        model.addAttribute("question", question);
        model.addAttribute("client", client);
        model.addAttribute("answers", answers);

        return "answer-info";
    }

    @RequestMapping("/submit-answer")
    public String submitAnswer(@RequestParam int clientId,
                               @RequestParam int questionId,
                               @RequestParam String answerText,
                               Model model) {
//        System.out.println(clientId);
//        System.out.println("hello!");
        try {
            Client client = clientService.getClientById(clientId);

//            System.out.println(clientId);
//            System.out.println(questionId);
//            System.out.println(answerText);

            // Создайте новый ответ
            Answer answer = new Answer();
            answer.setAnswerText(answerText);

            // Получите вопрос по его ID
            Question question = questionService.getQuestion(questionId);

            answer.setQuestion(question);
            answer.setClient(client);

            // Сохраните ответ в базе данных
            answerService.saveAnswer(answer);

            // Перенаправьте пользователя обратно на страницу с ответами
            return "redirect:/answer?clientId=" + clientId + "&questionId=" + questionId;
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage()); // Добавьте сообщение об ошибке в модель
            return "error-page"; // Создайте страницу для отображения ошибок
        }
    }
}

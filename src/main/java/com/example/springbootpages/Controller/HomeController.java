package com.example.springbootpages.Controller;

import com.example.springbootpages.Entity.Answer;
import com.example.springbootpages.Entity.User;
import com.example.springbootpages.Entity.Question;
import com.example.springbootpages.Service.AnswerService;
import com.example.springbootpages.Service.UserService;
import com.example.springbootpages.Service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.*;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

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

        List<User> allUsers = userService.getAllUsers();
//        System.out.println(allUsers);
        model.addAttribute("allClients", allUsers);
        Map<User, List<Question>> userQuestionsMap = new HashMap<>();

        for (User user : allUsers) {
//            System.out.println(user);
//            System.out.println("ID:" + user.getId());
            List<Question> userQuestions = questionService.getAllQuestions(user.getId());
//            System.out.println(userQuestions);
            userQuestionsMap.put(user, userQuestions);
        }

        model.addAttribute("userQuestionsMap", userQuestionsMap);

        return "showAllQuestions";
    }

    @RequestMapping("/answer")
    public String answerClient(@RequestParam int clientId,
            @RequestParam int questionId, Model
            model) {
        Question question = questionService.getQuestion(questionId);
        User user = userService.getUserById(clientId);
        List<Answer> answers = answerService.getByQuestion(question);

        model.addAttribute("question", question);
        model.addAttribute("client", user);
        model.addAttribute("answers", answers);

        return "answer-info";
    }

    @PostMapping("/submit-answer")
    public String submitAnswer(@RequestParam int questionId,
                               @RequestParam String answerText,
                               Principal principal,
                               Model model) {
        try {
//            System.out.println(principal);
            String username = principal.getName();
            User user = userService.getUserByUsername(username);
            // предполагая, что у вас есть этот метод
            Answer answer = new Answer();
            answer.setAnswerText(answerText);

            // Получите вопрос по его ID
            Question question = questionService.getQuestion(questionId);

            answer.setQuestion(question);
            answer.setUser(user);

            // Сохраните ответ в базе данных
            answerService.saveAnswer(answer);

            // Перенаправьте пользователя обратно на страницу с ответами
            return "redirect:/answer?clientId=" + user.getId() + "&questionId=" + questionId;
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage()); // Добавьте сообщение об ошибке в модель
            return "error-page"; // Создайте страницу для отображения ошибок
        }
    }

}

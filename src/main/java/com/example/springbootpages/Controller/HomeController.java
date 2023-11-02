package com.example.springbootpages.Controller;

import com.example.springbootpages.Entity.Answer;
import com.example.springbootpages.Entity.User;
import com.example.springbootpages.Entity.Question;
import com.example.springbootpages.Entity.Vote;
import com.example.springbootpages.Service.AnswerService;
import com.example.springbootpages.Service.UserService;
import com.example.springbootpages.Service.QuestionService;
import com.example.springbootpages.Service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private VoteService voteService;


    @RequestMapping("/")
    public String firstView(Principal principal,
                            Model model) {
        String name = principal.getName();
        model.addAttribute("userName", name);
        return "first-view";
    }


    @RequestMapping("/showAllQuestions")
    public String showAllQuestions(Model model) {

        List<User> allUsers = userService.getAllUsers();
//        System.out.println(allUsers);
        model.addAttribute("allClients", allUsers);
        LinkedHashMap<User, List<Question>> userQuestionsMap = new LinkedHashMap<>();

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
                               @RequestParam int questionId,
                               Model model,
                               Principal principal) {
        Question question = questionService.getQuestion(questionId);
        User user = userService.getUserById(clientId);
        List<Answer> answers = answerService.getByQuestion(question);
        String name = principal.getName();


        model.addAttribute("currentUserName", name);
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
            return "error"; // Создайте страницу для отображения ошибок
        }
    }

    @RequestMapping("/submit-vote")
    public String submitVote(@RequestParam int answerId, @RequestParam int voteValue, @RequestParam int questionId, Principal principal, Model model) {
        try {
            User user = userService.getUserByUsername(principal.getName());
            Answer answer = answerService.getAnswerById(answerId);
            Vote vote = voteService.getVoteByUserAndAnswer(user, answer);
//            Question question = questionService.getQuestion(questionId);
            if (vote == null) {
                // Користувач ще не голосував за цю відповідь
                vote = new Vote();
                vote.setUser(user);
                vote.setAnswer(answer);
                vote.setVote(voteValue);
                voteService.saveVote(vote);

                // Оновлюємо рейтинг відповіді
                answer.setRating(answer.getRating() + voteValue);
                answerService.update(answer);
            } else {
                if (vote.getVote() != voteValue) {
                    // Если новое значение голоса отличается от предыдущего, обновляем голос и рейтинг ответа
                    answer.setRating(answer.getRating() + voteValue); // Учитываем предыдущий голос в общем рейтинге
                    vote.setVote(voteValue);
                    voteService.saveVote(vote);
                    answerService.update(answer);
                }
//                model.addAttribute("error", "Ви вже голосували за цю відповідь!");
            }
            return "redirect:/answer?clientId=" + user.getId() + "&questionId=" + questionId;
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @RequestMapping("/edit-answer")
    public String editAnswer(@RequestParam int answerId,
                             @RequestParam int questionId,
                             @RequestParam String answerText,
                             Principal principal,
                             Model model) {
        Answer answer = answerService.getAnswerById(answerId);
        User user = userService.getUserByUsername(principal.getName());
        try {
            answer.setAnswerText(answerText);
            answerService.update(answer);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
        return "redirect:/answer?clientId=" + user.getId() + "&questionId=" + questionId;
    }
}

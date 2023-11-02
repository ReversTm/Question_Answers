package com.example.springbootpages.Controller;

import com.example.springbootpages.Entity.*;
import com.example.springbootpages.Service.*;
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
    private VoteAnswerService voteAnswerService;

    @Autowired
    private VoteQuestionService voteQuestionService;


    @RequestMapping("/")
    public String firstView(Principal principal, Model model) {
        String name = principal.getName();
        model.addAttribute("userName", name);
        return "first-view";
    }


    @RequestMapping("/showAllQuestions")
    public String showAllQuestions(Model model) {

        List<User> allUsers = userService.getAllUsers();
        model.addAttribute("allClients", allUsers);
        LinkedHashMap<User, List<Question>> userQuestionsMap = new LinkedHashMap<>();

        for (User user : allUsers) {
            List<Question> userQuestions = questionService.getAllQuestions(user.getId());
            userQuestionsMap.put(user, userQuestions);
        }


        model.addAttribute("userQuestionsMap", userQuestionsMap);

        return "showAllQuestions";
    }

    @RequestMapping("/answer")
    public String answerClient(@RequestParam int clientId, @RequestParam int questionId, Model model, Principal principal) {
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
    public String submitAnswer(@RequestParam int questionId, @RequestParam String answerText, Principal principal, Model model) {
        try {
            String username = principal.getName();
            User user = userService.getUserByUsername(username);
            Answer answer = new Answer();
            answer.setAnswerText(answerText);

            // Получаем вопрос по ID
            Question question = questionService.getQuestion(questionId);

            answer.setQuestion(question);
            answer.setUser(user);

            // Сохраняем ответ в базе данных
            answerService.saveAnswer(answer);

            return "redirect:/answer?clientId=" + user.getId() + "&questionId=" + questionId;
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @RequestMapping("/submit-vote-for-answer")
    public String submitVoteForAnswer(@RequestParam int answerId, @RequestParam int voteValue, @RequestParam int questionId, Principal principal, Model model) {
        try {
            User user = userService.getUserByUsername(principal.getName());
            Answer answer = answerService.getAnswerById(answerId);
            Vote vote = voteAnswerService.getVoteByUserAndAnswer(user, answer);
            if (vote == null) {

                // Користувач ще не голосував за цю відповідь
                vote = new Vote();
                vote.setUser(user);
                vote.setAnswer(answer);
                vote.setVote(voteValue);
                voteAnswerService.saveVote(vote);

                // Оновлюємо рейтинг відповіді
                answer.setRating(answer.getRating() + voteValue);
                answerService.update(answer);
            } else {
                if (vote.getVote() != voteValue) {
                    // Если новое значение голоса отличается от предыдущего, обновляем голос и рейтинг ответа
                    answer.setRating(answer.getRating() + voteValue); // Учитываем предыдущий голос в общем рейтинге
                    vote.setVote(voteValue);
                    voteAnswerService.saveVote(vote);
                    answerService.update(answer);
                }
            }
            return "redirect:/answer?clientId=" + user.getId() + "&questionId=" + questionId;
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @RequestMapping("/submit-vote-for-question")
    public String submitVoteForQuestion(@RequestParam int voteValue, @RequestParam int questionId, Principal principal, Model model) {
        try {
            User user = userService.getUserByUsername(principal.getName());
            Question question = questionService.getQuestion(questionId);
            VoteQuestion vote = voteQuestionService.getVoteByUserAndQuestion(user, question);

            if (vote == null) {
                // Користувач ще не голосував за цю відповідь
                vote = new VoteQuestion();
                vote.setUser(user);
                vote.setQuestion(question);
                vote.setVote(voteValue);
                voteQuestionService.saveVote(vote);

                // Оновлюємо рейтинг відповіді
                question.setRating(question.getRating() + voteValue);
                questionService.update(question);
            } else {
                if (vote.getVote() != voteValue) {
                    // Если новое значение голоса отличается от предыдущего, обновляем голос и рейтинг ответа
                    question.setRating(question.getRating() + voteValue); // Учитываем предыдущий голос в общем рейтинге
                    vote.setVote(voteValue);
                    voteQuestionService.saveVote(vote);
                    questionService.update(question);
                }
            }
            return "redirect:/answer?clientId=" + user.getId() + "&questionId=" + questionId;
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "error";
        }
    }

    @RequestMapping("/edit-answer")
    public String editAnswer(@RequestParam int answerId, @RequestParam int questionId, @RequestParam String answerText, Principal principal, Model model) {
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

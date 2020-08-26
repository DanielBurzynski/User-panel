package pl.spring.springtest.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pl.spring.springtest.component.mailer.SignUpMailer;
import pl.spring.springtest.entity.User;
import pl.spring.springtest.repository.UserRepository;

@RestController
public class MainController {


    @Autowired
    private SignUpMailer mailer;

    @Autowired
    UserRepository userRepository;


    @RequestMapping("/send_meil")
    public String sendMail() {
        mailer.sendMessage("springkurs177@gmail.com", "Zwykła wiadomosć", "To jest test");
        return "mail sent";
    }

    @RequestMapping("/confirm_email")
    public String confirmEmail(@RequestParam(name = "token") String token) {

        Optional<User> optionalUser = userRepository.findByConfirmationToken(token);

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setEnabled(true);
            userRepository.save(user);
            return "Your account has been activated";

        } else {
            return "Given token does not exist";
        }

    }


}

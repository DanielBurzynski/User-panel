package pl.spring.springtest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import pl.spring.springtest.component.MailComponent;
import pl.spring.springtest.component.MessageComponent;
import pl.spring.springtest.component.mailer.Contact;
import pl.spring.springtest.component.mailer.Message;
import pl.spring.springtest.entity.Item;

@Controller
public class OnePageController {

    @Autowired
    MailComponent mailComponent;

    @Autowired
    MessageComponent messageComponent;

    @GetMapping("/contact")
    public String index(@ModelAttribute Contact contact) {
        return "contact";
    }

    @PostMapping("/contact")
    public String processContact(@Validated Contact contact, RedirectAttributes model, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "index";

        if (mailComponent.sendSimpleMail(contact)) {
            model.addFlashAttribute("classCss", "alert alert-success");
            model.addFlashAttribute("message", "Your message has been sent");
        } else {
            model.addFlashAttribute("classCss", "alert alert-warning");
            model.addFlashAttribute("message", "An unexpected error occurred thank you to repeat your request later");
        }

        return "redirect:/index";
    }

    @GetMapping("/message")
    public String messageForm(@ModelAttribute Message message){
        return "message";
    }

    @PostMapping("/message")
    public String processMessage(@Validated Message message, RedirectAttributes model, BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "message";

        if (messageComponent.sendSimpleMessage(message)) {
            model.addFlashAttribute("classCss", "alert alert-success");
            model.addFlashAttribute("message", "Your message has been sent");
        } else {
            model.addFlashAttribute("classCss", "alert alert-warning");
            model.addFlashAttribute("message", "An unexpected error occurred thank you to repeat your request later");
        }

        return "redirect:/user_panel";
    }

}

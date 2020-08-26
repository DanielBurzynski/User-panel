package pl.spring.springtest.component.mailer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class Message {


    @NotBlank
    @Size(min = 1,max = 140, message = "Wprowadź tytuł wiadomości")
    private String title;
    @NotBlank
    @Size(min = 1,max = 140, message = "Wprowadź imię")
    private String name;

    private String email;

    @NotBlank
    @Size(min = 1,max = 500, message = "Wprowadź treść wiadomości")
    private String message;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

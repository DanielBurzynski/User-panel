package pl.spring.springtest.component.mailer;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class Contact {

    @NotBlank
    @Size(min = 5,max = 140, message = "Wprowadź tytuł wiadomości")
    private String title;

    private String email;

    @NotBlank
    @Size(min = 5,max = 500, message = "Wprowadź treść wiadomości")
    private String content;





    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

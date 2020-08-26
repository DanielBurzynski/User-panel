package pl.spring.springtest.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import pl.spring.springtest.component.mailer.RandomStringFactory;
import pl.spring.springtest.component.mailer.SignUpMailer;
import pl.spring.springtest.entity.Role;
import pl.spring.springtest.entity.User;
import pl.spring.springtest.repository.RoleRepository;
import pl.spring.springtest.repository.UserRepository;
import pl.spring.springtest.service.SignUpService;

import java.util.Optional;

@Service
public class SignUpServiceImpl implements SignUpService {

    public static final String CAN_T_SIGHT_UP_GIVEN_USER_IT_ALREADY_HAS_SET_ID = "Can't sight up given user, it already has set id";
    public static final int TOKEN_LENGHT=15;

    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    private SignUpMailer mailer;

    private RoleRepository roleRepository;


    @Autowired
    public SignUpServiceImpl(UserRepository userRepository,PasswordEncoder passwordEncoder,  SignUpMailer mailer, RoleRepository roleRepository){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailer = mailer;
        this.roleRepository = roleRepository;
    }

    @Override
    public User signUpUser(User user) {

        Assert.isNull(user.getIdUser(), CAN_T_SIGHT_UP_GIVEN_USER_IT_ALREADY_HAS_SET_ID + user.toString());

        user.setPassword( passwordEncoder.encode(user.getPassword()));

        String token = RandomStringFactory.getRandomString(TOKEN_LENGHT);

        user.setConfirmationToken(token);

        Optional<Role> roleOptional = roleRepository.findByName("USER");
        if(roleOptional.isPresent()){
            user.getRoles().add(roleOptional.get());

        }

        mailer.sendConfirmationLink(user.getEmail(),token);

        User savedUser = userRepository.save(user);


        return savedUser;
    }
}

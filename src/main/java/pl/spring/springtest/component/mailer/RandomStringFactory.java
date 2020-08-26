package pl.spring.springtest.component.mailer;

import org.omg.CORBA.WCharSeqHelper;
import org.springframework.stereotype.Component;

import java.util.Random;


public class RandomStringFactory {


private static final String chars="qazwsxedcrfvtgbyhnujmiklop1234567890QAZWSXEDCRFVTGBYHNUJMIKLOP";


public static String getRandomString(int lenght){

    StringBuilder builder = new StringBuilder();

    Random random = new Random();


    for(int i = 0 ; i<lenght; i++){

        builder.append(chars.charAt(random.nextInt(chars.length())));

    }

    return builder.toString();
}

}

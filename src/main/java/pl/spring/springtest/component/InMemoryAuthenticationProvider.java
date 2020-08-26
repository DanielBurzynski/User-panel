package pl.spring.springtest.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class InMemoryAuthenticationProvider implements AuthenticationProvider {

	private static final String INCORRECT_PASSWORD = "Incorrect password";
	private static final String CREDENTIALS_CANNOT_BE_NULL = "Credentials cannot be null";
	private static final String USERNAME_CANNOT_BE_NULL = "Username cannot be null";

	//klasa dotycząca wiadomosci o userach.
	UserDetailsService userDetailsService;

	//Wstrzykniecie przypisanie do pola,czyściejszy kod. Zasada Solid.Problemy z testowaniem.
	@Autowired
	public InMemoryAuthenticationProvider(UserDetailsService userDetailsService) {
		this.userDetailsService = userDetailsService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		//Pobranie username
		String name = authentication.getName();

		//Pobranie credentials, jako obiekt bo tak są zwracane wAuthentication.
		Object credentials = authentication.getCredentials();


		//Spawdzenie czy nie jest null.Wyciagnięcie do stałej

		Assert.notNull(name, USERNAME_CANNOT_BE_NULL);
		Assert.notNull(credentials, CREDENTIALS_CANNOT_BE_NULL);

		//Sprawdzenie czy jest instancja klasy
		if(!(credentials instanceof String)){
		return null;
		}

		String password = credentials.toString();

		//Pobieramy użytkownika
		UserDetails userDetails = userDetailsService.loadUserByUsername(name);
		
		if(!password.equals(userDetails.getPassword())) {
			throw new BadCredentialsException(INCORRECT_PASSWORD);
		}
		
		
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(name, password,
				userDetails.getAuthorities());
		return auth;
	}
	//Czy klasa rowna sie klasie z Tokenem
	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);

	}



}

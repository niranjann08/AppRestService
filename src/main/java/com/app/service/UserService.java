package com.app.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.app.entities.User;
import com.app.exceptions.PasswordEmptyException;
import com.app.exceptions.UserAlreadyExistException;
import com.app.repositories.UserRepository;

@Service
@Transactional
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Lazy
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {

		User loadedUser = userRepository.findByEmail(email).orElseThrow(
				() -> new UsernameNotFoundException("This user is not found"));

		return new org.springframework.security.core.userdetails.User(
				loadedUser.getEmail(), loadedUser.getPassword(),
				getAuthorities(loadedUser));
	}

	private Collection<GrantedAuthority> getAuthorities(User user) {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>(1);
		return authList;
	}

	public User createUser(User user) {
		if (StringUtils.isEmpty(user.getPassword())) {
			throw new PasswordEmptyException();
		}

		if (userRepository.findByEmail(user.getEmail()).isPresent()) {
			throw new UserAlreadyExistException();
		}

		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public User authenticateUser(String usernameOrEmail, String rawPassword) {
		User loadedUser = userRepository.findByUsernameOrEmail(usernameOrEmail)
				.orElseThrow(
						() -> new UsernameNotFoundException(
								"Username or email not found"));

		Authentication authToken = new UsernamePasswordAuthenticationToken(
				loadedUser.getEmail(), rawPassword);
		authToken = authenticationManager.authenticate(authToken);
		SecurityContextHolder.getContext().setAuthentication(authToken);
		return loadedUser;
	}
}

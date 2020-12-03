package com.tml.poc.Wallet.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tml.poc.Wallet.models.UserModel;
import com.tml.poc.Wallet.repository.UserRepository;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	/**
	 * here we are returning registered user with jwt wrapper detail for JWT
	 */
	@Override
	public JwtUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
//        .filter(user -> user.getUsername().equals(username)).findFirst();
		JwtUserDetails jwtUserDetails = null;
		Optional<UserModel> userModel = userRepository.findAllByMobileNumber(username);

		if (!userModel.isPresent()) {
			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
		} else {
			jwtUserDetails = new JwtUserDetails(userModel.get().getId(), userModel.get().getMobileNumber(),
					userModel.get().getOtp(), "user");
		}
		return jwtUserDetails;
	}

}

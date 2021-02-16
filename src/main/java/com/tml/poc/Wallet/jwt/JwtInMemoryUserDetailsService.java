package com.tml.poc.Wallet.jwt;

import java.util.Optional;

import com.tml.poc.Wallet.services.OTPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tml.poc.Wallet.models.webuser.WebUserModel;
import com.tml.poc.Wallet.models.usermodels.UserModel;
import com.tml.poc.Wallet.repository.WebUserRepository;
import com.tml.poc.Wallet.repository.UserRepository;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private OTPService otpService;
	
	@Autowired
	private WebUserRepository webUserRepository;

	/**
	 * here we are returning registered user with jwt wrapper detail for JWT
	 */
	@Override
	public JwtUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//    Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
//        .filter(user -> user.getUsername().equals(username)).findFirst();
		
		
		JwtUserDetails jwtUserDetails = null;
		Optional<UserModel> userModel = userRepository.findByQrCode(username);

		if (!userModel.isPresent()) {
			Optional<WebUserModel> empOptional = webUserRepository.findAllByEmailidAndIsActive(username,true);
			if(empOptional.isPresent()) {
				jwtUserDetails = new JwtUserDetails(empOptional.get().getId(), empOptional.get().getEmailid(),
						empOptional.get().getPassword(),empOptional.get().getRoleId().getRoleName());
				return jwtUserDetails;
			}
			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
		} else {
			jwtUserDetails = new JwtUserDetails(userModel.get().getId(),
					userModel.get().getQrCode(),
					userModel.get().getSaltKey(),
					"user");
		}
		return jwtUserDetails;
	}
		
}

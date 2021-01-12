package com.tml.poc.Wallet.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tml.poc.Wallet.models.EmployeeModel;
import com.tml.poc.Wallet.models.UserModel;
import com.tml.poc.Wallet.repository.EmployeeRepository;
import com.tml.poc.Wallet.repository.UserRepository;

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmployeeRepository employeeRepository;

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
			Optional<EmployeeModel> empOptional = employeeRepository.findAllByEmailidAndIsActive(username,true);
			if(empOptional.isPresent()) {
				jwtUserDetails = new JwtUserDetails(empOptional.get().getId(), empOptional.get().getEmailid(),
						empOptional.get().getPassword(),empOptional.get().getRoleId().getRoleName());
				return jwtUserDetails;
			}
			throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
		} else {
			jwtUserDetails = new JwtUserDetails(userModel.get().getId(),
					userModel.get().getQrCode(),
					userModel.get().getOtp(),
					"user");
		}
		return jwtUserDetails;
	}
		
}

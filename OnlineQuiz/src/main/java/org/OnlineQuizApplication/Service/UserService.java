package org.OnlineQuizApplication.Service;

import org.OnlineQuizApplication.Entity.Users;
import org.OnlineQuizApplication.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	public Users createUser(Users user) {
		return userRepository.save(user);
	}

}

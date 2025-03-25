package org.OnlineQuizApplication.Repository;

import org.OnlineQuizApplication.Entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users, Long> {

	Users findByUsername(String username);

}

package coms.journalproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import coms.journalproject.entity.Users;

public interface Usersrepository extends JpaRepository<Users,Integer> {

	
	
}

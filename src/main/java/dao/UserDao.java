package dao;

import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Transactional
public class UserDao {

	private HibernateTemplate hibernateTemplate;

	public List<User> getAllUsers() {
		return this.hibernateTemplate.loadAll(User.class);
	}

	public User getUser(int userId) {
		return this.hibernateTemplate.get(User.class, userId);
	}

	
	public Integer saveUser(User user) {
		return (Integer) this.hibernateTemplate.save(user);
	}

	
	public User updateUser(User user) {
		this.hibernateTemplate.update(user);
		return user;
	}

	
	public void deleteUser(User user) {
		this.hibernateTemplate.delete(user);
	}
}

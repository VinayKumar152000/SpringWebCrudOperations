package services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import bo.UserBo;
import dao.UserDao;
import domain.User;
import exception.InvalidInfoException;
import exception.ResourceNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import payload.UserPayload;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Service
public class UserService {

	private UserDao userDao;

	private ModelMapper modelMapper;

	public List<UserBo> getAllUsers() {

		List<UserBo> usersbo = new ArrayList<UserBo>();
		List<User> users = userDao.getAllUsers();
		for (User user : users) {
			usersbo.add(UsertoUserBo(user));
		}

		return usersbo;
	}

	public UserBo getUser(int userId) {

		User user = userDao.getUser(userId);
		if (user == null) {
			throw new ResourceNotFoundException("User Not Found", HttpStatus.NOT_FOUND);
		}
		return UsertoUserBo(user);
	}

	public Integer addUser(UserPayload userPayload) {

		if (userPayload.getUserName().equals("") || userPayload.getUserPassword().equals("")) {
			throw new InvalidInfoException("All Fields are Mandatory Please Enter Valid Data", HttpStatus.BAD_REQUEST);
		}

		User user = new User();
		user.setUserName(userPayload.getUserName());
		user.setUserPassword(userPayload.getUserPassword());
		return userDao.saveUser(user);
	}

	public UserBo updateUser(UserPayload userPayload, int userId) {
		User user = this.userDao.getUser(userId);

		if (user == null) {
			throw new ResourceNotFoundException("User Not Found", HttpStatus.NOT_FOUND);
		}

		if (userPayload.getUserName().equals("") || userPayload.getUserPassword().equals("")) {
			throw new InvalidInfoException("All Fields are Mandatory Please Enter Valid Data", HttpStatus.BAD_REQUEST);
		}

		user.setUserName(userPayload.getUserName());
		user.setUserPassword(userPayload.getUserPassword());
		return UsertoUserBo(userDao.updateUser(user));
	}

	public void deleteUser(int userId) {

		User user = this.userDao.getUser(userId);

		if (user == null) {
			throw new ResourceNotFoundException("User Not Found", HttpStatus.NOT_FOUND);
		}

		this.userDao.deleteUser(user);
	}

	public UserBo UsertoUserBo(User user) {
		UserBo userbo = this.modelMapper.map(user, UserBo.class);
		return userbo;
	}
}

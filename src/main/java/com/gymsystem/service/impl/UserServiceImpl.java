package com.gymsystem.service.impl;

import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.gymsystem.dao.impl.UserDaoImpl;
import com.gymsystem.models.User;
import com.gymsystem.service.UserService;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;


@Service
public class UserServiceImpl implements UserService{
	
	private final Counter counter = Metrics.counter("UserService_requests_total");

	@Autowired
	private UserDaoImpl userDao;
	
	@Override
	public User createUser(User user) {
		user = generateUsername(user);
		user = generatePassword(user);
		return userDao.save(user);
	}

	@Override
	public User getUserById(Long id) {
		return userDao.getUserById(id);
	}

	@Override
	public User updateUser(User user) {
		return userDao.save(user);
	}

	@Override
	public void deleteUser(Long id) {
	    userDao.deleteById(id);
	}

	@Override
	public List<User> getAllUsers() {
	    return userDao.getAllUsers();
	}
	
	private User generateUsername(User user) {
		String username = user.getFirstName().concat(".").concat(user.getLastName());
		List<User> users = userDao.getAllUsers();
		Iterator<User> iterator = users.iterator();
		while(iterator.hasNext()) {
			if(iterator.next().getUsername().equals(username)) {
				username.concat("2");
				break;
			}
		}
		user.setUsername(username);
		
		return user;
	}
	
	private User generatePassword(User user) {
		int passwordLength = 10;
		String password = RandomStringUtils.randomAlphabetic(passwordLength);
		user.setPassword(password);
		return user;
	}

	public boolean checkHealth() {
		User user = createUser(new User("testUser","test",false));
		if(user.getFirstName() != null) {
			return true;
		}
		return false;
	}
	
	public String handleRequest() {
        counter.increment();
        return "Counter metric";
    }
}

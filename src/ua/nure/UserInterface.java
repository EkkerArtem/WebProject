package ua.nure;

import java.util.List;

public interface UserInterface {
		
		public List<User> getAllUsers();
		public void addUser (User user);
		public void updateUser(User userhave, User userwant);
		public void deleteUser(User userdel);
		public void putToDB(List<User> list);
	}
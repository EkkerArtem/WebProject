package ua.nure;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySQLUserDAO implements UserInterface, ObserverAdmin {
	private static MySQLUserDAO dao = null;
	public MySQLUserDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static MySQLUserDAO getInctance() {
		if(dao == null) {
			dao = new MySQLUserDAO();
		}return dao;
	}

	@Override
	public void putToDB(List<User> list2) {
		for(int i = 0;i<list2.size();i++) {
			addUser(list2.get(i));
		}
	}

	@Override
	public List<User> getAllUsers() {
String url = "jdbc:mysql://localhost/GamesDB";


		Connection con = null;
		try {
			con = DriverManager.getConnection(url, "root", "");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement stm1 = null;
		try {
			 stm1 = con.prepareStatement("SELECT * FROM USER");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<User> list=new ArrayList<>();
		try {
			ResultSet rs = stm1.executeQuery();
			while (rs.next()){
				User tempUser = new User.Builder(rs.getString(2),rs.getString(4)).phone(rs.getString(3)).id(rs.getInt(1)).build();
			/*	rs.getInt(1);
				rs.getString(2);x
				rs.getString(3);
				rs.getString(4);
				rs.getString(5);
				rs.getString(6);
				rs.getString(7);
				rs.getString(8);
				rs.getInt(9);*/

				list.add(tempUser);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			stm1.close();
		} catch (SQLException e111) {
			// TODO Auto-generated catch block
			e111.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e111) {
			// TODO Auto-generated catch block
			e111.printStackTrace();
		}

		return list;

	}

	@Override
	public void addUser(User user) {

		/*SessionFactory sessionFactory = null;
		StandardServiceRegistry registry = null;

		registry = new StandardServiceRegistryBuilder().configure().build();
		sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		System.out.println("--------------------------------" + user.getPhone());
		session.save(user);
		session.getTransaction().commit();
		session.close();*/

		String url = "jdbc:mysql://localhost/GamesDB";

		Connection con = null;
		try {
			con = DriverManager.getConnection(url,"root", "");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement stm = null;
		try {
			 stm = con.prepareStatement("INSERT INTO USER (Nickname, email, Phone, password, registerTime, Passport_idPassport) VALUES (?,?,?,?,?,?)");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		try {
			stm.setString(1, user.getNicke());
			stm.setString(2, user.getEmail());
			stm.setString(3, user.getPhone());
			stm.setString(4, "");
			stm.setString(5, "");
			stm.setInt(6, 1);
			System.out.println(stm.toString());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			stm.executeUpdate();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
		    stm.close();
		} catch (SQLException e111) {
		    // TODO Auto-generated catch block
		    e111.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e111) {
			e111.printStackTrace();
		}
	}

	private int a = 0;

	@Override
	public void updateUser(User userhave, User userwant) {
		String url = "jdbc:mysql://localhost/GamesDB";

		Connection con = null;
		try {
			con = DriverManager.getConnection(url, "root", "");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement s = null;

		try {
			s = con.prepareStatement("UPDATE `GamesDB`.`user` SET `Nickname`=?, `Phone`=?, `email`=?  WHERE  `Nickname`=? AND`Phone`=? AND `email`=? ;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		try {
			s.setString(1, userwant.getNicke());
			s.setString(2, userwant.getPhone());
			s.setString(3, userwant.getEmail());
			s.setString(4, userhave.getNicke());
			s.setString(5, userhave.getPhone());
			s.setString(6, userhave.getEmail());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			s.executeUpdate();
		} catch (SQLException e10) {
// TODO Auto-generated catch block
			e10.printStackTrace();
		}
		try {
			s.close();
		} catch (SQLException e111) {
			// TODO Auto-generated catch block
			e111.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e111) {
			// TODO Auto-generated catch block
			e111.printStackTrace();
		}

		try {
		int	a = s.executeUpdate();
		} catch (Exception a123) {

		}
		this.a = a;

		Notify(userhave,userwant);

	}

	public int getUpd(){
		return a;
	}

	@Override
	public void deleteUser(User userdel) {
		String url = "jdbc:mysql://localhost/GamesDB";

		Connection con = null;
		try {
			con = DriverManager.getConnection(url,"root", "");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement s1 = null;


        try {
			s1 = con.prepareStatement("DELETE FROM `GamesDB`.`user` WHERE `Nickname`=? AND`Phone`=? AND `email`=?"); //
		} catch (SQLException e21) {
			// TODO Auto-generated catch block
            e21.printStackTrace();
        }



try {
	s1.setString(1, userdel.getNicke());
	s1.setString(2, userdel.getPhone());
	s1.setString(3, userdel.getEmail());
} catch (SQLException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}

		try {
			s1.executeUpdate();
		} catch (SQLException e10) {
// TODO Auto-generated catch block
			e10.printStackTrace();
		}
try {
    s1.close();
} catch (SQLException e111) {
    // TODO Auto-generated catch block
    e111.printStackTrace();
}

		try {
			con.close();
		} catch (SQLException e111) {
			// TODO Auto-generated catch block
			e111.printStackTrace();
		}

	}

	private List<ListenerAdmin> list = new ArrayList<>();

	@Override
	public void addListener(ListenerAdmin listener) {
		list.add(listener);
	}

	@Override
	public void removeListener(ListenerAdmin listener) {
		list.remove(listener);
	}

	@Override
	public void Notify(User oldUser, User newUser) {
		for(ListenerAdmin l : list){
			l.onUserChange(oldUser, newUser);
		}
	}

	public void updatePassport(User userwant) {
		String url = "jdbc:mysql://localhost/GamesDB";

		Connection con = null;
		try {
			con = DriverManager.getConnection(url, "root", "");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		PreparedStatement se = null;

		try {
			se = con.prepareStatement("UPDATE `Passport_idPassport`=?  WHERE  `Nickname`=? AND`Phone`=? AND `email`=? AND `Passport_idPassport`=3;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		try {
			se.setInt(1,1);
			se.setString(2, userwant.getNicke());
			se.setString(3, userwant.getPhone());
			se.setString(4, userwant.getEmail());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			se.executeUpdate();
		} catch (SQLException e10) {
// TODO Auto-generated catch block
			e10.printStackTrace();
		}
		try {
			se.close();
		} catch (SQLException e111) {
			// TODO Auto-generated catch block
			e111.printStackTrace();
		}
		try {
			con.close();
		} catch (SQLException e111) {
			// TODO Auto-generated catch block
			e111.printStackTrace();
		}

}

public void resNick(User userwant, User userhave){

	String url = "jdbc:mysql://localhost/GamesDB";

	Connection con = null;
	try {
		con = DriverManager.getConnection(url, "root", "");
	} catch (SQLException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
	PreparedStatement sp = null;

	try {
		sp = con.prepareStatement("UPDATE `GamesDB`.`user` SET `Nickname`=?  WHERE  `Nickname`=? AND`Phone`=? AND `email`=? ");
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}


	try {
		sp.setString(1, userhave.getNicke());
		sp.setString(2, userwant.getNicke());
		sp.setString(3, userwant.getPhone());
		sp.setString(4, userwant.getEmail());

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	try {
		sp.executeUpdate();
	} catch (SQLException e10) {
// TODO Auto-generated catch block
		e10.printStackTrace();
	}
	try {
		sp.close();
	} catch (SQLException e111) {
		// TODO Auto-generated catch block
		e111.printStackTrace();
	}
	try {
		con.close();
	} catch (SQLException e111) {
		// TODO Auto-generated catch block
		e111.printStackTrace();
	}
}



}

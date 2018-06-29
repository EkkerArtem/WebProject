package ua.nure;
public class AdmChangeDB implements ListenerAdmin {


    @Override
    public void onUserChange(User oldUser,User newUser) {
        if(!(oldUser.equals(newUser))){
            MySQLUserDAO.getInctance().updatePassport(newUser);
        }
        }

    }


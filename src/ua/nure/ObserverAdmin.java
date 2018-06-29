package ua.nure;

public interface ObserverAdmin {

    public void addListener(ListenerAdmin listener);
    public void removeListener(ListenerAdmin listener);
    public void Notify(User oldUser,User newUser);
}

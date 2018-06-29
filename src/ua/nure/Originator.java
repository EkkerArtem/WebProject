package ua.nure;
//TODO делать ли класс веб-сервлетом что бы прикрепить к кнопке?
public class Originator extends UpdateUser{

    private String nameM;

    public void setNameM(String nic){
        nameM = nic;
    }

    public Memento save(){
        return new Memento(nameM);
    }

    public void restore(Memento m){
        //TODO ВНЕСТИ запрос сюда!
    }
    }


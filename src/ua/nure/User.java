package ua.nure;


import javax.persistence.*;

@Entity
@Table(name = "Customer")
public class User {
    @Id
    @GeneratedValue
    private Integer id;

    @Column(name="nickname")
    private final String nicke;
    @Column
    private final String phone;
    @Column
    private final String email;

    public User(){
        nicke = "default";
        phone = "default";
        email = "default";

    }

    public static class Builder{
        private final String nicke;
        private final String email;
        private  String phone;
        private int id;

        public Builder(String nicke, String email/* ,String phone*/){

            this.nicke = nicke;
            this.email = email;
           // this.phone = phone;
        }
        public Builder phone(String val){
            phone = val;
            return this;
        }
        public Builder id(int idd){
            id = idd;
            return this;
        }

        public User build(){

            if(nicke == null || email == null /*|| phone == null*/){
            return null;
            }
            else
                {
                    return new User(this);
                }
        }

    }

    private User(Builder builder){

        nicke = builder.nicke;
        email = builder.email;
        phone = builder.phone;
    }


    public String getNicke() {
        return nicke;
    }

//    public void setNicke(String nicke) {
//        this.nicke = nicke;
//    }

    public String getPhone() {
        return phone;
    }

    /*public void setPhone(String phone) {
    this.phone = phone;
   }*/

    public String getEmail() {
        return email;
    }

//    public void setEmail(String email) {
//        this.email = email;
//    }



}

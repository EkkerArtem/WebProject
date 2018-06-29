package ua.nure;

import com.mongodb.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MyMongUserDAO implements UserInterface{

    private static MyMongUserDAO mdao = null;
    MongoClient m;
    DB db;
    DBCollection coll;

        public   MyMongUserDAO()
        {
            m = new MongoClient(Arrays.asList(
                     new ServerAddress("127.0.1",27001),
                     new ServerAddress("127.0.1",27002),
                     new ServerAddress("127.0.1",27003)));
            db = m.getDB("MyMong3");
            coll = db.getCollection("User");
//в конструктор
        }



    @Override
    public void putToDB(List<User> list){
        for(int i = 0;i<list.size();i++){
            addUser(list.get(i));
        }}

    public void SeartchUsers(){
       /*  Bson filter1 = Aggregates.match(Filters.eq("sex","male"));
        Bson filter2 = match(eq("country","Ukraine"));
        Bson filter3 = match(gte("higth",180));
        Bson wsort = sort(filter3);
        Bson wgroup = group("name");*/
    /*AggregateIterable<Document> result = coll.aggregate(Arrays.asList(Aggregates.match(Filters.eq(("sex","male"))));
        List<DBObject> pipe = new ArrayList<>();//
        pipe.add(filter);
        coll.aggregate(pipe);*/
/*        Bson filter1 = Aggregates.match(Filters.eq("sex", "male"));
        Bson filter2 = Aggregates.match(Filters.eq("country","Ukraine"));
        Bson filter3 = Aggregates.match(Filters.gte("hight",180));
        Bson filter4 = Aggregates.sort(filter3);
        List<AggregationOutput> pipe = new ArrayList<>();
        AggregationOutput cur = coll.aggregate((DBObject) filter1);
        cur = coll.aggregate((DBObject) filter2);
        cur = coll.aggregate((DBObject) filter3);
        cur = coll.aggregate((DBObject) filter4);
        pipe.add(cur);
        System.out.println(pipe);*/

            BasicDBObject field = new BasicDBObject();
            field.put("hight",new BasicDBObject("$gte",180));
            field.put("country","Ukraine");
            field.put("sex","male");
            BasicDBObject fieldSORT = new BasicDBObject();
            fieldSORT.put("name",1);
            DBCursor cur =  coll.find(); //.find(field);
            //BasicDBObject fieldGROUP = new BasicDBObject();
            //fieldGROUP.put("name",1)
            //coll.group();
        //cur.sort(fieldSORT);
        while(cur.hasNext()){
            DBObject o = cur.next();
            System.out.println(o.get("name"));
            System.out.println(o.get("phone"));
            System.out.println(o.get("sex"));
            System.out.println(o.get("hight"));
            System.out.println(o.get("country"));
            System.out.println("_________________________");
        }
        System.out.println("----------------");
        System.out.println(cur.count());
        System.out.println("----------------");
    }

    @Override
    public List<User> getAllUsers() {
        User temp2User = new User();
        List<User> list2 = new ArrayList<>();
        BasicDBObject field = new BasicDBObject();
        DBCursor cur =  coll.find();



        while(cur.hasNext()){
            DBObject o = cur.next();
            temp2User = new User.Builder(o.get("name").toString(),o.get("email").toString()).phone(o.get("phone").toString()).build();
            list2.add(temp2User);
        }

        return list2;

    }


    @Override
    public void addUser(User user) {
        for(int i=0; i<10000; i++){
        BasicDBObject us = new BasicDBObject();

        us.put("name", user.getNicke());
        us.put("phone", user.getPhone());
        us.put("email", user.getEmail());
        us.put("country", "Ukraine");
        us.put("sex", "male");
        us.put("hight", Math.random()*30 + 155);


            try{
                coll.insert(us);}
            catch (Exception insertex){

                try {
                    Thread.sleep(5000);
                } catch (Exception insertex2) {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        System.out.println("Ошибочка вышла");;
                    }
                }
            }


}
    }

    @Override
    public void updateUser(User userhave, User userwant) {

            BasicDBObject updhave = new BasicDBObject();
        updhave.put("name", userhave.getNicke());
        updhave.put("phone", userhave.getPhone());
        updhave.put("email", userhave.getEmail());

        BasicDBObject updwant = new BasicDBObject();
            updwant.put("name", userwant.getNicke());
            updwant.put("phone", userwant.getPhone());
            updwant.put("email", userwant.getEmail());


            coll.update(updhave,updwant);

    }

    @Override
    public void deleteUser(User userdel) {
        BasicDBObject usdel = new BasicDBObject();
        usdel.put("name", userdel.getNicke());
        usdel.put("phone", userdel.getPhone());
        usdel.put("email", userdel.getEmail());

        coll.remove(usdel);
    }
}

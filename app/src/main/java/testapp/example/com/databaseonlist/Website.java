package testapp.example.com.databaseonlist;

import java.io.Serializable;

public class Website implements Serializable{
    int id;
    String name;
    String email;
    String username;
    String password;
    String comment;
    String url;

    public Website(){

    }

    public Website(String w, String e, String u, String p, String url){
        this.name = w;
        this.email = e;
        this.username = u;
        this.password = p;
        this.comment = null;
        this.url = url;
    }


    public Website(String w, String e, String u, String p, String c, String url){
        this.name = w;
        this.email = e;
        this.username = u;
        this.password = p;
        this.comment = c;
        this.url = url;
    }
    public int getId(){return this.id;}
    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }
    public String getUsername(){return this.username;}
    public String getPassword(){
        return this.password;
    }
    public String getComment(){
        return this.comment;
    }
    public String getUrl() { return url; }

    public void setId(int id){this.id = id;}
    public void setName(String name){
        this.name = name;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setComment(String comment){
        this.comment = comment;
    }
    public void setUrl(String url) { this.url = url;}
}

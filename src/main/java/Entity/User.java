/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

/**
 *
 * @author Hjalmar
 */
public class User {

    int id;
    String username;
    String password;
    String email;
    int admin;


    public User(String name, String password, String email, int admin) {
        this.username = name;
        this.password = password;
        this.email = email;
        this.admin = admin;
    }

    public User(int id, String name, String password, String email, int admin) {
        this.id = id;
        this.username = name;
        this.password = password;
        this.email = email;
        this.admin = admin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int isAdmin() {
        return admin;
    }

    public void setAdmin(int admin) {
        this.admin = admin;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email){
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", name=" + username + ", password=" + password + ", admin=" + admin + '}';
    }
}

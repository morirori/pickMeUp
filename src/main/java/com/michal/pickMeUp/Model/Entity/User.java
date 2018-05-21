package com.michal.pickMeUp.Model.Entity;

import java.nio.file.attribute.UserPrincipal;
import java.util.HashMap;

/**
 * Created by Michał on 14.08.2017.
 */
public class User {


    private Integer id;
    private String name;
    private String lastName;
    private String password;
    private String phone;
    private  String car;
    private String email;
    private String adres;

    public User() {

    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
        userProperties.put("adres",adres);

    }

    private HashMap <String, String> userProperties = new HashMap<>();

    public User(String name, String lastName, String password, String phone, String car, String email,String adres){
        setName(name);
        setCar(car);
        setEmail(email);
        setLastName(lastName);
        setPasswod(password);
        setPhone(phone);
        setAdres(adres);
        initializeHashMap();
    }

    public User(String name, String lastName, String password, String phone, String car, String email,String adres, Integer id){

        setName(name);
        setCar(car);
        setEmail(email);
        setLastName(lastName);
        setPasswod(password);
        setPhone(phone);
        setId(id);
        setAdres(adres);
        initializeHashMap();
    }

    public User(HashMap<String,String>map){
        for (HashMap.Entry<String, String> entry : map.entrySet()) {
            if(entry.getKey().equals("id")){
                setId(Integer.parseInt(entry.getValue()));
            }
            if (entry.getKey().equals("name")){
                setName(entry.getValue());
            }
            if (entry.getKey().equals("lastName")){
                setLastName(entry.getValue());
            }
            if (entry.getKey().equals("password")) {
                setPasswod(entry.getValue());
            }
            if (entry.getKey().equals("phone")) {
                setPhone(entry.getValue());
            }
            if (entry.getKey().equals("car")) {
                setCar(entry.getValue());
            }
            if (entry.getKey().equals("email")) {
                setEmail(entry.getValue());
            }
            if (entry.getKey().equals("adres")) {
                setAdres(entry.getValue());
            }
        }

        this.userProperties= map;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        userProperties.put("name",name);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
        userProperties.put("lastName",lastName);
    }

    public String getPasswod() {
        return password;
    }

    public void setPasswod(String passwod) {
        this.password = passwod;
        userProperties.put("password",passwod);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
        userProperties.put("phone",phone);
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
        userProperties.put("id",id.toString());
    }
    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
        userProperties.put("car",car);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
        this.userProperties.put("email",email);
    }

    private void initializeHashMap(){
        userProperties.put("id",this.id.toString());
        userProperties.put("name",this.name);
        userProperties.put("lastName",this.lastName);
        userProperties.put("password",this.password);
        userProperties.put("phone",this.phone);
        userProperties.put("car",this.car);
        userProperties.put("email",this.email);
        userProperties.put("adres",this.adres);
    }

    public HashMap<String,String> getMap(){
        return this.userProperties;
    }
    public void  show(){
        for (HashMap.Entry<String,String> entry : this.userProperties.entrySet()){
            System.out.print(entry.getKey() +" - "+ entry.getValue()+"\n");

        }
    }
    }

package com.slotegrator.api;

import com.github.javafaker.Faker;

import java.util.Base64;


public class Player {
    private String name;
    private String surname;
    private String username;
    private String password;
    private String encodedPasswordString;
    private String email;
    private String currencyCode;
    private Integer id;
    private Integer country_id;
    private Integer timezone_id;
    private String gender;
    private String phone_number;
    private String birthdate;
    private Boolean bonuses_allowed;
    private Boolean is_verified;

    public Player() {
        Faker faker = new Faker();

        name = faker.name().firstName();
        surname = faker.name().lastName();
        username = name + surname + System.currentTimeMillis() % 10000;
        password = "atLeast6Chars";
        encodedPasswordString = Base64.getEncoder().encodeToString(password.getBytes());
        email = username + "@gmail.com";
        currencyCode = "RUB";
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCountry_id(Integer country_id) {
        this.country_id = country_id;
    }

    public void setTimezone_id(Integer timezone_id) {
        this.timezone_id = timezone_id;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public void setBonuses_allowed(Boolean bonuses_allowed) {
        this.bonuses_allowed = bonuses_allowed;
    }

    public void setIs_verified(Boolean is_verified) {
        this.is_verified = is_verified;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getUsername() {
        return username;
    }

    public String getEncodedPasswordString() {
        return encodedPasswordString;
    }

    public String getEmail() {
        return email;
    }

}

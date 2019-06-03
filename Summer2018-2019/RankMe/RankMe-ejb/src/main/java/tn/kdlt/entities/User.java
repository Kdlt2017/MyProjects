/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.kdlt.entities;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;
import javax.validation.constraints.Pattern;

/**
 *
 * @author pc
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn( name = "USER_TYPE")
public abstract class User implements Serializable{

     	//@TableGenerator(name = "USER_GEN", table = "ID_GEN", pkColumnName = "GEN_NAME", valueColumnName = "GEN_VAL", allocationSize = 1)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
    @Column(unique = true)
    @Pattern(regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+"
            + "(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\""
            + "(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21"
            + "\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b"
            + "\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)"
            + "+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|"
            + "[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|"
            + "[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-"
            + "\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])",
            message = "{invalid.email}")
    private String email;

    @Column(unique = true)
    private String username;

    private String firstName;

    private String lastName;
   

    public User() {
    }

    public User(String username, String email, String firstName, String lastName, byte[] password) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
    }

    public User(String username, String email, String firstName, String lastName) {
        this.username = username;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    //Getting the discriminator value from the database;
    @Transient
    public String getRole() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public byte[] getSalt() {
        return salt;
    }

    public void setSalt(byte[] salt) {
        this.salt = salt;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) throws NoSuchAlgorithmException {
        this.salt = new byte[32];
        new Random().nextBytes(salt);
        byte[] hashBytes = new byte[password.length + this.salt.length];
        System.arraycopy(password, 0, hashBytes, 0, password.length);
        System.arraycopy(this.salt, 0, hashBytes, password.length, this.salt.length);
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        this.password = digest.digest(hashBytes);
    }

    @Column(columnDefinition = "BINARY(32) NOT NULL")
    private byte[] salt;

    @Column(columnDefinition = "BINARY(32) NOT NULL")
    private byte[] password;

    

}

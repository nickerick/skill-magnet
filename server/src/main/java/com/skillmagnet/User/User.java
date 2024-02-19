package com.skillmagnet.User;

import jakarta.persistence.*;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private int id;

    private String username;
    private String password_hash;
    //private Date last_login;

    public User() {}

    public User(String username, String plainTextPassword) {
        this.username = username;
        // Hash the plain-text password
        this.password_hash = hashPassword(plainTextPassword);
        // Save last_login
    }

    public String getUsername() {
        return this.username;
    }

    public int getId() {
        return this.id;
    }

    public String getPassword_hash() { return password_hash; }

    //public Date getLastLogin() { return last_login; }

    /**
     * Helper method for converting the plaintext password to a hashed string before storing the user's password in the
     * database. The implementation uses the Argon2id hash algorithm provided by the Spring Security Crypto library.
     * The output string is the Argon2id hashing result(The '$' separates encoder configuration elements, and the final
     * element of the string is the hashed password).
      * @param plainTextPassword - the raw string password prior to hashing
     * @return - the hashed string in the Argon2id format.
     */
    private String hashPassword(String plainTextPassword) {
        /*
           Creates an Argon2 Encoder (Argon2id by default) that can be configured via the arguments. It is generally
           recommended to use a 16 byte salt and 32 byte hash length. The rest should be configured to match the
           capabilities of the server.
           Arguments:
            - int saltLength
            - int hashLength
            - int parallelism
            - int memory (memory=32*1024 sets the memory cost to ~32 MB)
            - int iterations
         */
        Argon2PasswordEncoder argonEncoder = new Argon2PasswordEncoder(16, 16, 1, 32*1024, 2);
        return argonEncoder.encode(plainTextPassword);
    }

    public Boolean passwordMatch(String plaintextPassword) {
        Argon2PasswordEncoder argonEncoder = new Argon2PasswordEncoder(16, 16, 1, 32*1024, 2);
        return argonEncoder.matches(plaintextPassword, password_hash);
    }
}
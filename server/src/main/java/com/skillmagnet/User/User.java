package com.skillmagnet.User;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private int id;

    private String username;
    // Annotation prevents the password from being included in the response body
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password_hash;
    private LocalDateTime last_login;

    public User() {}

    public User(String username, String plainTextPassword) {
        this.username = username;
        // Hash the plain-text password first
        this.password_hash = hashPassword(plainTextPassword);
        this.last_login = LocalDateTime.now();
    }

    public int getId() {
        return this.id;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword_hash() { return this.password_hash; }

    public LocalDateTime getLastLogin() { return this.last_login; }

    public void setLast_login(LocalDateTime updated_login_time) { this.last_login = updated_login_time; }

    /**
     * Password hashing method using the Argon2id hash algorithm provided by the Spring Security Crypto library.
     * The output string is the Argon2id hashing result.
      * @param plainTextPassword - the raw string password prior to hashing
     * @return - the hashed string in the Argon2id format (uses '$' as a delimiter for the Argon Algorithm in hashed result).
     */
    private String hashPassword(String plainTextPassword) {
        // Argon2id Encoder w/ 16 byte salt, 16 byte hash length. The remaining parameters need to be configured to match
        // the capabilities of the server.
        //  - Memory Parameter Note: memory=32*1024 sets the memory cost to ~32 MB
        Argon2PasswordEncoder argonEncoder = new Argon2PasswordEncoder(16, 16, 1, 32*1024, 2);
        return argonEncoder.encode(plainTextPassword);
    }

    /**
     * Hashes a plaintext password and compares it to the stored password hash of this user.
     * @param plaintextPassword - input raw password
     * @return - boolean result of match comparison
     */
    public Boolean passwordMatch(String plaintextPassword) {
        Argon2PasswordEncoder argonEncoder = new Argon2PasswordEncoder(16, 16, 1, 32*1024, 2);
        return argonEncoder.matches(plaintextPassword, password_hash);
    }
}
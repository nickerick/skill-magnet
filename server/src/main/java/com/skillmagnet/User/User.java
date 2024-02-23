package com.skillmagnet.User;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue
    private int id;

    private String username;

    // Annotation prevents the password from being included in the response body
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String passwordHash;

    private LocalDateTime lastLogin;

    public User(String username, String plaintextPassword) {
        this.username = username;
        // Hash the plain-text password first
        this.passwordHash = hashPassword(plaintextPassword);
        this.lastLogin = LocalDateTime.now();
    }

    /**
     * Password hashing method using the Argon2id hash algorithm provided by the Spring Security Crypto library.
     * The output string is the Argon2id hashing result.
     *
     * @param plaintextPassword - the raw string password prior to hashing
     * @return - the hashed string in the Argon2id format (uses '$' in the hashed result as a delimiter for the Argon Algorithm).
     */
    private String hashPassword(String plaintextPassword) {
        // Argon2id Encoder w/ 16 byte salt, 16 byte hash length. The remaining parameters need to be configured to match
        // the capabilities of the server.
        //  - Memory Parameter Note: memory=32*1024 sets the memory cost to ~32 MB
        Argon2PasswordEncoder argonEncoder = new Argon2PasswordEncoder(16, 16, 1, 32 * 1024, 2);
        return argonEncoder.encode(plaintextPassword);
    }

    /**
     * Hashes a plaintext password and compares it to the stored password hash of this user.
     *
     * @param plaintextPassword - input raw password
     * @return - boolean result of match comparison
     */
    public Boolean passwordMatch(String plaintextPassword) {
        Argon2PasswordEncoder argonEncoder = new Argon2PasswordEncoder(16, 16, 1, 32 * 1024, 2);
        return argonEncoder.matches(plaintextPassword, passwordHash);
    }
}
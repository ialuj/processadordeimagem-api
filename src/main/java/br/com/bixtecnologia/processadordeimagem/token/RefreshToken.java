package br.com.bixtecnologia.processadordeimagem.token;

import java.time.Instant;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity(name = "RefreshToken")
@Table(name = "refresh_tokens")
public class RefreshToken {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "ID",
            nullable = false
    )
    private Long id;

    @NotBlank
    @Column(name = "USER_NAME", nullable = false, length = 100)
    private String username;

    @NotBlank
    @Column(name = "REFRESH_TOKEN", nullable = false, length = 500)
    private String refreshToken;

    @NotNull
    @Column(name = "REVOKED", nullable = false)
    private Boolean revoked;

    @NotNull
    @Column(name = "CREATION_DATE", nullable = false)
    private Instant dateCreated;

    public RefreshToken(@NotBlank String username, @NotBlank String refreshToken, @NotNull Boolean revoked) {
        this.username = username;
        this.refreshToken = refreshToken;
        this.revoked = revoked;
        this.dateCreated = new Date().toInstant();
    }

    public RefreshToken() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank String username) {
        this.username = username;
    }

    public @NotBlank String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(@NotBlank String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public @NotNull Boolean getRevoked() {
        return revoked;
    }

    public void setRevoked(@NotNull Boolean revoked) {
        this.revoked = revoked;
    }

    public @NotNull Instant getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(@NotNull Instant dateCreated) {
        this.dateCreated = dateCreated;
    }

    @Override
    public String toString() {
        return "RefreshToken{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", revoked=" + revoked +
                ", dateCreated=" + dateCreated +
                '}';
    }
}

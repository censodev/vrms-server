package io.github.censodev.vrms.vrmsserver.data.models;

import com.censodev.jauthutils.jwt.Credentials;
import io.github.censodev.vrms.vrmsserver.utils.enums.RoleEnum;
import io.github.censodev.vrms.vrmsserver.utils.enums.StatusEnum;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "account")
@Getter
@Setter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class Account implements Credentials {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private String otp;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Override
    public Object getSubject() {
        return id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public List<String> getAuthorities() {
        return new ArrayList<>(List.of(role.name()));
    }
}
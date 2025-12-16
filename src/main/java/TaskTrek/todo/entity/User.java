package TaskTrek.todo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "APP_USER")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(nullable = false, unique = true, length = 50)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean emailVerified = false;  // 이메일 인증 완료 여부

    @Column(nullable = false)
    private boolean isDeleted = false;      // 소프트 삭제 여부

    @Builder
    public User(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    // 비즈니스: 이메일 인증 완료 처리
    public void verifyEmail() {
        this.emailVerified = true;
    }

    // 비즈니스: 소프트 삭제 처리
    public void softDelete() {
        this.isDeleted = true;
    }
}
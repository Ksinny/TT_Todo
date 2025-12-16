package TaskTrek.todo.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class EmailVerification extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String verificationCode; // 인증 코드

    @Column(nullable = false)
    private LocalDateTime expirationTime; // 코드 만료 시간

    @Column(nullable = false)
    private boolean isUsed = false; // 코드 사용 여부

    // 인증 코드 유효 시간 정의
    private static final long EXPIRATION_MINUTES = 5;

    @Builder
    public EmailVerification(String email, String verificationCode) {
        this.email = email;
        this.verificationCode = verificationCode; // 만료 시간 설정
        this.expirationTime = LocalDateTime.now().plusMinutes(EXPIRATION_MINUTES);
    }

    // 비즈니스: 코드 사용 처리
    public void useCode() {
        this.isUsed = true;
    }

    // 비즈니스: 코드 만료 여부 확인
    public boolean isExpired() {
        return LocalDateTime.now().isAfter(this.expirationTime);
    }
}
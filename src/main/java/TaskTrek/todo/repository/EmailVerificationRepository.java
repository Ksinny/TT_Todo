package TaskTrek.todo.repository;

import TaskTrek.todo.entity.EmailVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerificationRepository extends JpaRepository<EmailVerification, Long> {

    // 가장 최근의 유효한 인증 코드 1개 선택
    Optional<EmailVerification> findTopByEmailAndVerificationCodeOrderByExpirationTimeDesc(
            String email, String verificationCode);
}
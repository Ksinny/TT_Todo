package TaskTrek.todo.repository;

import TaskTrek.todo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // 이메일 중복 체크
    boolean existsByEmail(String email);

    // 로그인 및 사용자 정보 조회
    Optional<User> findByEmail(String email);
}
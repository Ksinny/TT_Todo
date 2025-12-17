package tasktrek.todo.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import tasktrek.todo.entity.User;

public class UserRequestDto {

    /**
     * 회원가입 요청 DTO
     */
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Signup {

        @NotBlank(message = "이메일을 입력해주세요.")
        @Email(message = "유효하지 않은 이메일입니다.")
        @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
                message = "유효하지 않은 이메일입니다.")
        private String email;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$",
                message = "영문과 숫자를 포함하여 8자 이상의 비밀번호를 입력해주세요.")
        private String password;

        @NotBlank(message = "비밀번호 확인을 입력해주세요.")
        private String passwordConfirm;

        @NotBlank(message = "이름을 입력해주세요.")
        private String name;

        // 비밀번호 일치 여부 확인 로직
        @AssertTrue(message = "비밀번호가 일치하지 않습니다.")
        public boolean isPasswordConfirmed() {
            return password != null && password.equals(passwordConfirm);
        }

        public Signup(String email, String password, String name) {
            this.email = email;
            this.password = password;
            this.name = name;
        }

        // DTO를 엔티티로 변환
        public static User toEntity(Signup dto) {
            return User.builder()
                    .email(dto.getEmail())
                    .password(dto.getPassword())
                    .name(dto.getName())
                    .build();
        }
    }

    /**
     * 로그인 요청 DTO
     */
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Login {

        @NotBlank(message = "이메일을 입력해주세요.")
        @Email(message = "유효하지 않은 이메일입니다.")
        private String email;

        @NotBlank(message = "비밀번호를 입력해주세요.")
        private String password;
    }

    /**
     * 이메일 인증번호 발송 요청 DTO
     */
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class EmailCheck {

        @NotBlank(message = "이메일을 입력해주세요.")
        @Email(message = "유효하지 않은 이메일입니다.")
        private String email;
    }

    /**
     * 이메일 인증번호 검증 요청 DTO
     */
    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class EmailAuth {

        @NotBlank(message = "이메일을 입력해주세요.")
        @Email(message = "유효하지 않은 이메일입니다.")
        private String email;

        @NotBlank(message = "인증 코드를 입력해주세요.")
        private String code;
    }
}
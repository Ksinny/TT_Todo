package tasktrek.todo.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class BasicResponseDto<T> {
    private final boolean success;
    private final String message;
    private final T data;


    // 성공 응답 (데이터 포함)
    public static <T> BasicResponseDto<T> success(T data, String message) {
        return new BasicResponseDto<>(true, message, data);
    }

    // 성공 응답
    public static <T> BasicResponseDto<T> success(String message) {
        return new BasicResponseDto<>(true, message, null);
    }

    // 실패 응답
    public static BasicResponseDto<Object> fail(String message) {
        return new BasicResponseDto<>(false, message, null);
    }
}
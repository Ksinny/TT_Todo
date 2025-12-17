package tasktrek.todo.dto;

import tasktrek.todo.entity.User;
import lombok.*;

public class UserResponseDto {

    @Getter
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    public static class Response {
        private Long id;
        private String email;
        private String name;


        @Builder
        public Response(Long id, String email, String name) {
            this.id = id;
            this.email = email;
            this.name = name;
        }

        // 엔티티를 DTO로 변환
        public static Response from(User user) {
            return Response.builder()
                    .id(user.getId())
                    .email(user.getEmail())
                    .name(user.getName())
                    .build();
        }
    }
}
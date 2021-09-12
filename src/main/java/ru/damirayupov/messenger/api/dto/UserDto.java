package ru.damirayupov.messenger.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.damirayupov.messenger.api.models.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String username;
    private String phoneNumber;
    private String status;
    private String createAt;

    public static UserDto from (User user){
        return UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .createAt(user.getCreateAt().toString())
                .build();
    }

    public static List<UserDto> from(List<User> users) {
        return users.stream().map(UserDto::from).collect(Collectors.toList());
    }
}

package al.crystal.conferenceApp.mapper;

import al.crystal.conferenceApp.dto.UserDto;
import al.crystal.conferenceApp.model.User;

public class UserMapper {

    public static UserDto toDto(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }
}

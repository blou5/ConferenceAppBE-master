package al.crystal.conferenceApp.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ParticipantDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public ParticipantDTO(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}

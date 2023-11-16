package al.crystal.conferenceApp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Data
@Builder
public class OrganizerDTO {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String companyName;
    private String biography;
    private String linkedinUrl;
    private String tweeterUrl;
    private String facebookUrl;
    private String instagramUrl;

}

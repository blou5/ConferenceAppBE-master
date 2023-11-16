package al.crystal.conferenceApp.dto.speaker;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpeakerDTO {
    private Long id;
    private String name;
    private String lastName;
    private String companyName;
    private String biography;
    private String title;
    private String linkedinUrl;
    private String tweeterUrl;
    private String facebookUrl;
    private String instagramUrl;

}

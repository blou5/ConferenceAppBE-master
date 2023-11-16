package al.crystal.conferenceApp.dto.speaker;

import al.crystal.conferenceApp.model.SpeakerRate;
import lombok.*;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor

public class SpeakerRateDTO {
    private long id;
    private String name;
    private String lastName;
    private String companyName;
    private String biography;
    private String title;
    private String linkedinUrl;
    private String tweeterUrl;
    private String facebookUrl;
    private String instagramUrl;
    private List<SpeakerRate> ratings;
}

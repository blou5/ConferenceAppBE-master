package al.crystal.conferenceApp.dto.speaker;

import al.crystal.conferenceApp.model.Participant;
import al.crystal.conferenceApp.model.Speaker;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpeakerParticipantRateDTO {

    private Participant participant;
    private Speaker speaker;
    private Integer rating;
}

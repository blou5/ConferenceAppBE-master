package al.crystal.conferenceApp.dto;

import al.crystal.conferenceApp.dto.speaker.SpeakerDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@AllArgsConstructor
@Builder
@Data
public class SpeakerRateForRatingDto {

    private ParticipantDTO participantDTO;
    private SpeakerDTO speakerDTO;
    private Integer rate;

    @Override
    public String toString() {
        return "SpeakerRateForRatingDto{" +
                "participantDTO=" + participantDTO +
                ", speakerDTO=" + speakerDTO +
                ", rate=" + rate +
                '}';
    }
}

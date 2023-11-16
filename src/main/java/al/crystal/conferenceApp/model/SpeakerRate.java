package al.crystal.conferenceApp.model;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class SpeakerRate {

    @EmbeddedId
    private SpeakerRateId id = new SpeakerRateId();

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private Participant participant;

    @ManyToOne
    @MapsId("speakerId")
    @JoinColumn(name = "speaker_id")
    private Speaker speaker;
    @Nullable
    private Integer rating;

}

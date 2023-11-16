package al.crystal.conferenceApp.model;

import lombok.*;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
@Entity(name = "participant")
@AllArgsConstructor
@NoArgsConstructor
@DiscriminatorValue(value = "P") //Type.Values.PARTICIPANT
public class Participant extends User {

    @OneToMany(mappedBy = "participant")
    private List<SpeakerRate> speakerRatings;

    @OneToMany(mappedBy = "participant")
    private List<ParticipantSession> participantSessionList;

}

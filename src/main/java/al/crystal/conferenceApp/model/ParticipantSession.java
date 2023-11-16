package al.crystal.conferenceApp.model;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ParticipantSession {

    @EmbeddedId
    private ParticipantSessionId id=new ParticipantSessionId();
    private Integer rating;

    @ManyToOne
    @MapsId("sessionId")
    private Session session;

    @ManyToOne
    @MapsId("userId")
    private Participant participant;

    public ParticipantSession(int rating, Session session, Participant participant) {
        this.rating = rating;
        this.session = session;
        this.participant = participant;
    }
}

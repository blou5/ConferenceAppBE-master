package al.crystal.conferenceApp.model;

import lombok.*;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class SpeakerRateId implements Serializable {

    private Long userId;
    private Long speakerId;

}

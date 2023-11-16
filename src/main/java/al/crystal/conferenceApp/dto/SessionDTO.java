package al.crystal.conferenceApp.dto;

import al.crystal.conferenceApp.dto.speaker.SpeakerDTO;
import al.crystal.conferenceApp.model.Event;
import al.crystal.conferenceApp.model.ParticipantSession;
import al.crystal.conferenceApp.model.Track;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SessionDTO {

    private Long id;
    private String title;
    private String description;
    private String type;
    private int capacity;
    //    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime startTime;
    //    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", shape = JsonFormat.Shape.STRING)
    private LocalDateTime endTime;
    private Track track;
    private Event event;
    private List<SpeakerDTO> speakersDTO;
    private Integer participation;
//    private int participation; //map sessionRatings size


}

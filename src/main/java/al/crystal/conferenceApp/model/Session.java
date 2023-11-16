package al.crystal.conferenceApp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity(name = "session")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Session {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String type;
    private int capacity;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startTime;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endTime;

    @ManyToOne
    @JoinColumn(name = "track_id", nullable = false)
    private Track track;

    @ManyToOne
    private Event event;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "session_speaker",
            joinColumns = @JoinColumn(name = "session_id"),
            inverseJoinColumns = @JoinColumn(name = "speaker_id")
    )
    private List<Speaker> speakers;

    @OneToMany(mappedBy = "session")
    private List<ParticipantSession> sessionRatings;
}

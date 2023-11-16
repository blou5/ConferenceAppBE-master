package al.crystal.conferenceApp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tracks")
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Track implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String trackName;
    private String roomLocation;
    private String roomType;


    @JsonIgnore
    @OneToMany(mappedBy = "track")
    private List<Session> sessions;

}

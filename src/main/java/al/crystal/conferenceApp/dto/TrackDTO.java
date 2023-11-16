package al.crystal.conferenceApp.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class TrackDTO {

    private Long id;
    private String trackName;
    private String roomLocation;
    private String roomType;

}

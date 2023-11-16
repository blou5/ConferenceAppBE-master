package al.crystal.conferenceApp.service;

import al.crystal.conferenceApp.dto.TrackDTO;
import al.crystal.conferenceApp.mapper.SessionMapper;
import al.crystal.conferenceApp.mapper.TrackMapper;
import al.crystal.conferenceApp.model.Session;
import al.crystal.conferenceApp.model.Track;
import al.crystal.conferenceApp.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrackService {

    @Autowired
    private TrackRepository trackRepository;

    public Track createTrack(TrackDTO trackDTO) {
        Track newTrack = Track.builder()
                .trackName(trackDTO.getTrackName())
                .roomLocation(trackDTO.getRoomLocation())
                .roomType(trackDTO.getRoomType()).build();
       return  trackRepository.save(newTrack);

    }

    public Track getTrack(Long id) {
        return trackRepository.getReferenceById(id);
    }

    public List<Track> saveTracks(List<TrackDTO> trackDTOList) {
        List<Track> collect = trackDTOList.stream().map(trackDTO -> Track.builder()
                .trackName(trackDTO.getTrackName())
                .roomLocation(trackDTO.getRoomLocation())
                .roomType(trackDTO.getRoomType()).build()).collect(Collectors.toList());
        return trackRepository.saveAll(collect);
    }

    public List<TrackDTO> getTracksList() {
        List<Track> trackList = trackRepository.findAll();
        return trackToTrackDTO(trackList);
    }

    public List<TrackDTO> trackToTrackDTO(List<Track> trackList) {
        return trackList.stream()
                .map(track -> TrackMapper.Instance.trackToTrackDTO(track))
                .collect(Collectors.toList());
    }
}

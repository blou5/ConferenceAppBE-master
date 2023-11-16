package al.crystal.conferenceApp.mapper;

import al.crystal.conferenceApp.dto.TrackDTO;
import al.crystal.conferenceApp.model.Track;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TrackMapper {

    al.crystal.conferenceApp.mapper.TrackMapper Instance = Mappers.getMapper(al.crystal.conferenceApp.mapper.TrackMapper.class);


    TrackDTO trackToTrackDTO(Track track);

    Track trackDTOToTrack(TrackDTO trackDTO);

}

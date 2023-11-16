package al.crystal.conferenceApp.mapper;

import al.crystal.conferenceApp.dto.speaker.SpeakerDTO;
import al.crystal.conferenceApp.model.Speaker;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SpeakerMapper {

    SpeakerMapper Instance= Mappers.getMapper(SpeakerMapper.class);


    SpeakerDTO speakerDto(Speaker speaker);

    Speaker speaker(SpeakerDTO speakerDTO);
}

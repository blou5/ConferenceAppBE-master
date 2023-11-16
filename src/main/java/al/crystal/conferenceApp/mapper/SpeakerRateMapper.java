package al.crystal.conferenceApp.mapper;

import al.crystal.conferenceApp.dto.speaker.SpeakerDTO;
import al.crystal.conferenceApp.dto.speaker.SpeakerRateDTO;
import al.crystal.conferenceApp.model.Speaker;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")

public interface SpeakerRateMapper {

    SpeakerRateMapper Instance= Mappers.getMapper(SpeakerRateMapper.class);

    SpeakerRateDTO speakerRateDTO(SpeakerDTO speakerDTO);
    Speaker speaker(SpeakerRateDTO speakerRateDTO);
}

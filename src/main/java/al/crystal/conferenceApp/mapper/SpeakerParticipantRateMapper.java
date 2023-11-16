package al.crystal.conferenceApp.mapper;


import al.crystal.conferenceApp.dto.speaker.SpeakerParticipantRateDTO;
import al.crystal.conferenceApp.model.SpeakerRate;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SpeakerParticipantRateMapper {

    SpeakerParticipantRateMapper Instance= Mappers.getMapper(SpeakerParticipantRateMapper.class);

    SpeakerRate speakerRate(SpeakerParticipantRateDTO speakerParticipantRateDTO);
}

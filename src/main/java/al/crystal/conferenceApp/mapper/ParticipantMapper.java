package al.crystal.conferenceApp.mapper;

import al.crystal.conferenceApp.dto.ParticipantDTO;
import al.crystal.conferenceApp.model.Participant;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ParticipantMapper {

    ParticipantMapper Instance= Mappers.getMapper(ParticipantMapper.class);

    Participant participant(ParticipantDTO participantDTO);

}

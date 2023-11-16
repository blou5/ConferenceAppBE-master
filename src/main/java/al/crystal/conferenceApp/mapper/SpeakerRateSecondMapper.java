package al.crystal.conferenceApp.mapper;

import al.crystal.conferenceApp.dto.SpeakerRateForRatingDto;
import al.crystal.conferenceApp.model.SpeakerRate;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SpeakerRateSecondMapper {
    SpeakerRateSecondMapper Instance= Mappers.getMapper(SpeakerRateSecondMapper.class);
    @Mapping(source = "participant",target = "participantDTO")
    @Mapping(source = "speaker",target = "speakerDTO")
    SpeakerRateForRatingDto speakerRateDto(SpeakerRate speakerRate);
}

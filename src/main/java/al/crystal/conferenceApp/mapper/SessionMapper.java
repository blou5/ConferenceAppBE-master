package al.crystal.conferenceApp.mapper;

import al.crystal.conferenceApp.dto.SessionDTO;
import al.crystal.conferenceApp.model.Session;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface SessionMapper {
    SessionMapper Instance= Mappers.getMapper(SessionMapper.class);

    @Mapping(source ="speakers",target = "speakersDTO")
     SessionDTO sessionToSessionDTO(Session session);

    Session sessionDTOToSession(SessionDTO sessionDTO);
}

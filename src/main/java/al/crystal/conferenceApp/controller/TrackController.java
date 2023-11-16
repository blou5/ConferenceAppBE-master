package al.crystal.conferenceApp.controller;


import al.crystal.conferenceApp.dto.TrackDTO;
import al.crystal.conferenceApp.model.Track;
import al.crystal.conferenceApp.service.TrackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/track")
public class TrackController {

    @Autowired
    private TrackService trackService;

    @PostMapping("/add")
    public Track createTrack(@RequestBody TrackDTO trackDTO) {
        System.out.println(trackDTO.toString());
        return trackService.createTrack(trackDTO);
    }

    @GetMapping("/{id}")
    public Track getTrack(@PathVariable Long id) {
        return trackService.getTrack(id);
    }

    @GetMapping
    public List<TrackDTO> getTracksList(){return  trackService.getTracksList();}
}

package al.crystal.conferenceApp.controller;

import al.crystal.conferenceApp.dto.OrganizerDTO;
import al.crystal.conferenceApp.model.Organiser;
import al.crystal.conferenceApp.service.OrganizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/admin")
public class OrganizerController {

    @Autowired
    private OrganizerService service;

    @PostMapping("/add")
    public String createOrganizer(@RequestBody OrganizerDTO organizerDTO) {
        return service.addOrganizer(organizerDTO);
    }

    @GetMapping("/get/{id}")
    public Organiser getOrganizer(@PathVariable Long id) {
        return service.getOrganizer(id);
    }
}

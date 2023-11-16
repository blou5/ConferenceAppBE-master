package al.crystal.conferenceApp.controller;

import al.crystal.conferenceApp.dto.SessionDTO;
import al.crystal.conferenceApp.model.Event;
import al.crystal.conferenceApp.model.ParticipantSession;
import al.crystal.conferenceApp.model.Session;
import al.crystal.conferenceApp.service.SessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/sessions")
public class SessionController {

    @Autowired
    private SessionService sessionService;

    @PostMapping
    public Session addSession(@RequestBody SessionDTO session) {
        return sessionService.createSession(session);
    }

    @GetMapping("/{id}")
    public SessionDTO getSession(@PathVariable Long id) {
        return sessionService.getOneSession(id);
    }

//    @GetMapping("event/{id}")
//    public  List<SessionDTO> getSessionsByEvent(@PathVariable Long id){
//        return sessionService.getSessionsByEvent(id);
//    }


    @GetMapping()
    public List<SessionDTO> getSessions(@RequestParam(required = false) String date,
                                        @RequestParam(required = false) String location,
                                        @RequestParam(required = false) Long eventId) {
        return this.sessionService.getSessions(date, location, eventId);
    }

    @GetMapping("/")
    public List<SessionDTO> getSessions() {
        List<SessionDTO> allSessionsDTO = sessionService.getAllSessionsDTO();
        return allSessionsDTO;
    }


    @GetMapping("/dates")
    public List<String> getSessionsDates(@RequestParam(required = false) String location,
                                         @RequestParam(required = false) Long eventId) {
        List<String> ls = this.sessionService.getSessionsDates(location, eventId);
        return ls;
    }

    @GetMapping("/locations")
    public List<String> getLocations(@RequestParam(required = false) String date,
                                     @RequestParam(required = false) Long eventId) {
        List<String> ls = this.sessionService.getSessionsLocations(date, eventId);
        return ls;
    }

    @DeleteMapping("/{id}")
    public void deleteSession(@PathVariable Long id) {
        this.sessionService.deleteSession(id);
    }

    @PutMapping(value = "/update/")
    public SessionDTO updateSession(@RequestBody SessionDTO sessionDTO) {
        return this.sessionService.updateSession(sessionDTO);
    }

//    @GetMapping
//    public double getSessionRateByParticipantId(Long participantId) {
//        return 0;
//    }

//    @GetMapping
//    public List<Integer> getAllSessionRates(Long sessionId) {
//        return null;
//    }

//    @PostMapping("/rate/{sessionId}")
//    public ResponseEntity<Boolean> rateSession(@RequestParam(required = true) String email,
//                            @PathVariable Long sessionId,
//                            @RequestParam(required = true) int rate) {
//        boolean submited= this.sessionService.rateSession(email, sessionId, rate);
//        if (submited){
//            return new ResponseEntity<>(true, HttpStatus.OK);
//        }else {
//            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
//        }
//    }

    @PostMapping("/rate")
    public ResponseEntity<Boolean> rateSession(@RequestBody Map<String, String> body) {
        String email = body.get("email");
        Long sessionId = Long.parseLong(body.get("sessionId"));
        int rate = Integer.parseInt(body.get("rateSession"));

        boolean submitted = this.sessionService.rateSession(email, sessionId, rate);
        if (submitted) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/checkrate")
    public ResponseEntity<Integer> checkRatedSession(@RequestParam(required = true) String email,
                                                     @RequestParam(required = true) Long sessionId){
        Integer rated = this.sessionService.checkRatedSession(email, sessionId);
        if (rated!=null) {
            return new ResponseEntity<>(rated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }


}

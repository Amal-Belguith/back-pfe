package com.care.careplan.Controller;



import com.care.careplan.Entity.Appointment;
import com.care.careplan.Service.AppointmentService;
import com.care.careplan.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;



    @Autowired
    private RestTemplate restTemplate;

    private static final String USER_SERVICE_URL = "http://localhost:8091/api/v1/auth/details";

    @PostMapping(value = "/add")
    public ResponseEntity<?> addApp(@RequestBody Appointment appointment) {
        if (appointmentService.existsByDoctorAndDoaAndEmail(appointment.getDoctor(), appointment.getDoa(), appointment.getTimeslot(), appointment.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Choose another date or time");
        }

        Integer userKy = getUserKy(appointment.getEmail());
        appointment.setUser_ky(userKy);
        Appointment savedAppointment = appointmentService.saveorUpdate(appointment);
        // Envoyer un email de confirmation après avoir sauvegardé le rendez-vous
        appointmentService.sendConfirmationEmail(savedAppointment);
        return ResponseEntity.ok(savedAppointment);
    }

    @PutMapping(value = "/update/{app_ky}")
    public ResponseEntity<?> update(@RequestBody Appointment appointment, @PathVariable("app_ky") Integer appKy) {

        if (appointmentService.existsByDoctorAndDoaAndEmail(appointment.getDoctor(), appointment.getDoa(), appointment.getTimeslot(), appointment.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Choose another date or time");
        }
        // Mettre à jour les champs de rendez-vous
        Appointment existingAppointment = appointmentService.findByApp_ky(appKy);
            existingAppointment.setAddress(appointment.getAddress());
            existingAppointment.setDoa(appointment.getDoa());
            existingAppointment.setDob(appointment.getDob());
            existingAppointment.setDoctor(appointment.getDoctor());
            existingAppointment.setEmail(appointment.getEmail());
            existingAppointment.setFirst(appointment.getFirst());
            existingAppointment.setGender(appointment.getGender());
            existingAppointment.setInjury(appointment.getInjury());
            existingAppointment.setLast(appointment.getLast());
            existingAppointment.setMobile(appointment.getMobile());
            existingAppointment.setTimeslot(appointment.getTimeslot());
            existingAppointment.setUser_ky(appointment.getUser_ky());

            // Enregistrer les modifications
            Appointment updatedAppointment = appointmentService.saveorUpdate(existingAppointment);
        // Envoyer un email de confirmation après avoir sauvegardé le rendez-vous
        appointmentService.sendConfirmationEmail(updatedAppointment);
            return ResponseEntity.ok(updatedAppointment);

    }


    @DeleteMapping("/delete/{appKy}")
    public void deleteApp(@PathVariable("appKy") Integer appKy) {
        appointmentService.delete(appKy);
    }

    @GetMapping("/all")
    public List<Appointment> getAppointments() {
        return appointmentService.getAppointment();
    }

    private Integer getUserKy(String email) {
        UserDTO user = restTemplate.getForObject(USER_SERVICE_URL + "/" + email, UserDTO.class);
        if (user != null) {
            return user.getUser_ky();
        }
        throw new RuntimeException("User not found");
    }



}
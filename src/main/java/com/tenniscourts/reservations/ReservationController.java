package com.tenniscourts.reservations;

import com.tenniscourts.config.BaseRestController;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.repository.query.Param;

@RestController
@RequestMapping("/reservation")
@AllArgsConstructor
public class ReservationController extends BaseRestController {

    @Autowired
    private final ReservationService reservationService;

    @PostMapping
    public ResponseEntity<Void> bookReservation(CreateReservationRequestDTO createReservationRequestDTO) {
        return ResponseEntity.created(locationByEntity(reservationService.bookReservation(createReservationRequestDTO).getId())).build();
    }

    @GetMapping
    public ResponseEntity<ReservationDTO> findReservation(@Param("reservationId") Long reservationId) {
        return ResponseEntity.ok(reservationService.findReservation(reservationId));
    }
    
    @DeleteMapping
    public ResponseEntity<ReservationDTO> cancelReservation(@Param("reservationId") Long reservationId) {
        return ResponseEntity.ok(reservationService.cancelReservation(reservationId));
    }
    
    @PutMapping
    public ResponseEntity<ReservationDTO> rescheduleReservation(@Param("reservationId") Long reservationId, @Param("scheduleId") Long scheduleId) {
        return ResponseEntity.ok(reservationService.rescheduleReservation(reservationId, scheduleId));
    }
}

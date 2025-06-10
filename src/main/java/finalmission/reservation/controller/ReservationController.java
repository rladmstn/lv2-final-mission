package finalmission.reservation.controller;

import finalmission.reservation.dto.BookedReservationResponse;
import finalmission.reservation.dto.CreateReservationRequest;
import finalmission.reservation.dto.DeleteReservationRequest;
import finalmission.reservation.dto.EditReservationRequest;
import finalmission.reservation.dto.ReservationResponse;
import finalmission.reservation.service.ReservationService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping
    public ResponseEntity<ReservationResponse> create(@RequestBody CreateReservationRequest request) {
        ReservationResponse response = reservationService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<BookedReservationResponse>> getAllBookedReservations(@RequestParam int year,
                                                                                    @RequestParam int month) {
        List<BookedReservationResponse> response = reservationService.getAllBookedReservations(year, month);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponse> getReservation(@PathVariable("id") long id, @RequestParam String name,
                                                              @RequestParam String phoneNumber) {
        return ResponseEntity.ok().body(reservationService.getReservation(id, name, phoneNumber));
    }

    @PatchMapping
    public ResponseEntity<ReservationResponse> edit(@RequestBody EditReservationRequest request) {
        ReservationResponse response = reservationService.edit(request);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@RequestBody DeleteReservationRequest request) {
        reservationService.delete(request);
        return ResponseEntity.noContent().build();
    }
}

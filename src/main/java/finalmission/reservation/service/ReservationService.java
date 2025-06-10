package finalmission.reservation.service;

import finalmission.accommodation.domain.Accommodation;
import finalmission.accommodation.repository.AccommodationRepository;
import finalmission.dateprice.service.DatePriceService;
import finalmission.global.DataNotFoundException;
import finalmission.reservation.domain.CustomerInfo;
import finalmission.reservation.domain.Reservation;
import finalmission.reservation.dto.BookedReservationResponse;
import finalmission.reservation.dto.CreateReservationRequest;
import finalmission.reservation.dto.ReservationResponse;
import finalmission.reservation.repository.ReservationRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final AccommodationRepository accommodationRepository;
    private final DatePriceService datePriceService;

    public ReservationService(ReservationRepository reservationRepository,
                              AccommodationRepository accommodationRepository, DatePriceService datePriceService) {
        this.reservationRepository = reservationRepository;
        this.accommodationRepository = accommodationRepository;
        this.datePriceService = datePriceService;
    }

    public ReservationResponse create(CreateReservationRequest request) {
        Accommodation accommodation = accommodationRepository.findById(request.accommodationId())
                .orElseThrow(() -> new DataNotFoundException("존재하지 않는 숙소입니다."));

        CustomerInfo customerInfo = new CustomerInfo(request.name(), request.phoneNumber(), request.email());

        long totalPrice = datePriceService.calculateTotalPrice(request.startDate(), request.endDate());

        Reservation reservation = new Reservation(request.startDate(), request.endDate(),
                totalPrice, customerInfo, accommodation);
        return ReservationResponse.of(reservationRepository.save(reservation));
    }

    public List<BookedReservationResponse> getAllBookedReservations(int year, int month) {
        return reservationRepository.findAllInDateRange(year, month).stream()
                .map(BookedReservationResponse::of)
                .toList();
    }

    public ReservationResponse getReservation(long id, String name, String phoneNumber) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new DataNotFoundException("존재하지 않는 예약입니다."));

        reservation.validateCustomer(name, phoneNumber);

        return ReservationResponse.of(reservation);
    }
}

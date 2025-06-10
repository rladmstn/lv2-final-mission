package finalmission.reservation.service;

import finalmission.accommodation.domain.Accommodation;
import finalmission.accommodation.repository.AccommodationRepository;
import finalmission.dateprice.service.DatePriceService;
import finalmission.global.DataNotFoundException;
import finalmission.reservation.domain.CustomerInfo;
import finalmission.reservation.domain.Reservation;
import finalmission.reservation.dto.CreateReservationRequest;
import finalmission.reservation.dto.ReservationResponse;
import finalmission.reservation.repository.ReservationRepository;
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
}

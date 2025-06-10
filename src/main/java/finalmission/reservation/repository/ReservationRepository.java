package finalmission.reservation.repository;

import finalmission.reservation.domain.Reservation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Query("SELECT r FROM Reservation r "
            + "WHERE (YEAR(r.startDate) = :year "
            + "AND MONTH(r.startDate) = :month)"
            + "OR (YEAR(r.endDate) = :year "
            + "AND MONTH(r.endDate) = :month) ")
    List<Reservation> findAllInDateRange(Integer year, Integer month);
}

package finalmission.dateprice.repository;

import finalmission.dateprice.domain.DatePrice;
import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatePriceRepository extends JpaRepository<DatePrice, Long> {
    
    DatePrice findByDate(LocalDate date);
}

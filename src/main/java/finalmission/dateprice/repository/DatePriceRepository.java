package finalmission.dateprice.repository;

import finalmission.dateprice.domain.DatePrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatePriceRepository extends JpaRepository<DatePrice, Long> {
}

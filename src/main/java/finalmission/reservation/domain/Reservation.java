package finalmission.reservation.domain;

import finalmission.accommodation.domain.Accommodation;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate startDate;
    private LocalDate endDate;
    private long totalPrice;

    @Embedded
    private CustomerInfo customer;

    @ManyToOne(fetch = FetchType.LAZY)
    private Accommodation accommodation;

    public Reservation(long id, LocalDate startDate, LocalDate endDate, long totalPrice, CustomerInfo customer,
                       Accommodation accommodation) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalPrice = totalPrice;
        this.customer = customer;
        this.accommodation = accommodation;
    }

    protected Reservation() {
    }

    public long getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public CustomerInfo getCustomer() {
        return customer;
    }
}

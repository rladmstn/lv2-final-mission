package finalmission.reservation.domain;

import finalmission.accommodation.domain.Accommodation;
import finalmission.member.domain.Member;
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
    private String userName;
    private String phoneNumber;
    private long totalPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    private Accommodation accommodation;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;

    public Reservation(long id, LocalDate startDate, LocalDate endDate, String userName, String phoneNumber,
                       long totalPrice, Accommodation accommodation, Member member) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userName = userName;
        this.phoneNumber = phoneNumber;
        this.totalPrice = totalPrice;
        this.accommodation = accommodation;
        this.member = member;
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

    public String getUserName() {
        return userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }

    public Member getUser() {
        return member;
    }
}

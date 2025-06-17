package finalmission.accommodation.service;

import finalmission.accommodation.domain.Accommodation;
import finalmission.accommodation.dto.AccommodationResponse;
import finalmission.accommodation.dto.CreateAccommodationRequest;
import finalmission.accommodation.repository.AccommodationRepository;
import finalmission.member.domain.Member;
import org.springframework.stereotype.Service;

@Service
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;

    public AccommodationService(AccommodationRepository accommodationRepository) {
        this.accommodationRepository = accommodationRepository;
    }

    public AccommodationResponse create(Member admin, CreateAccommodationRequest request) {
        Accommodation accommodation = new Accommodation(
                request.name(),
                request.description(),
                request.address(),
                admin
        );
        return AccommodationResponse.of(accommodationRepository.save(accommodation));
    }
}

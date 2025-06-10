package finalmission.accommodation.service;

import finalmission.accommodation.dto.AccommodationResponse;
import finalmission.accommodation.dto.CreateAccommodationRequest;
import finalmission.accommodation.repository.AccommodationRepository;
import org.springframework.stereotype.Service;

@Service
public class AccommodationService {

    private final AccommodationRepository accommodationRepository;

    public AccommodationService(AccommodationRepository accommodationRepository) {
        this.accommodationRepository = accommodationRepository;
    }

    public AccommodationResponse create(CreateAccommodationRequest request) {
        return AccommodationResponse.of(accommodationRepository.save(request.toEntity()));
    }
}

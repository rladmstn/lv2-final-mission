package finalmission.accommodation.dto;

import finalmission.accommodation.domain.Accommodation;

public record CreateAccommodationRequest(String name,
                                         String description,
                                         String address) {
    public Accommodation toEntity() {
        return new Accommodation(name, description, address);
    }
}

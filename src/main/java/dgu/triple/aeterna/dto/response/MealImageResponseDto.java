package dgu.triple.aeterna.dto.response;

import dgu.triple.aeterna.domain.MealImage;

import java.time.LocalDateTime;

public record MealImageResponseDto(
        Long id,
        Long mealId,
        String imageUrl,
        MealImage.AnalysisStatus analysisStatus,
        LocalDateTime createdAt
) {
    public static MealImageResponseDto fromEntity(MealImage e) {
        return new MealImageResponseDto(
                e.getId(),
                e.getMeal().getId(),
                e.getImageUrl(),
                e.getAnalysisStatus(),
                e.getCreatedAt()
        );
    }
}


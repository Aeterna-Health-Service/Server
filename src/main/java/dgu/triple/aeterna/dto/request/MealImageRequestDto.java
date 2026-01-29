package dgu.triple.aeterna.dto.request;

import dgu.triple.aeterna.domain.MealImage;
import dgu.triple.aeterna.domain.MealLog;

public record MealImageRequestDto(
        String imageUrl,
        MealImage.AnalysisStatus analysisStatus
) {
    public MealImage toEntity(MealLog meal) {
        return MealImage.builder()
                .meal(meal)
                .imageUrl(imageUrl)
                .analysisStatus(analysisStatus)
                .build();
    }
}

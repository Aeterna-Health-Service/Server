package dgu.triple.aeterna.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Page;

import java.util.List;

@Schema(description = "페이지네이션 응답 DTO")
public record PageResponseDto<T>(

        @Schema(description = "조회된 데이터 목록")
        List<T> content,

        @Schema(description = "현재 페이지 번호 (0부터 시작)", example = "0")
        Integer pageNum,

        @Schema(description = "페이지 크기", example = "10")
        Integer size,

        @Schema(description = "전체 데이터 수", example = "125")
        Long totalElements,

        @Schema(description = "전체 페이지 수", example = "13")
        Integer totalPages,

        @Schema(description = "첫 페이지 여부", example = "true")
        Boolean first,

        @Schema(description = "마지막 페이지 여부", example = "false")
        Boolean last,

        @Schema(description = "다음 페이지 존재 여부", example = "true")
        Boolean hasNext,

        @Schema(description = "이전 페이지 존재 여부", example = "false")
        Boolean hasPrevious
) {
    public static <T> PageResponseDto<T> from(Page<T> page) {
        return new PageResponseDto<>(
                page.getContent(),
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast(),
                page.hasNext(),
                page.hasPrevious()
        );
    }
}

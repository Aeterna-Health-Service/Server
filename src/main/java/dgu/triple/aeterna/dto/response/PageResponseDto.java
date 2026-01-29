package dgu.triple.aeterna.dto.response;

import org.springframework.data.domain.Page;

import java.util.List;

public record PageResponseDto<T>(
        List<T> content,
        Integer pageNum,
        Integer size,
        Long totalElements,
        Integer totalPages,
        Boolean first,
        Boolean last,
        Boolean hasNext,
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

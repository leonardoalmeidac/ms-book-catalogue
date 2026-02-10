package org.unir.msbookcatalogue.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@Builder
public class BookResponse {

    private Long id;

    private String title;

    private String author;

    private LocalDate publicationDate;

    private String category;

    private String isbn;

    private Integer rating;

    private Boolean visible;
}

package org.unir.msbookcatalogue.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(
        name = "books",
        uniqueConstraints = { @UniqueConstraint(columnNames = "isbn")}
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    private LocalDate publicationDate;

    private String category;

    @Column(nullable = false, unique = true)
    private String isbn;

    @Column
    private Integer rating;

    @Column(nullable = false)
    private Boolean visible;
}

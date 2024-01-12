package com.train.aimforthehead.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "title", nullable = false)
    private String title;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false)
    private AuthorEntity author;
    @Column(name = "image", nullable = false)
    private String image;
    @Column(name = "price", nullable = false)
    private double price;
    @Column(name = "description")
    private String description;
}

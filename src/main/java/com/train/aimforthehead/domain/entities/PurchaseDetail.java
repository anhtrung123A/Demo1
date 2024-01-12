package com.train.aimforthehead.domain.entities;

import com.train.aimforthehead.domain.entities.BookEntity;
import com.train.aimforthehead.domain.entities.PurchaseDetailID;
import com.train.aimforthehead.domain.entities.PurchaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "purchase_details")
@IdClass(PurchaseDetailID.class)
public class PurchaseDetail {
    @Id
    @ManyToOne
    @JoinColumn(name = "book_id", referencedColumnName = "id")
    private BookEntity book;
    @Id
    @ManyToOne
    @JoinColumn(name = "purchase_id", referencedColumnName = "id")
    private PurchaseEntity purchase;
    @Column(name = "quantity", nullable = false)
    private int quantity;
}

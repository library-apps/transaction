package com.transaction.model;

import com.transaction.model.audit.AuditModel;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Set;

@Entity(name = "Transaction")
@Table(name = "transactions")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Transaction extends AuditModel<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = false, unique = true)
    private Long id;

    private Integer createdBy;
    private Integer readerId;
    private Date transaction_date;
    private Date borrow_start;
    private Date borrow_end;
    private Date returned_date;
    private String status;
    private String color;
    private Integer updatedBy;
    private Integer isDeleted;
    private Date deletedAt;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Collection<DetailTransaction> detailTransactions;
}

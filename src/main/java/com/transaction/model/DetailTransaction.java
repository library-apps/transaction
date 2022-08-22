package com.transaction.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.transaction.model.audit.AuditModel;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity(name = "DetailTransaction")
@Table(name = "detailtransactions")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DetailTransaction extends AuditModel<String> implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, insertable = false, unique = true)
    private Long id;


    private Long transaction_id;

    private Integer book_id;
    private Integer isDeleted;
    private Date deletedAt;
}

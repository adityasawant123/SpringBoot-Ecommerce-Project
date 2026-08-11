package com.ecommerce.project.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;

    @OneToOne(mappedBy = "payment",cascade = {CascadeType.PERSIST,CascadeType.MERGE})
    private Order order;


    @Size(min = 4, message = "Payment method must contain at least 4 characters")
    private String paymentMethod;

    private String pgPaymentId;//ID getting from the payment gateway
    private String pgStatus;
    private String pgResponseMessage;
    private String pgName;


    public Payment(Long paymentId, String pgPaymentId, String pgStatus, String pgResponseMessage,String pgName)
    {
        this.paymentId = paymentId;
        this.pgPaymentId = pgPaymentId;
        this.pgStatus = pgStatus;
        this.pgResponseMessage = pgResponseMessage;
        this.pgName = pgName;
    }

}

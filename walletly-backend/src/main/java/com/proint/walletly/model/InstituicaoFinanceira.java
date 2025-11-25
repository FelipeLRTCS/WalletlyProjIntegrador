package com.proint.walletly.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.envers.Audited;

@Entity
@Table(name = "instituicao_financeira", schema = "geral")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Audited
@Getter
@Setter
public class InstituicaoFinanceira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", unique = true, nullable = false, length = 100)
    private String nome;

    @Column(name = "logo_url", nullable = false)
    private String logoUrl;
}

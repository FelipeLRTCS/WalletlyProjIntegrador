package com.proint.walletly.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import com.proint.walletly.model.User;
import org.hibernate.envers.Audited;
import java.math.BigDecimal;

@Entity
@Table(name = "orcamento", schema = "geral", uniqueConstraints = {
        @UniqueConstraint(name = "uk_orcamento_user_category_month_year", columnNames = {"fk_usuario", "fk_categoria", "mes", "ano"})
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Audited
public class Orcamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_usuario", nullable = false)
    @NotNull(message = "O usuário é obrigatório")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_categoria", nullable = false)
    @NotNull(message = "A categoria é obrigatória")
    private Categoria categoria;

    @Column(name = "valor_maximo", nullable = false, precision = 15, scale = 2)
    @NotNull(message = "O valor máximo é obrigatório")
    @DecimalMin(value = "0.01", message = "O valor deve ser maior que zero")
    private BigDecimal valorMaximo;

    @Column(name = "mes", nullable = false)
    @NotNull(message = "O mês é obrigatório")
    private Integer mes;

    @Column(name = "ano", nullable = false)
    @NotNull(message = "O ano é obrigatório")
    private Integer ano;
}
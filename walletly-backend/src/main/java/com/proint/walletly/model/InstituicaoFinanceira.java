package java.com.proint.walletly.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "instituicao_financeira", schema = "geral")
public class InstituicaoFinanceira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", unique = true, nullable = false, length = 100)
    private String nome;

    @Column(name = "logo_url", nullable = false)
    private String logoUrl;

    public InstituicaoFinanceira(String nome, String logoUrl) {
        this.nome = nome;
        this.logoUrl = logoUrl;
    }

    public Long getId(){
        return id;
    }

    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public String getLogoUrl(){
        return this.logoUrl;
    }

    public void setLogoUrl(String logoUrl){
        this.logoUrl = logoUrl;
    }
}

package com.proint.walletly.mapper;

import com.proint.walletly.dto.transacao.TransacaoDTO;
import com.proint.walletly.model.Categoria;
import com.proint.walletly.model.Conta;
import com.proint.walletly.model.Transacao;
import com.proint.walletly.repository.CategoriaRepository;
import com.proint.walletly.repository.ContaRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class TransacaoMapper {

    @Autowired
    protected ContaRepository contaRepository;

    @Autowired
    protected CategoriaRepository categoriaRepository;

    @Mapping(target = "conta", expression = "java(getConta(dto.contaId()))")
    @Mapping(target = "categoria", expression = "java(getCategoria(dto.categoriaId()))")
    @Mapping(target = "id", ignore = true)
    public abstract Transacao toEntity(TransacaoDTO dto);

    @Mapping(target = "contaId", source = "conta.id")
    @Mapping(target = "categoriaId", source = "categoria.id")
    public abstract TransacaoDTO toDTO(Transacao entity);

    public abstract List<TransacaoDTO> toDTOList(List<Transacao> entities);

    @Mapping(target = "conta", expression = "java(getConta(dto.contaId()))")
    @Mapping(target = "categoria", expression = "java(getCategoria(dto.categoriaId()))")
    @Mapping(target = "id", ignore = true)
    public abstract void updateEntityFromDTO(TransacaoDTO dto, @MappingTarget Transacao entity);

    protected Conta getConta(Long contaId) {
        if (contaId == null) {
            return null;
        }
        return contaRepository.findById(contaId)
                .orElseThrow(() -> new RuntimeException("Conta não encontrada com o ID " + contaId));
    }

    protected Categoria getCategoria(Long categoriaId) {
        if (categoriaId == null) {
            return null;
        }
        return categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID " + categoriaId));
    }
}


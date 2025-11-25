package com.proint.walletly.mapper;

import com.proint.walletly.dto.orcamento.OrcamentoDTO;
import com.proint.walletly.model.Categoria;
import com.proint.walletly.model.Orcamento;
import com.proint.walletly.model.User;
import com.proint.walletly.repository.CategoriaRepository;
import com.proint.walletly.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OrcamentoMapper {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected CategoriaRepository categoriaRepository;

    @Mapping(target = "user", expression = "java(getUser(dto.usuarioId()))")
    @Mapping(target = "categoria", expression = "java(getCategoria(dto.categoriaId()))")
    @Mapping(target = "id", ignore = true)
    public abstract Orcamento toEntity(OrcamentoDTO dto);

    @Mapping(target = "usuarioId", source = "user.id")
    @Mapping(target = "categoriaId", source = "categoria.id")
    public abstract OrcamentoDTO toDTO(Orcamento entity);

    public abstract List<OrcamentoDTO> toDTOList(List<Orcamento> entities);

    @Mapping(target = "user", expression = "java(getUser(dto.usuarioId()))")
    @Mapping(target = "categoria", expression = "java(getCategoria(dto.categoriaId()))")
    @Mapping(target = "id", ignore = true)
    public abstract void updateEntityFromDTO(OrcamentoDTO dto, @MappingTarget Orcamento entity);

    protected User getUser(Long usuarioId) {
        if (usuarioId == null) {
            return null;
        }
        return userRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID " + usuarioId));
    }

    protected Categoria getCategoria(Long categoriaId) {
        if (categoriaId == null) {
            return null;
        }
        return categoriaRepository.findById(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoria não encontrada com o ID " + categoriaId));
    }
}


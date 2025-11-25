package com.proint.walletly.mapper;

import com.proint.walletly.dto.categoria.CategoriaDTO;
import com.proint.walletly.model.Categoria;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    Categoria toEntity(CategoriaDTO dto);

    CategoriaDTO toDTO(Categoria entity);

    List<CategoriaDTO> toDTOList(List<Categoria> entities);

    void updateEntityFromDTO(CategoriaDTO dto, @MappingTarget Categoria entity);
}


package com.proint.walletly.mapper;

import com.proint.walletly.dto.instituicao.InstituicaoDTO;
import com.proint.walletly.model.InstituicaoFinanceira;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InstituicaoFinanceiraMapper {

    InstituicaoFinanceira toEntity(InstituicaoDTO dto);

    InstituicaoDTO toDTO(InstituicaoFinanceira entity);

    List<InstituicaoDTO> toDTOList(List<InstituicaoFinanceira> entities);

    void updateEntityFromDTO(InstituicaoDTO dto, @MappingTarget InstituicaoFinanceira entity);
}


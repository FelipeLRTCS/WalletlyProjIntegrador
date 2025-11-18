package com.proint.walletly.mapper;

import com.proint.walletly.dto.conta.ContaDTO;
import com.proint.walletly.model.Conta;
import com.proint.walletly.model.InstituicaoFinanceira;
import com.proint.walletly.model.User;
import com.proint.walletly.repository.InstituicaoFinanceiraRepository;
import com.proint.walletly.repository.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class ContaMapper {

    @Autowired
    protected UserRepository userRepository;

    @Autowired
    protected InstituicaoFinanceiraRepository instituicaoFinanceiraRepository;

    @Mapping(target = "usuario", expression = "java(getUser(dto.usuarioId()))")
    @Mapping(target = "instituicao", expression = "java(getInstituicao(dto.instituicaoId()))")
    @Mapping(target = "id", ignore = true)
    public abstract Conta toEntity(ContaDTO dto);

    @Mapping(target = "usuarioId", source = "usuario.id")
    @Mapping(target = "instituicaoId", source = "instituicao.id")
    public abstract ContaDTO toDTO(Conta entity);

    public abstract List<ContaDTO> toDTOList(List<Conta> entities);

    @Mapping(target = "usuario", expression = "java(getUser(dto.usuarioId()))")
    @Mapping(target = "instituicao", expression = "java(getInstituicao(dto.instituicaoId()))")
    @Mapping(target = "id", ignore = true)
    public abstract void updateEntityFromDTO(ContaDTO dto, @MappingTarget Conta entity);

    protected User getUser(Long usuarioId) {
        if (usuarioId == null) {
            return null;
        }
        return userRepository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado com o ID " + usuarioId));
    }

    protected InstituicaoFinanceira getInstituicao(Long instituicaoId) {
        if (instituicaoId == null) {
            return null;
        }
        return instituicaoFinanceiraRepository.findById(instituicaoId)
                .orElseThrow(() -> new RuntimeException("Instituição financeira não encontrada com o ID " + instituicaoId));
    }
}


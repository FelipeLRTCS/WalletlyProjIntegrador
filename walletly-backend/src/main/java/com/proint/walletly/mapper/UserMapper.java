package com.proint.walletly.mapper;

import org.mapstruct.Mapper;

import com.proint.walletly.dto.UserDTO;
import com.proint.walletly.dto.shallow.UserShallowDTO;
import com.proint.walletly.model.User;
import com.proint.walletly.utils.SignupRequest;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    public abstract User toEntity(UserDTO userDTO);
    public abstract User toEntity(UserShallowDTO userShallowDTO);

    public abstract UserDTO toDTO(User user);
    public abstract UserDTO toDTO(UserShallowDTO userShallowDTO);

    public abstract UserShallowDTO toShallowDTO(UserDTO userDTO);
    public abstract UserShallowDTO toShallowDTO(User user);

    public abstract User toUser(SignupRequest signupRequest);
    
}

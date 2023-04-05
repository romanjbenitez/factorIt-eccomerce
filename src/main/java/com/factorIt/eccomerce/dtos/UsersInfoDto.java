package com.factorIt.eccomerce.dtos;

import com.factorIt.eccomerce.models.Role;
import com.factorIt.eccomerce.models.Users;

public class UsersInfoDto {

    private String name;
    private Role role;

    public UsersInfoDto() {
    }

    public UsersInfoDto(Users users) {
        this.name = users.getFirsName() +" " +users.getLastName();
        this.role = users.getRole();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}

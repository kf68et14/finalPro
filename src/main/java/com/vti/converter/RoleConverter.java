package com.vti.converter;

import com.vti.entity.Role;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class RoleConverter implements AttributeConverter<Role, String> {
    @Override
    public String convertToDatabaseColumn(Role roleAttribute) {
        return roleAttribute.getValue();
    }

    @Override
    public Role convertToEntityAttribute(String dbData) {
        return Role.of(dbData);
    }
}

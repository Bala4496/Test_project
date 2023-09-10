package com.example.test.mapper;

import com.example.test.dto.SubscriptionDTO;
import com.example.test.model.Subscription;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SubscriptionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Subscription map(SubscriptionDTO subscriptionDTO);

    @InheritInverseConfiguration
    SubscriptionDTO map(Subscription subscription);
}

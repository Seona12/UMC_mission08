package com.example.demo.converter;

import com.example.demo.domain.Region;
import com.example.demo.domain.Store;
import com.example.demo.web.dto.AddStoreRequest;

public class StoreConverter {
    public static Store toEntity(AddStoreRequest req, Region region) {
        return Store.builder()
                .name(req.getName())
                .address(req.getAddress())
                // score 초기값이 필요하다면 0.0f 같이 지정
                .region(region)
                .build();
    }
}

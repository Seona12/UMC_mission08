package com.example.demo.repository.StoreRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.demo.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long>, StoreRepositoryCustom {
}
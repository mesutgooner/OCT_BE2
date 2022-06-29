package com.globits.da.repository;

import com.globits.da.domain.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WardRepository extends JpaRepository<Ward,Long> {
    Ward getWardById(Long id);
}

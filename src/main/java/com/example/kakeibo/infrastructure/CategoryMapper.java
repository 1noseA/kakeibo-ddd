package com.example.kakeibo.infrastructure;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CategoryMapper {

    Optional<CategoryRecord> findById(@Param("id") UUID id);

    List<CategoryRecord> findExpenseCategories();
}

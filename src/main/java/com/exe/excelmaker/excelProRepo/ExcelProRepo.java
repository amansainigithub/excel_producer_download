package com.exe.excelmaker.excelProRepo;

import com.exe.excelmaker.entities.ExcelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExcelProRepo extends JpaRepository<ExcelEntity, Long> {
}

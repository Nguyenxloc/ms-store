package com.example.java4.repositories;
import com.example.java4.entities.HDCT;
import com.example.java4.entities.MauSac;
import com.example.java4.entities.SanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Repository
public interface SanPhamRepository
        extends JpaRepository<SanPham,Integer>
{
    public static final int ACTIVE  = 1;
    public static final int INACTIVE =0;
    public Page<SanPham> findByTrangThai(int trangThai, Pageable pageable);


    // Query để lấy ra 3 sản phẩm duy nhất (distinct) từ cơ sở dữ liệu
    @Query("SELECT DISTINCT sp FROM SanPham sp ")
    List<SanPham> getDistinctTopBy3(Pageable pageable);
};

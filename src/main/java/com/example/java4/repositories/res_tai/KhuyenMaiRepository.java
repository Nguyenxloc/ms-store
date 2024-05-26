package com.example.java4.repositories.res_tai;

import com.example.java4.entities.HoaDon;
import com.example.java4.entities.ett_tai.KhuyenMai;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface KhuyenMaiRepository extends JpaRepository<KhuyenMai, UUID> {

    public static final int ACTIVE  = 1;
    public static final int INACTIVE =0;
    public Page<KhuyenMai> findByTrangThai(int trangThai, Pageable pageable);
    @Query(value = "SELECT TOP 5 * FROM KhuyenMai where trangThai = 0 ORDER BY ID DESC ",
            nativeQuery = true)
    public List<KhuyenMai> selectTop5();
    public Optional<KhuyenMai> findById(UUID id);

    List<KhuyenMai> findAll();
    Page<KhuyenMai> findAll(Pageable pageable);

}

package com.example.java4.repositories.repo_tai;

import com.example.java4.entities.DiaChi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IDiaChiRepository extends JpaRepository<DiaChi,String> {

//    @Query("SELECT diaChi FROM DiaChi diaChi WHERE diaChi.idKhachHang.id = :idKH")
//    Optional<DiaChi> findUniqueDiaChiByKhachHangId(@Param("idKH") String idKH);

    @Query("SELECT diaChi FROM DiaChi diaChi WHERE diaChi.idKhachHang.id = :idKH")
    List<DiaChi> findDiaChiByKhachHangId(@Param("idKH") String idKH);


}

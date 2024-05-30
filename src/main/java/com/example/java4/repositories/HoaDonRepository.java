<<<<<<< HEAD
//package com.example.java4.repositories;
//
//import org.springframework.data.domain.Page;
//import org.springframework.data.domain.Pageable;
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//import org.springframework.stereotype.Repository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Repository
//public interface HoaDonRepository
//        extends JpaRepository<HoaDon,Integer>
//{
//    public static final int ACTIVE  = 1;
//    public static final int INACTIVE =0;
//    public Page<HoaDon> findByTrangThai(int trangThai, Pageable pageable);
//    @Query(value = "SELECT TOP 5 * FROM HoaDon where trangThai = 0 ORDER BY ID DESC ",
//            nativeQuery = true)
//    public List<HoaDon>  selectTop5();
//    public Optional<HoaDon> findById(Integer id);
//};
=======
package com.example.java4.repositories;

import com.example.java4.entities.HoaDon;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HoaDonRepository
        extends JpaRepository<HoaDon,Integer>
{
    public static final int ACTIVE  = 1;
    public static final int INACTIVE =0;
    public Page<HoaDon> findByTrangThai(int trangThai, Pageable pageable);
    @Query(value = "SELECT TOP 5 * FROM HoaDon where trangThai = 0 ORDER BY ID DESC ",
            nativeQuery = true)
    public List<HoaDon>  selectTop5();
    public Optional<HoaDon> findById(String id);
};
>>>>>>> acd7cf62bc26f7efb5b1ad954516bae5a7efecb8

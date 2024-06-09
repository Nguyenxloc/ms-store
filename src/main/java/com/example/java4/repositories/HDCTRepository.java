package com.example.java4.repositories;

import com.example.java4.entities.ChiTietHoaDon;
import com.example.java4.entities.ChiTietSanPham;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.java4.response.GioHangResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Repository
public interface HDCTRepository
        extends JpaRepository<ChiTietHoaDon,String>
{
//        public static final int ACTIVE = 1;
//        public static final int INACTIVE = 0;
//        public Page<ChiTietHoaDon> findByTrangThai(int trangThai, Pageable pageable);
//        public List<ChiTietHoaDon> findAllByHoaDon_Id(String id);
//        @Query ("SELECT h FROM ChiTietHoaDon h WHERE h.idHoaDon.id = :hoaDon AND h.idCTSP = :sanPhamChiTiet")
//        ChiTietHoaDon findByHoaDonAndIdSanPhamChiTiet(@PathVariable ("hoaDon") String hoaDon, @PathVariable ("sanPhamChiTiet") ChiTietSanPham sanPhamChiTiet);
//

    @Query("select cthd from ChiTietHoaDon cthd where cthd.idHoaDon.id = ?1")
    List<ChiTietHoaDon> findByIdHoaDon(String idHD);

    @Query("select cthd from ChiTietHoaDon cthd where cthd.idCTSP.id = ?1")
    ChiTietHoaDon deleteByIdSPCT(String id);

    Optional<ChiTietHoaDon> findById(String id);

    @Query("select cthd from ChiTietHoaDon cthd\n" +
            "join HoaDon hd on hd.id = cthd.idHoaDon.id\n" +
            "where hd.idKhachHang.id = ?1")
    List<ChiTietHoaDon> findByIdHoaDonByIDKH(String idKH);


    @Query("select new com.example.java4.response.GioHangResponse(cthd.id, ctsp.idSanPham.ten, ha.hinhAnh1, ctsp.idMauSac.ten, ctsp.idKichThuoc.ten, cthd.soLuong, cthd.donGia) " +
            "from ChiTietHoaDon cthd " +
            "join ChiTietSanPham ctsp on ctsp.id = cthd.idCTSP.id " +
            "join HinhAnh ha on ha.idCTSP.id = ctsp.id " +
            "join HoaDon hd on hd.id = cthd.idHoaDon.id " +
            "where hd.idKhachHang.id = ?1")
    List<GioHangResponse> getAll(String idKH);

};

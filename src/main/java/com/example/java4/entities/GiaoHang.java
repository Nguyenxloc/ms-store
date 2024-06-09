package com.example.java4.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.sql.Date;
import java.util.Objects;

@Entity
public class GiaoHang {
    private Object id;
    private String tenNguoiNhan;
    private String sdtNguoiNhan;
    private String diaChiChiTiet;
    private String idPhuongXa;
    private String idQuanHuyen;
    private String idTinhThanh;
    private Date ngayShip;
    private Date ngayNhan;
    private Integer phiShip;
    private Integer trangThai;
    private String ghiChu;

    @Id
    @Column(name = "ID")
    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TenNguoiNhan")
    public String getTenNguoiNhan() {
        return tenNguoiNhan;
    }

    public void setTenNguoiNhan(String tenNguoiNhan) {
        this.tenNguoiNhan = tenNguoiNhan;
    }

    @Basic
    @Column(name = "SdtNguoiNhan")
    public String getSdtNguoiNhan() {
        return sdtNguoiNhan;
    }

    public void setSdtNguoiNhan(String sdtNguoiNhan) {
        this.sdtNguoiNhan = sdtNguoiNhan;
    }

    @Basic
    @Column(name = "DiaChiChiTiet")
    public String getDiaChiChiTiet() {
        return diaChiChiTiet;
    }

    public void setDiaChiChiTiet(String diaChiChiTiet) {
        this.diaChiChiTiet = diaChiChiTiet;
    }

    @Basic
    @Column(name = "IdPhuongXa")
    public String getIdPhuongXa() {
        return idPhuongXa;
    }

    public void setIdPhuongXa(String idPhuongXa) {
        this.idPhuongXa = idPhuongXa;
    }

    @Basic
    @Column(name = "IdQuanHuyen")
    public String getIdQuanHuyen() {
        return idQuanHuyen;
    }

    public void setIdQuanHuyen(String idQuanHuyen) {
        this.idQuanHuyen = idQuanHuyen;
    }

    @Basic
    @Column(name = "IdTinhThanh")
    public String getIdTinhThanh() {
        return idTinhThanh;
    }

    public void setIdTinhThanh(String idTinhThanh) {
        this.idTinhThanh = idTinhThanh;
    }

    @Basic
    @Column(name = "NgayShip")
    public Date getNgayShip() {
        return ngayShip;
    }

    public void setNgayShip(Date ngayShip) {
        this.ngayShip = ngayShip;
    }

    @Basic
    @Column(name = "NgayNhan")
    public Date getNgayNhan() {
        return ngayNhan;
    }

    public void setNgayNhan(Date ngayNhan) {
        this.ngayNhan = ngayNhan;
    }

    @Basic
    @Column(name = "PhiShip")
    public Integer getPhiShip() {
        return phiShip;
    }

    public void setPhiShip(Integer phiShip) {
        this.phiShip = phiShip;
    }

    @Basic
    @Column(name = "TrangThai")
    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    @Basic
    @Column(name = "GhiChu")
    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GiaoHang giaoHang = (GiaoHang) o;
        return Objects.equals(id, giaoHang.id) && Objects.equals(tenNguoiNhan, giaoHang.tenNguoiNhan) && Objects.equals(sdtNguoiNhan, giaoHang.sdtNguoiNhan) && Objects.equals(diaChiChiTiet, giaoHang.diaChiChiTiet) && Objects.equals(idPhuongXa, giaoHang.idPhuongXa) && Objects.equals(idQuanHuyen, giaoHang.idQuanHuyen) && Objects.equals(idTinhThanh, giaoHang.idTinhThanh) && Objects.equals(ngayShip, giaoHang.ngayShip) && Objects.equals(ngayNhan, giaoHang.ngayNhan) && Objects.equals(phiShip, giaoHang.phiShip) && Objects.equals(trangThai, giaoHang.trangThai) && Objects.equals(ghiChu, giaoHang.ghiChu);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tenNguoiNhan, sdtNguoiNhan, diaChiChiTiet, idPhuongXa, idQuanHuyen, idTinhThanh, ngayShip, ngayNhan, phiShip, trangThai, ghiChu);
    }
}

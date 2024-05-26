package com.example.java4.entities.ett_tai;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "KhuyenMai", schema = "dbo", catalog = "ms-store")
public class KhuyenMai {
    private UUID id;
    private String ten;
    private String ma;
    private Integer soLuong;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private Date ngayCapNhat;
    private Integer giaTriGiam;
    private Integer indx;
    private Integer trangThai;

    @Id
    @Column(name = "Id", nullable = false)
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Ten", nullable = true, length = 50)
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @Basic
    @Column(name = "Ma", nullable = true, length = 17)
    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    @Basic
    @Column(name = "SoLuong")
    public Integer getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(Integer soLuong) {
        this.soLuong = soLuong;
    }


    @Basic
    @Column(name = "NgayBatDau", nullable = true)
    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    @Basic
    @Column(name = "NgayKetThuc", nullable = true)
    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }


    @Basic
    @Column(name = "NgayCapNhat", nullable = true)
    public Date getNgayCapNhat() {
        return ngayCapNhat;
    }

    public void setNgayCapNhat(Date ngayCapNhat) {
        this.ngayCapNhat = ngayCapNhat;
    }

    @Basic
    @Column(name = "GiaTriGiam", nullable = true, precision = 0)
    public Integer getGiaTriGiam() {
        return giaTriGiam;
    }

    public void setGiaTriGiam(Integer giaTriGiam) {
        this.giaTriGiam = giaTriGiam;
    }

    @Basic
    @Column(name = "indx", nullable = false)
    public int getIndx() {
        return indx;
    }

    public void setIndx(int indx) {
        this.indx = indx;
    }

    @Basic
    @Column(name = "trangThai", nullable = false)
    public int getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(int trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KhuyenMai that = (KhuyenMai) o;
        return indx == that.indx && Objects.equals(id, that.id) && Objects.equals(ten, that.ten) && Objects.equals(ma, that.ma) && Objects.equals(ngayBatDau, that.ngayBatDau) && Objects.equals(ngayKetThuc, that.ngayKetThuc) && Objects.equals(giaTriGiam, that.giaTriGiam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ten, ma, ngayBatDau, ngayKetThuc, giaTriGiam, indx);
    }
}

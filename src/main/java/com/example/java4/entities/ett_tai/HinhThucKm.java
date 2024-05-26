package com.example.java4.entities.ett_tai;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "HinhThucKM", schema = "dbo", catalog = "ms-store")
public class HinhThucKm {
    private Object id;
    private String loaiPhuongThuc;
    private Integer heSo;
    private Integer trangThai;
    private Date ngayTao;

    @Id
    @Column(name = "Id", nullable = false)
    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    @Basic
    @Column(name = "LoaiPhuongThuc", nullable = true, length = 50)
    public String getLoaiPhuongThuc() {
        return loaiPhuongThuc;
    }

    public void setLoaiPhuongThuc(String loaiPhuongThuc) {
        this.loaiPhuongThuc = loaiPhuongThuc;
    }

    @Basic
    @Column(name = "HeSo", nullable = true)
    public Integer getHeSo() {
        return heSo;
    }

    public void setHeSo(Integer heSo) {
        this.heSo = heSo;
    }

    @Basic
    @Column(name = "TrangThai", nullable = true)
    public Integer getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(Integer trangThai) {
        this.trangThai = trangThai;
    }

    @Basic
    @Column(name = "NgayTao", nullable = true)
    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HinhThucKm that = (HinhThucKm) o;
        return Objects.equals(id, that.id) && Objects.equals(loaiPhuongThuc, that.loaiPhuongThuc) && Objects.equals(heSo, that.heSo) && Objects.equals(trangThai, that.trangThai) && Objects.equals(ngayTao, that.ngayTao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, loaiPhuongThuc, heSo, trangThai, ngayTao);
    }
}

package com.example.java4.config;

import com.example.java4.service.KhuyenMaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class KhuyenMaiScheduler {

    @Autowired
    private KhuyenMaiService khuyenMaiService;

    @Scheduled(cron = "0 0 0 * * *")
//    @Scheduled(fixedRate = 5000)
    // Chạy vào lúc 12 giờ đêm hàng ngày
    public void runTask() {
        khuyenMaiService.updateKhuyenMaiStatus();
    }
}

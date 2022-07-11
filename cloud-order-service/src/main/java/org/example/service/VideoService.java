package org.example.service;

import domain.Video;
import org.example.service.fallback.VideoServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cloud-video-service", fallback = VideoServiceFallback.class)
public interface VideoService {

    @GetMapping(value = "/api/v1/video/find_by_id")
    Video findById(@RequestParam("videoId") int videoId);

    @PostMapping("/api/v1/video/save")
    int save(Video video);
}

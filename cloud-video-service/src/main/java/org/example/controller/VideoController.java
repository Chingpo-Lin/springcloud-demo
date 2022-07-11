package org.example.controller;

import domain.Video;
import domain.VideoOrder;
import org.example.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@RestController
@RequestMapping("api/v1/video")
public class VideoController {

    @Autowired
    private VideoService videoService;

    @RequestMapping("find_by_id")
    public Object findById(int videoId, HttpServletRequest request) {
        Video video = videoService.findById(videoId);
        // know which machine send the req
        video.setServerInfo(request.getServerName() + ":" + request.getServerPort());
        return video;
    }

    @PostMapping("save")
    public int save(@RequestBody Video video) {
        System.out.println(video.getTitle());
        return 1;
    }
}

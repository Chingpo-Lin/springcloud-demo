package org.example.controller;

import domain.Video;
import domain.VideoOrder;
import org.example.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/v1/video_order")
public class OrderController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private VideoService videoService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("save")
    public Object save(int videoId) {
//        Video video = restTemplate.getForObject("http://localhost:9000/api/v1/video/find_by_id?videoId="
//                        + videoId, Video.class);

//        List<ServiceInstance> list = discoveryClient.getInstances("cloud-video-service");
//        // manually choice a service instance
//        ServiceInstance serviceInstance = list.get(0);
//
//        Video video = restTemplate.getForObject("http://" + serviceInstance.getHost() + ":"
//                        + serviceInstance.getPort() + "/api/v1/video/find_by_id?videoId="
//                        + videoId, Video.class);

        // ribbon usage
//        Video video = restTemplate.getForObject("http://cloud-video-service/api/v1/video/find_by_id?videoId="
//                + videoId, Video.class);

        Video video = videoService.findById(videoId);

        VideoOrder videoOrder = new VideoOrder();
        videoOrder.setVideoId(video.getId());
        videoOrder.setVideoTitle(video.getTitle());
        videoOrder.setCreateTime(new Date());

        videoOrder.setServerInfo(video.getServerInfo());

        return videoOrder;
    }

//    /**
//     * test post request of using feign
//     * @param video
//     * @return
//     */
//    @RequestMapping("save")
//    public Object save(@RequestBody Video video) {
//        int rows = videoService.save(video);
//        Map<String, Object> map =new HashMap<>();
//        map.put("rows", rows);
//        return map;
//    }

    int temp = 0;

    @GetMapping("list")
    public Object list(HttpServletRequest request) {
//        try {
//            TimeUnit.SECONDS.sleep(3);
//        } catch(Exception e) {
//            e.printStackTrace();
//        }

        temp++;

        if (temp % 3 == 0) {
            throw new RuntimeException();
        }

        Map<String, Object> map =new HashMap<>();
        map.put("title1", "hihi");
        map.put("title2", "hihi2");
        map.put("port", "" + request.getServerPort());
        return map;
    }
}

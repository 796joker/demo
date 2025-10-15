package com.example.demo.app;


import com.example.demo.app.entity.AppEntity;
import com.example.demo.app.mapper.AppMapper;
import com.example.demo.app.vo.AppVO;
import com.example.demo.common.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * * AppController
 * 正式的业务控制器
 */
@RestController
@RequestMapping("/api/apps")
public class AppController {
    private final AppMapper appMapper;

    public AppController(AppMapper appMapper) {
        this.appMapper = appMapper;
    }

    /**
     * 将数据库数据转为VO返回
     * @return ApiResponse<List<AppVO>>
     */
    private static List<AppVO> convertToVOList(List<AppEntity> entities) {
        List<AppVO> apps = new ArrayList<>();
        for (AppEntity e : entities) {
            AppVO vo = AppVO.builder()
                    .id(e.getId())
                    .name(e.getName())
                    .description(e.getDescription())
                    .fullDescription(e.getFullDescription())
                    .avatar(e.getAvatar())
                    .category(e.getCategory())
                    .price(e.getPrice())
                    .rating(e.getRating())
                    .downloads(e.getDownloads())
                    .reviews(e.getReviews())
                    .author(e.getAuthor())
                    .publishedAt(e.getPublishedAt())
                    .build();
            apps.add(vo);
        }
        return apps;
    }

    @GetMapping
    public ApiResponse<List<AppVO>> list() {
        // 使用MyBatis从数据库获取数据
        List<AppEntity> entities = appMapper.findAllOrderById();

        // 将实体转换为VO
        List<AppVO> apps = convertToVOList(entities);

        return ApiResponse.ok(apps);
    }

    // 新增：根据分类查询应用
    @GetMapping("/category/{category}")
    public ApiResponse<List<AppVO>> listByCategory(@PathVariable String category) {
        List<AppEntity> entities = appMapper.findByCategory(category);
        List<AppVO> apps = convertToVOList(entities);
        return ApiResponse.ok(apps);
    }

    // hw1:根据评分排序
    @GetMapping("/top-rated")
    public ApiResponse<List<AppVO>> listTopRated() {
        List<AppEntity> entities = appMapper.findAllOrderByRating();
        List<AppVO> apps = convertToVOList(entities);
        return ApiResponse.ok(apps);
    }

    // hw1:根据下载量排序
    @GetMapping("/most-downloaded")
    public ApiResponse<List<AppVO>> listMostDownloaded() {
        List<AppEntity> entities = appMapper.findAllOrderByDownloads();
        List<AppVO> apps = convertToVOList(entities);
        return ApiResponse.ok(apps);
    }
}

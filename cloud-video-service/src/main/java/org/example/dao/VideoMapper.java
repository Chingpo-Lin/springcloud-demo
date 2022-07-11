package org.example.dao;

import domain.Video;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface VideoMapper {

    @Select("select * from video where id = #{videoId}")
    Video findById(@Param("videoId") int videoId);
}

package com.example.minproj2_mybatis.reply.mapper;

import com.example.minproj2_mybatis.reply.entity.ReplyEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ReplyMapper {

    int save(ReplyEntity reply);

    List<ReplyEntity> findAll(Long boardId);
}

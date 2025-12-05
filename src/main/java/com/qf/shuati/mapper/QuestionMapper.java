package com.qf.shuati.mapper;

import com.qf.shuati.domain.entity.Question;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 20688
* @description 针对表【question(题目)】的数据库操作Mapper
* @createDate 2025-11-23 16:30:47
* @Entity com.qf.shuati.domain.entity.Question
*/
@Mapper
public interface QuestionMapper extends BaseMapper<Question> {

}





package com.qf.shuati.mapper;

import com.qf.shuati.domain.entity.QuestionBank;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 20688
* @description 针对表【question_bank(题库)】的数据库操作Mapper
* @createDate 2025-11-22 21:50:06
* @Entity com.qf.shuati.domain.entity.QuestionBank
*/
@Mapper
public interface QuestionBankMapper extends BaseMapper<QuestionBank> {

}





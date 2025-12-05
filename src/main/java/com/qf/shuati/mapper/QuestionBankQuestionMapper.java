package com.qf.shuati.mapper;

import com.qf.shuati.domain.entity.QuestionBankQuestion;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 20688
* @description 针对表【question_bank_question(题库题目)】的数据库操作Mapper
* @createDate 2025-11-24 14:52:58
* @Entity com.qf.shuati.domain.entity.QuestionBankQuestion
*/
@Mapper
public interface QuestionBankQuestionMapper extends BaseMapper<QuestionBankQuestion> {

}





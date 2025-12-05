package com.qf.shuati.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.shuati.domain.entity.Question;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.shuati.domain.dto.question.QuestionQueryRequest;
import com.qf.shuati.domain.vo.QuestionVO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
* @author 20688
* @description 针对表【question(题目)】的数据库操作Service
* @createDate 2025-11-23 16:30:47
*/
public interface QuestionService extends IService<Question> {

    Page<Question> listQuestionByPage(QuestionQueryRequest questionQueryRequest);

    Page<QuestionVO> getQuestionVOPage(Page<Question> questionPage, HttpServletRequest request);

    QuestionVO getQuestionVO(Question question, HttpServletRequest request);

    void validQuestion(Question question, boolean b);

    Wrapper<Question> getQueryWrapper(QuestionQueryRequest questionQueryRequest);

    void batchDeleteQuestions(List<Long> questionIdList);
}

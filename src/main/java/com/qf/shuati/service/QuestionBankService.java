package com.qf.shuati.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.shuati.domain.entity.QuestionBank;
import com.qf.shuati.domain.dto.questionBank.QuestionBankQueryRequest;
import com.qf.shuati.domain.vo.QuestionBankVO;
import jakarta.servlet.http.HttpServletRequest;

/**
* @author 20688
* @description 针对表【question_bank(题库)】的数据库操作Service
* @createDate 2025-11-22 21:50:06
*/
public interface QuestionBankService extends IService<QuestionBank> {

    QuestionBankVO getQuestionBankVO(QuestionBank questionBank, HttpServletRequest request);

    QueryWrapper<QuestionBank> getQueryWrapper(QuestionBankQueryRequest questionBankQueryRequest);

    Page<QuestionBankVO> getQuestionBankVOPage(Page<QuestionBank> questionBankPage, HttpServletRequest request);

    void validQuestionBank(QuestionBank questionBank, boolean b);
}

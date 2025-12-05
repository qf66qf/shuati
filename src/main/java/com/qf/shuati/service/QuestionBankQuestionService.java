package com.qf.shuati.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qf.shuati.domain.dto.questionbankquestion.QuestionBankQuestionQueryRequest;
import com.qf.shuati.domain.entity.QuestionBankQuestion;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.shuati.domain.entity.User;
import com.qf.shuati.domain.vo.QuestionBankQuestionVO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* @author 20688
* @description 针对表【question_bank_question(题库题目)】的数据库操作Service
* @createDate 2025-11-24 14:52:58
*/
public interface QuestionBankQuestionService extends IService<QuestionBankQuestion> {

    void validQuestionBankQuestion(QuestionBankQuestion questionBankQuestion, boolean b);

    QuestionBankQuestionVO getQuestionBankQuestionVO(QuestionBankQuestion questionBankQuestion, HttpServletRequest request);

    Wrapper<QuestionBankQuestion> getQueryWrapper(QuestionBankQuestionQueryRequest questionBankQuestionQueryRequest);

    Page<QuestionBankQuestionVO> getQuestionBankQuestionVOPage(Page<QuestionBankQuestion> questionBankQuestionPage, HttpServletRequest request);

    void batchAddQuestionsToBank(List<Long> questionIdList, Long questionBankId, User loginUser);

    /**
     * 批量添加题目到题库(事物,仅限内部调用)
     * @param questionBankQuestions
     */
    @Transactional(rollbackFor = Exception.class)
    void batchAddQuestionsToBankInner(List<QuestionBankQuestion> questionBankQuestions);

    /**
     * 批量从题库移除题目
     * @param questionIdList
     * @param questionBankId
     */
    void batchRemoveQuestionsFromBank(List<Long> questionIdList, long questionBankId);
}

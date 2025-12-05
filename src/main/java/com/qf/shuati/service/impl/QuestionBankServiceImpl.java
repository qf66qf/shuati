package com.qf.shuati.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.qf.shuati.common.ErrorCode;
import com.qf.shuati.domain.entity.QuestionBank;
import com.qf.shuati.domain.entity.User;
import com.qf.shuati.domain.dto.questionBank.QuestionBankQueryRequest;
import com.qf.shuati.domain.vo.QuestionBankVO;
import com.qf.shuati.domain.vo.UserVO;
import com.qf.shuati.exception.ThrowUtils;
import com.qf.shuati.service.QuestionBankService;
import com.qf.shuati.mapper.QuestionBankMapper;
import com.qf.shuati.service.UserService;
import com.qf.shuati.utils.SqlUtils;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import cn.hutool.core.util.ObjectUtil;        // ✔ 有 isNotEmpty
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
* @author 20688
* @description 针对表【question_bank(题库)】的数据库操作Service实现
* @createDate 2025-11-22 21:50:06
*/
@Service
public class QuestionBankServiceImpl extends ServiceImpl<QuestionBankMapper, QuestionBank>
    implements QuestionBankService{

    @Resource
    private UserService userService;

    @Override
    public QuestionBankVO getQuestionBankVO(QuestionBank questionBank, HttpServletRequest request) {
        //对象转成封装类
        QuestionBankVO questionBankVO = QuestionBankVO.objToVo(questionBank);
        //1.关联查询用户信息
        Long userId = questionBank.getUserId();
        User user = null;
        if(userId != null && userId > 0) {
            user = userService.getById(userId);
        }
        UserVO userVO = userService.getUserVO(user);
        questionBankVO.setUser(userVO);
        return questionBankVO;
    }

    @Override
    public QueryWrapper<QuestionBank> getQueryWrapper(QuestionBankQueryRequest questionBankQueryRequest) {
        QueryWrapper<QuestionBank> queryWrapper = new QueryWrapper<>();
        if(questionBankQueryRequest == null) {
            return queryWrapper;
        }
        //从对象中取值
        Long id = questionBankQueryRequest.getId();
        Long notId = questionBankQueryRequest.getNotId();
        String title = questionBankQueryRequest.getTitle();
        String searchText = questionBankQueryRequest.getSearchText();
        String sortField = questionBankQueryRequest.getSortField();
        String sortOrder = questionBankQueryRequest.getSortOrder();
        Long userId = questionBankQueryRequest.getUserId();
        String description = questionBankQueryRequest.getDescription();
        String picture = questionBankQueryRequest.getPicture();
        if(StringUtils.isNotBlank(searchText)) {
            queryWrapper.and(qw -> qw.like("title", searchText).or().like("description", searchText));
        }
        //模糊查询
        queryWrapper.like(StringUtils.isNotBlank(title), "title", title);
        queryWrapper.like(StringUtils.isNotBlank(description), "description", description);
        //精确查询
        queryWrapper.ne(ObjectUtil.isNotEmpty(notId), "id", notId);
        queryWrapper.eq(ObjectUtil.isNotEmpty(id), "id", id);
        queryWrapper.eq(ObjectUtil.isNotEmpty(userId), "userId", userId);
        queryWrapper.eq(ObjectUtil.isNotEmpty(picture), "picture", picture);
        //排序规则
        queryWrapper.orderBy(SqlUtils.validSortField(sortField),
            sortOrder.equals("ascend"),
            sortField);

        return queryWrapper;
    }

    @Override
    public Page<QuestionBankVO> getQuestionBankVOPage(Page<QuestionBank> questionBankPage, HttpServletRequest request) {
        List<QuestionBank> questionBankList = questionBankPage.getRecords();
        Page<QuestionBankVO> questionBankVOPage = new Page<>(questionBankPage.getCurrent(), questionBankPage.getSize(), questionBankPage.getTotal());
        if(CollUtil.isEmpty(questionBankList)) {
            return questionBankVOPage;
        }
        List<QuestionBankVO> questionBankVOList = questionBankList.stream().map(
                questionBank -> {
                    return QuestionBankVO.objToVo(questionBank);
                }).collect(Collectors.toList());
        Set<Long> userIdSet = questionBankList.stream().map(QuestionBank::getUserId).collect(Collectors.toSet());
        Map<Long, List<User>> userIdUserListMap = userService.listByIds(userIdSet).stream()
                .collect(Collectors.groupingBy(User::getId));
        // 填充信息
        questionBankVOList.forEach(questionBankVO -> {
            Long userId = questionBankVO.getUserId();
            User user = null;
            if (userIdUserListMap.containsKey(userId)) {
                user = userIdUserListMap.get(userId).get(0);
            }
            questionBankVO.setUser(userService.getUserVO(user));
        });

        questionBankVOPage.setRecords(questionBankVOList);
        return questionBankVOPage;
    }

    /**
     * 校验数据
     *
     * @param questionBank
     * @param add      对创建的数据进行校验
     */
    @Override
    public void validQuestionBank(QuestionBank questionBank, boolean add) {
        ThrowUtils.throwIf(questionBank == null, ErrorCode.PARAMS_ERROR);
        // todo 从对象中取值
        String title = questionBank.getTitle();
        // 创建数据时，参数不能为空
        if (add) {
            // todo 补充校验规则
            ThrowUtils.throwIf(StringUtils.isBlank(title), ErrorCode.PARAMS_ERROR);
        }
        // 修改数据时，有参数则校验
        // todo 补充校验规则
        if (StringUtils.isNotBlank(title)) {
            ThrowUtils.throwIf(title.length() > 80, ErrorCode.PARAMS_ERROR, "标题过长");
        }
    }
}





package com.qf.shuati.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.qf.shuati.domain.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.qf.shuati.domain.dto.user.UserQueryRequest;
import com.qf.shuati.domain.vo.LoginUserVO;
import com.qf.shuati.domain.vo.UserVO;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

/**
* @author 20688
* @description 针对表【user(用户)】的数据库操作Service
* @createDate 2025-11-19 16:15:24
*/
public interface UserService extends IService<User> {

    long userRegister(String userAccount, String userPassword, String checkPassword);

    LoginUserVO userLogin(String userAccount, String userPassword, HttpServletRequest request);

    boolean userLogout(HttpServletRequest request);
    
    LoginUserVO getLoginUserVO(User user);

    User getLoginUser(HttpServletRequest request);

    UserVO getUserVO(User user);

    List<UserVO> getUserVO(List<User> userList);

    QueryWrapper<User> getQueryWrapper(UserQueryRequest userQueryRequest);

    boolean isAdmin(HttpServletRequest request);

    boolean isAdmin(User user);

    boolean addUserSignIn(User loginUser);

    List<Integer> getUserSignInRecord(Long id, Integer year);
}

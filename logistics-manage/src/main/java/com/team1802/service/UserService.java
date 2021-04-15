package com.team1802.service;

import com.team1802.dto.*;
import com.team1802.pojo.Node;
import com.team1802.pojo.User;
import com.team1802.vo.ListVO;

import java.util.List;

/**
 * @author fengmingxian
 * Created on 2020-12-27
 */
public interface UserService {
    Integer checkUser(UserLoginDTO dto);

    User getUser(UserGetDTO dto);

    boolean sendCode(String emailNo);

    boolean sendCode2(String email);

    boolean registerUser(UserAddDTO dto);

    boolean deleteUser(int id);

    boolean updateUser(UserUpdateDTO dto);//修改

    ListVO<User> listUser(UserListDTO dto);

    List<Node> listNode(UserMenuDTO dto);

    boolean updateUserPw(UserUpdatePwDTO dto);

    boolean updateUserVerify(UserUpdateVerifyDTO dto);

    List<User> selectUser(UserSelectDTO dto);

    boolean findPassword(UserFindPasswordDTO dto);

    List<String> getAllUsername();

    int getVerify(UserLoginDTO dto);
}

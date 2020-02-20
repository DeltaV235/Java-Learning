package com.wuyue.case17.service.impl;

import com.wuyue.case17.dao.UserDao;
import com.wuyue.case17.dao.impl.UserDaoImpl;
import com.wuyue.case17.entities.User;
import com.wuyue.case17.service.UserService;

import java.util.List;

/**
 * @author DeltaV235
 * @version 1.0
 * @className UserServiceImpl
 * @description 实行UserService接口，查询所用用户的数据
 * @date 2020/2/18 18:12
 */
public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    /**
     * @author DeltaV235
     * @date 2020/2/18 18:15
     * @see UserService
     */
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public boolean updateUser(User user) {
        return checkUserData(user) && userDao.updateUser(user);
    }

    @Override
    public User findUser(int id) {
        return userDao.findUser("id", id);
    }

    @Override
    public boolean deleteUser(int id) {
        return userDao.deleteUser(id);
    }

    /**
     * @param user 由controller封装好的User对象
     * @return dao层添加成功则返回true，否则返回false
     * @author DeltaV235
     * @date 2020/2/18 20:13
     * @description 完成添加一个用户的业务功能
     */
    @Override
    public boolean addUser(User user) {
        return checkUserData(user) && userDao.addUser(user);
    }

    /**
     * @author DeltaV235
     * @date 2020/2/19 17:28
     */
    @Override
    public boolean isLegal(User loginUser) {
        String loginUserName = loginUser.getUsername();
        String loginPassword = loginUser.getPassword();
        User user = userDao.findUser("username", loginUserName);
        return user != null && user.getPassword().equals(loginPassword);
    }

    @Override
    public boolean isCheckCodeLegal(String checkCode, String inputCheckCode) {
        if (checkCode != null) {
            return checkCode.equalsIgnoreCase(inputCheckCode);
        }
        return false;
    }

    @Override
    public boolean checkUserData(User user) {
        return user.getName().length() >= 1 && user.getGender().length() >= 1 && user.getQq().length() >= 1 && user.
                getEmail().length() >= 1 && user.getAddress().length() >= 1;
    }

    /**
     * @author DeltaV235
     * @date 2020/2/19 23:56
     */
    @Override
    public int batchDelUsers(String[] ids) {
        int count = 0;
        if (ids != null && ids.length > 0) {
            for (String id : ids) {
                boolean isDel = userDao.deleteUser(Integer.parseInt(id));
                if (isDel)
                    count++;
            }
        }
        return count;
    }
}

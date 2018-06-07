package com.entor.hrm.service.impl;

import com.entor.hrm.mapper.UserMapper;
import com.entor.hrm.po.User;
import com.entor.hrm.service.UserService;
import com.entor.hrm.util.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.transaction.annotation.Isolation.DEFAULT;
import static org.springframework.transaction.annotation.Propagation.REQUIRED;

@Service("userSerivce")
@Transactional(propagation = REQUIRED, isolation = DEFAULT, readOnly = false)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public User findByLoginNameAndPassword(String loginName, String password) {
        EncryptUtil encryptUtil = null;
        User user = null;
        try {
            encryptUtil = new EncryptUtil();
            user = userMapper.selectByLoginName(loginName);
            System.out.println("从数据库中获取的密码："+user.getPassword());
            if (!encryptUtil.convert(user.getPassword()).equals(password)){
                //如果从数据库查出的密码和输入的密码不一样，则返回null
                user = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            user = null;
        }
        return user;
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getAll() {
        return userMapper.selectAll();
    }

    @Transactional(readOnly = true)
    @Override
    public PageModel<User> getByPage(User user, Integer pageIndex, Integer pageSize) {
        // 1.整理参数
        Map<String, Object> params = new HashMap<>();
        params.put("user", user);
        // 根据检索条件查询记录总数
        int recordCount = userMapper.count(params);
        System.out.println("按条件查询的结果记录数：" + recordCount);
        // 整理分页参数
        PageModel<User> pageModel = new PageModel<User>();
        System.out.println("pageModel ->" + pageModel);
        pageModel.setPageIndex(pageIndex);
        pageModel.setPageSize(pageSize);
        pageModel.setRecordCount(recordCount);
        if (recordCount > 0) {
            // 根据检索和分页条件查询用户记录，保存到分页对象中
            params.put("pageModel", pageModel);
            pageModel.setPageList(userMapper.selectByPage(params));
        }
        return pageModel;
    }


    @Override
    public void modifyUser(User user) {
        userMapper.update(user);
    }

    @Override
    public void save(User user) {
        try {
            EncryptUtil encryptUtil = new EncryptUtil();
            user.setPassword(encryptUtil.convert(user.getPassword()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        userMapper.insert(user);
    }

    @Override
    public void removeById(Integer id) {
        userMapper.deleteById(id);
    }

    @Override
    public void batchRemoveUser(Integer[] ids) {
        Map<String, Object> params = new HashMap<>();
        params.put("ids", ids);
        userMapper.batchDelete(params);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(Integer id) {
        return userMapper.selectById(id);
    }

    @Transactional(readOnly = true)
    @Override
    public User getById(Integer id) {
        User user = new User();
        try {
            EncryptUtil encryptUtil = new EncryptUtil();
            user = userMapper.selectById(id);
            String password = encryptUtil.convert(user.getPassword());
            System.out.println("解密后的密码："+password);
            user.setPassword(password);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    @Override
    public List<User> getByIds(Integer[] ids) {
        Map<String, Object> params = new HashMap<>();
        params.put("ids", ids);
        return userMapper.selectByIds(params);
    }
}

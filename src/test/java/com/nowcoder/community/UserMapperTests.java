package com.nowcoder.community;

import com.nowcoder.community.dao.DiscussPostMapper;
import com.nowcoder.community.dao.LoginTicketMapper;
import com.nowcoder.community.dao.UserMapper;
import com.nowcoder.community.entity.DiscussPost;
import com.nowcoder.community.entity.LoginTicket;
import com.nowcoder.community.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = CommunityApplication.class)
public class UserMapperTests {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private DiscussPostMapper discussPostMapper;
    @Autowired
    private LoginTicketMapper loginTicketMapper;
    @Test
    public void selectUserTest() {
        User user = userMapper.selectById(101);
        System.out.println(user);
        user = userMapper.selectByName("aaa");
        System.out.println(user);
        user = userMapper.selectByEmail("nowcoder12@sina.com");
        System.out.println(user);
    }

    @Test
    public void TestInsertUser() {
        User user = new User();
        user.setUsername("test");
        user.setPassword("121212");
        user.setSalt("盐");
        user.setEmail("test@qq.com");
        user.setType(101);
        user.setStatus(125);
        user.setActivationCode("ace");
        user.setHeaderUrl("head");

        int rows = userMapper.insertUser(user);
        System.out.println(rows);
        System.out.println(user.getId());
    }
    @Test
    public void TestUpdateUser() {
        int rows = userMapper.updateHeader(150,"HHHHHH");
        System.out.println(rows);
        userMapper.updatePassword(150,"abc123");
        rows = userMapper.updateStatus(150, 120);
        System.out.println(rows);
    }
    @Test
    public void DiscussPostTest() {
        List<DiscussPost> list = discussPostMapper.selectDiscussPosts(149, 0, 10,1);
        for (DiscussPost post : list) {
            System.out.println(post);
        }
        int count = discussPostMapper.selectDiscussPostRows(149);
        System.out.println(count);
    }
    @Test
    public void testInsertLoginTicket() {
        LoginTicket loginTicket = new LoginTicket();
        loginTicket.setId(203);
        loginTicket.setTicket("abcd");
        loginTicket.setStatus(0);
        loginTicket.setExpired(new Date(System.currentTimeMillis() + 1000 * 60 * 10));

        loginTicketMapper.insertLoginTicket(loginTicket);
    }
    @Test
    public void testSelectLoginTicket() {
        LoginTicket loginTicket = loginTicketMapper.selectByTicket("abcd");
        System.out.println(loginTicket);

        loginTicketMapper.updateStatus("abcd", 1);
        loginTicket = loginTicketMapper.selectByTicket("abcd");
        System.out.println(loginTicket);
    }
}

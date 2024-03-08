package com.itheima.controller;

import com.itheima.entity.User;
import com.itheima.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping

//    @CachePut(cacheNames = "userCache" ,key = "#user.id")//key的生成：cacheNames::key    如userCache::1
//    @CachePut(cacheNames = "userCache" ,key = "#a0.id") //如果有多个参数可以用a0、a1或p0、p1。。来取
    @CachePut(cacheNames = "userCache" ,key = "#result.id") //取的是return user;中的user对象的id
    //CachePut缓存的所有key都是先执行save完再进行缓存，使用这三种注解都是一样的
    public User save(@RequestBody User user){
        userMapper.insert(user);
        user.setId(10L);
        return user;
    }

    @DeleteMapping
    @Cacheable(cacheNames = "userCache",key = "#id")
    public void deleteById(Long id){
        userMapper.deleteById(id);
    }

	@DeleteMapping("/delAll")
    @CacheEvict(cacheNames = "userCache",allEntries = true)  //删除所有userCache的缓存
    public void deleteAll(){
        userMapper.deleteAll();
    }

    @GetMapping
    public User getById(Long id){
        User user = userMapper.getById(id);
        return user;
    }

}

package com.baizhi.travels.controller;

import com.baizhi.travels.entity.Result;
import com.baizhi.travels.entity.User;
import com.baizhi.travels.service.UserService;
import com.baizhi.travels.utils.CreateImageCode;
import com.baizhi.travels.utils.ValidateImageCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author Liu
 * @date 2020/10/3 10:47:45
 * @description
 */
@RestController // 不用再方法上加@ResponseBody，就会把所有方法的返回值都转成JSON //Controller
@RequestMapping("user")
@CrossOrigin    //  允许跨域    以后前端架构可能部署在Node.js上，域可能不一样
@Slf4j  //  打印参数
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("login")
    public Result login(@RequestBody User user, HttpServletRequest request){
        Result result = new Result();
        log.info("user: " + user);
        try{
            User userDB = userService.login(user);
            //  登录成功之后保存用户的标记 applicationContext servletContext Redis userId userDB
            request.getServletContext().setAttribute(userDB.getId(), userDB);
            result.setMsg("登录成功").setUserId(userDB.getId());
        } catch (Exception e){
            result.setStatus(false).setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 用户注册
     * @param code
     * @param user
     * @return
     */
    @PostMapping("register")
    @ResponseBody
    public Result register(String code, String key, @RequestBody User user, HttpServletRequest request){
        Result result = new Result();
        log.info("接收的验证码：" + code);
        log.info("接收的验证码的key：" + key);
        log.info("接收到的user对象：" + user);
        //  验证验证码
        String keyCode = (String) request.getServletContext().getAttribute(key);
        log.info("接收到的keyCode：" + keyCode);
        try {
            if(code.equalsIgnoreCase(keyCode)){
                //  注册用户
                userService.register(user);
                result.setMsg("注册成功");
            }else{
                throw new RuntimeException("验证码错误");
            }
        } catch (Exception e){
            e.printStackTrace();
            result.setMsg(e.getMessage()).setStatus(false);
        }
        return result;
    }


    /**
     * 生成验证码
     * @param
     * @param request
     * @throws IOException
     */
    //  GetMapping只允许GET请求方式, RequestMapping允许任意的请求方式
    @GetMapping("getImage")
    @ResponseBody
    public Map<String, String> getImage(HttpServletRequest request) throws IOException {
        Map<String, String> result = new HashMap<>();
        CreateImageCode createImageCode = new CreateImageCode();
        //  获取验证码
        String securityCode = createImageCode.getCode();
        //  验证码存入session
//        session.setAttribute("code", securityCode);
//        session.getServletContext().setAttribute("key", UUID.randomUUID().toString());
        String key = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        request.getServletContext().setAttribute(key, securityCode);
        //  生成图片
        BufferedImage image = createImageCode.getBuffImg();
        //  响应浏览器
        //response.setContentType("image/png");
        //进行base64编码
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", bos);
        String string = Base64Utils.encodeToString(bos.toByteArray());
        result.put("key", key);
        result.put("image", string);
        return result;
    }
}

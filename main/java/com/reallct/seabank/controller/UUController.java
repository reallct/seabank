package com.reallct.seabank.controller;


import com.reallct.seabank.exception.BaseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Rest风格的接口
 *
 * @author ahuang
 * @version V1.0
 * @Title: BookRestController
 * @Program: bookCornerServer
 * @Package com.ahuang.bookCornerServer.controller
 * @create 2018-07-05 22:34
 */
@RestController
public class UUController extends BaseController{

    @GetMapping(value = "/1")
    public String index() throws BaseException {
        return "HELLO";
    }

}

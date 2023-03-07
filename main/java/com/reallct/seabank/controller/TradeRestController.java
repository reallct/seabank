package com.reallct.seabank.controller;


import com.reallct.seabank.controller.req.CommonRequest;
import com.reallct.seabank.controller.req.CommonResponse;
import com.reallct.seabank.controller.req.TradeListReq;
import com.reallct.seabank.entity.TradeEntity;
import com.reallct.seabank.entity.UserEntity;
import com.reallct.seabank.exception.BaseException;
import com.reallct.seabank.servise.FamilyService;
import com.reallct.seabank.servise.TradeService;
import com.reallct.seabank.servise.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@Slf4j
@RestController
@RequestMapping(path = "/seabank/v1")
public class TradeRestController extends BaseController {
    /**
     * JWT加密密钥
     */
    @Value("${jwt.secret}")
    protected String SECRET;

    /**
     * JWT超时时间
     */
    @Value("${jwt.expiration.time}")
    protected long EXPIRATIONTIME;

    private TradeService tradeService;
    private UserService userService;
    private FamilyService familyService;


    @Autowired
    public TradeRestController(TradeService tradeService, UserService userService, FamilyService familyService) {
        this.tradeService = tradeService;
        this.userService = userService;
        this.familyService = familyService;

    }


//传参三种方式：
//1.在url中传参数@RequestParam，无需新建实体类，但使用时需要做类型转换
//2.在body中传参数@RequestBody，需要新建req的实体类
//3.在URL中显示带入@PathVariable，如/trades/{tradeId}


//查、删可以放url中，也就是1或2，因为只传序号或条件
    //更新、新增还是json吧

    @PostMapping(value = "/trades")

    public Integer insertTradeRecord(@RequestParam("tradeDate") String tradeDate, @RequestParam("tradeTime") String tradeTime, @RequestParam("userId") String userId, @RequestParam("type1") String type1, @RequestParam("type2") String type2,
                                     @RequestParam("detail") String detail, @RequestParam("tradeAmt") String tradeAmt, @RequestParam("tradeChannel") String tradeChannel, @RequestParam("note") String note) throws BaseException, IOException {

        TradeEntity entity = new TradeEntity();
        entity.setTradeDate(Integer.valueOf(tradeDate));
        entity.setTradeTime(Integer.valueOf(tradeTime));
        entity.setUserId(Integer.valueOf(userId));
        entity.setType1(type1);
        entity.setType2(type2);
        entity.setDetail(detail);
        entity.setTradeAmt(new BigDecimal(tradeAmt));
        entity.setTradeChannel(tradeChannel);
        entity.setNote(note);
        tradeService.insertTradeRecord(entity);
        // 返回自增主键
        return entity.getTradeId();
    }

    @PutMapping(value = "/trades")
    public Integer updateTradeRecord(@RequestParam("tradeId") String tradeId, @RequestParam("tradeDate") String tradeDate, @RequestParam("tradeTime") String tradeTime, @RequestParam("userId") String userId, @RequestParam("type1") String type1, @RequestParam("type2") String type2,
                                     @RequestParam("detail") String detail, @RequestParam("tradeAmt") String tradeAmt, @RequestParam("tradeChannel") String tradeChannel, @RequestParam("note") String note) throws BaseException, IOException {


        TradeEntity entity = new TradeEntity();
        entity.setTradeId(Integer.valueOf(tradeId));
        entity.setTradeDate(Integer.valueOf(tradeDate));
        entity.setTradeTime(Integer.valueOf(tradeTime));
        entity.setUserId(Integer.valueOf(userId));
        entity.setType1(type1);
        entity.setType2(type2);
        entity.setDetail(detail);
        entity.setTradeAmt(new BigDecimal(tradeAmt));
        entity.setTradeChannel(tradeChannel);
        entity.setNote(note);

        return tradeService.updateTradeRecord(entity);
    }


    @DeleteMapping(value = "/trades/{tradeId}")
    public Integer deleteTradeRecord(@PathVariable Integer tradeId) throws BaseException {
        return tradeService.deleteTradeRecord(tradeId);
    }


    @GetMapping(value = "/trades/{tradeId}")
    public CommonResponse<?> queryTradeRecord(@PathVariable Integer tradeId, HttpServletRequest request) throws BaseException {
        Object res = tradeService.queryTradeRecord(tradeId);
        return getRes(res);

    }

    @GetMapping(value = "/trades/queryTradeList")
    public CommonResponse<?> queryTradeList(@RequestBody @Valid CommonRequest<TradeListReq> req, HttpSession session) throws BaseException {

        Integer num = req.getParams().getNum();
        Integer pageSize = req.getParams().getPageSize();

        Integer userId = req.getParams().getUserId();
        String mode = req.getParams().getMode();

        String type1 = req.getParams().getType1();
        String type2 = req.getParams().getType2();
        String tradeChannel = req.getParams().getTradeChannel();
        Integer beginDate = req.getParams().getBeginDate();
        Integer endDate = req.getParams().getEndDate();


        Map<String, Object> param = new HashMap<>();
        param.put("num", num);
        param.put("pageSize", pageSize);


        // 如果是管理员，默认查询全家的交易，除非选择只查自己
        UserEntity user = userService.queryUserInfo(userId);
        if ("Y".equals(user.getIsAdmin())) {
            if ("P".equals(mode)) {
                param.put("userId", userId);
            } else {
                List list = familyService.queryFamilyMember(user.getFamilyId());
                StringBuilder liststr = new StringBuilder();
                for (int i = 0; i < list.size(); i++) {
                    if (i == 0) {
                        liststr.append(list.get(i).toString());
                    } else {
                        liststr.append(", ").append(list.get(i).toString());
                    }
                }
                param.put("liststr", liststr);
            }
        } else {
            param.put("userId", userId);
        }

        param.put("type1", type1);
        param.put("type2", type2);
        param.put("tradeChannel", tradeChannel);
        param.put("beginDate", beginDate);
        param.put("endDate", endDate);

        System.out.println(param);
        Object res = tradeService.queryTradelist(param);

        return getRes(res);
    }

//    @GetMapping(value = "/trades/queryTradeAmtSum")
//    public CommonResponse<?> queryTradeAmtSum(@RequestBody @Valid CommonRequest<TradeListReq> req, HttpSession session) throws BaseException {
//
//
//        Integer num = req.getParams().getNum();
//        String phone = req.getParams().getPhone();
//
//        //搜索的关键词都改成小写
//        Map<String, Object> param = new HashMap<>();
//        param.put("num", num);
//        param.put("pageSize", 20);
//        param.put("phone", phone);
//
//        Object res = tradeService.getUserListByphone(param);
//        return getRes(res);
//
//
//    }


}

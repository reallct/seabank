package com.reallct.seabank.servise;

import com.reallct.seabank.entity.UserEntity;
import com.reallct.seabank.exception.BaseException;
import org.springframework.stereotype.Service;

/**
 * 
* @ClassName: CommonService
* @Description: 公共服务类
* @author ahuang
* @date 2018年6月10日 下午8:50:11
* @version V1.0
 */
@Service
public interface CommonService {
	/**
	 * 
	* @Title: getOpenidByCode
	* @Description: 使用code获取openid
	* @param code
	* @return String    返回类型
	* @author ahuang  
	* @date 2018年6月10日 下午9:04:49
	* @version V1.0
	* @throws
	 */
	String getOpenidByCode(String code);
	/**
	* @Title: getUserByOpenid
	* @Description: 根据openid获取绑定用户信息
	* @param openid
	* @return UserEntity    返回类型
	* @author ahuang  
	* @date 2018年6月10日 下午9:54:01
	* @version V1.0
	* @throws
	*/
	UserEntity getUserByOpenid(String openid);
	
	/**
	* @Title: custUserBind
	* @Description: 绑定用户
	* @param user
	* @throws BaseException void    返回类型
	* @author ahuang  
	* @date 2018年6月26日 下午10:02:19
	* @version V1.0
	* @throws
	*/
	void custUserBind(UserEntity user) throws BaseException;



}

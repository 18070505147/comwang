package com.chejet.cloud.util;

import com.chejet.cloud.common.ErrorCodeEnum;
import com.chejet.cloud.exception.BaseException;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 密码加密实现提供者
 * 
 * @author Random
 */
public class PasswordProvider {

	// salt
	private static final String SUFFIX = "`1qazx";

	/**
	 * 加密
	 * 
	 * @param password
	 *            Md5密码
	 * @return
	 */
	public static String encrypt(String password) {
		if (StringUtils.isBlank(password)) {
			throw new BaseException(ErrorCodeEnum.PARAM_ERROR);
		}
		try {
			return md5(new StringBuilder(password).append(SUFFIX).toString());
		}
		catch (Exception e) {
			throw new BaseException(ErrorCodeEnum.PASSWORD_ENCRYPTION_ERROR);
		}
	}

	private static String md5(String str) {
		String password = null;
		try {
			// 生成一个MD5加密计算摘要
			MessageDigest md = MessageDigest.getInstance("MD5");
			// 计算md5函数
			md.update(str.getBytes());
			// digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
			// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
			password = new BigInteger(1, md.digest()).toString(16);
		}
		catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return password;
	}

	/**
	 * 随机生成密码
	 * @param length 密码长度 不小于6位
	 * @param includCap 是否包含大写字符
	 * @return
	 */
	public static String generateRandomPassword(int length,boolean includCap){
		if (length >= 6){
			int param = 36;
			if (includCap){
				param = 62;
			}
			StringBuilder sb = new StringBuilder();
			String strAll = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
			for (int i = 0; i < length; i++) {
				int f = (int) (Math.random()*param);
				sb.append(strAll.charAt(f));
			}
			return sb.toString();
		}
		return null;
	}
	public static void main(String[] args) {
		String password = "123456";
		System.err.println("加密后:" + encrypt(password));

		System.out.println(generateRandomPassword(8,false));
	}
}

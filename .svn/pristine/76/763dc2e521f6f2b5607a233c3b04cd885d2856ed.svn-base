package com.chejet.cloud.service.captchaApi;

import com.chejet.cloud.dto.CacheCaptcha;
import com.chejet.cloud.service.captchaApi.mode.Captcha;
import com.chejet.cloud.service.captchaApi.mode.ExpressionCaptcha;
import com.chejet.cloud.service.captchaApi.mode.GoogleKaptcha;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * 验证码缓存工具类
 * 
 * @author Random
 */
public class CaptchaHelper {

	public static final String ID_CAPTCHA = "Challenge";

	public static CacheCaptcha setInCache(final HttpServletRequest request, HttpServletResponse response, Integer width, Integer height, Integer type, String challenge) throws IOException {

		type = type == null ? 3 : type;

		CacheCaptcha cacheCaptcha = new CacheCaptcha();
		cacheCaptcha.setChallenge(challenge);
		BufferedImage image = null;
		if (Objects.equals(type, 1)){
			image = new Captcha() {
				protected void setInCache(String captcha) {
					createCache(response, cacheCaptcha, captcha);
				}
			}.generate(width, height);
		}
		if (Objects.equals(type, 2)){
			image = new ExpressionCaptcha() {
				protected void setInCache(String captcha) {
					createCache(response, cacheCaptcha, captcha);
				}
			}.generate(null, null);
		}
		if (Objects.equals(type, 3)){
			image = new GoogleKaptcha(){
				@Override
				protected void setInCache(String captcha) {
					createCache(response, cacheCaptcha, captcha);
				}
			}.generate(width, height);
		}

		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(image, "jpg", out);
		try {
			out.flush();
		}
		finally {
			out.close();
		}

		return cacheCaptcha;
	}

	private static void createCache(HttpServletResponse response, CacheCaptcha cacheCaptcha, String captcha){
		String captchaId = cacheCaptcha.getChallenge() == null ? UUID.randomUUID().toString().replaceAll("-", "") : cacheCaptcha.getChallenge();
		cacheCaptcha.setChallenge(captchaId);
		cacheCaptcha.setCaptcha(captcha);
		// 为验证码绑定ID
		response.addHeader(ID_CAPTCHA, captchaId);
	}

	public static boolean validate(String captchaId, String captcha) {
		// String sessionCaptcha = request.getSession().getAttribute(CaptchaHelper.CACHE_CAPTCHA).toString();
		return false;
	}
}
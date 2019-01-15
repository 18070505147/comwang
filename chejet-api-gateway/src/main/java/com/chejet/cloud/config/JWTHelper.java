package com.chejet.cloud.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

/**
 * @author Neo.fang
 * @creatTime 2018/11/8 0008
 */
@Configuration
public class JWTHelper {

    private static final Logger logger = LoggerFactory.getLogger(JWTHelper.class);

    private static String publicKey;
    private static String privateKey;

    public static String getPublicKey() {
        return publicKey;
    }

    @Value("${jwt.publicKey}")
    public void setPublicKey(String publicKey) {
        JWTHelper.publicKey = publicKey;
    }

    public static String getPrivateKey() {
        return privateKey;
    }

    @Value("${jwt.privateKey}")
    public void setPrivateKey(String privateKey) {
        JWTHelper.privateKey = privateKey;
    }


    // 创建JWT token
    public static String createJwt(Long userId, Long appId, String deviceInfo){
        String token = "";
        try {
            RSAPublicKey rsaPublicKey = (RSAPublicKey) convertPublicKey(publicKey);
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey)convertPrivateKey(privateKey);
            Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
            token = JWT.create()
                    .withClaim("userId",userId)
                    .withClaim("appId",appId)
                    .withClaim("clientInfo", deviceInfo)
                    //.withExpiresAt(generateExpireDate())
                    .sign(algorithm);
        } catch (Exception e) {
            logger.warn("生成JWT出现异常：" + e.getMessage());
        }
        return token;
    }


    // 验证JWT token
    public static boolean verifyJwt(String token) {
        try {
            RSAPublicKey rsaPublicKey = (RSAPublicKey) convertPublicKey(publicKey);
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey)convertPrivateKey(privateKey);
            Algorithm algorithm = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            return true;
        } catch (JWTVerificationException e2) {
            logger.warn("验证JWT异常：" + e2.getMessage());
            return false;
        }catch (Exception e1){
            logger.warn("验证JWT异常：" + e1.getMessage());
            return false;
        }
    }


    // 将base64编码后的公钥字符串转成PublicKey实例
    public static PublicKey convertPublicKey(String publicKey) throws Exception{
        byte[] keyBytes = Base64.getDecoder().decode(publicKey.getBytes());
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(keySpec);
    }

    //将base64编码后的私钥字符串转成PrivateKey实例
    public static PrivateKey convertPrivateKey(String privateKey) throws Exception{
        byte[ ] keyBytes=Base64.getDecoder().decode(privateKey.getBytes());
        PKCS8EncodedKeySpec keySpec=new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory=KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }


    //设置过期时间
    private Date generateExpireDate(){
        Date current = new Date();
        current.setTime(current.getTime() + 1800);
        return current;
    }

    //解析token的payload部分
    public static Map<String,Claim> parseClaims(String token){
        if (token == null){
            return null;
        }
        Map<String,Claim> claims = JWT.decode(token).getClaims();
        return claims;
    }

}

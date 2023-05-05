package team.sxcoding.Utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Util {


        /**
         * 对字符串进行MD5加密
         * @param password 待加密的原始密码
         * @return 加密后的结果
         */
        public static String encrypt(String password) {

            try {
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] messageDigest = md.digest(password.getBytes());
                BigInteger no = new BigInteger(1, messageDigest);
                String hashText = no.toString(16);
                while (hashText.length() < 32) {
                    hashText = "0" + hashText;
                }
                return hashText;
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }

}


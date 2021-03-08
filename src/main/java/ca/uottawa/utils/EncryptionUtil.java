package ca.uottawa.utils;

import org.apache.commons.codec.digest.DigestUtils;

public class EncryptionUtil {

    public static String encrypt(String password) {
        return DigestUtils.md5Hex(password);
    }

}

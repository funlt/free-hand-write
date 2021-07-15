package com.funlt.freehandwrite.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

public class Sm4Util {


    /**
     * 加密
     * @param content
     * @param key
     * @return
     */
    public static String sm4Encode(String content, String key) {
        SymmetricCrypto sm4 = SmUtil.sm4(key.getBytes());
        return sm4.encryptHex(content);
    }

    /**
     * 解密
     * @param encrypt
     * @param key
     * @return
     */
    public static String sm4Decode(String encrypt, String key){
        SymmetricCrypto sm4 = SmUtil.sm4(key.getBytes());
        return sm4.decryptStr(encrypt, CharsetUtil.CHARSET_UTF_8);
    }
}

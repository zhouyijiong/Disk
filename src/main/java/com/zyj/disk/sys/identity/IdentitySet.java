package com.zyj.disk.sys.identity;

import com.zyj.disk.sys.exception.Client;
import lombok.AllArgsConstructor;

//TODO 增加群体类身份机制(如科技部,财务部,营业部等)

/**
 * 身份集(白名单模式)
 */
@AllArgsConstructor
public enum IdentitySet {
    /**
     * 限制为游客身份
     */
    VISITOR(),

    /**
     * 限制为用户身份
     */
    USER();

    /**
     * 判断当前身份是否属于访问区间内,如不是则抛出验证失败
     */
    public static void check(IdentitySet[] targets, Object current) {
        for (IdentitySet item : targets) {
            if (item.equals(current)) return;
        }
        throw Client.VERIFY_ERROR.exception;
    }
}
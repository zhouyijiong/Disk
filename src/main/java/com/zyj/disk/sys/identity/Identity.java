package com.zyj.disk.sys.identity;

/**
 * 身分类
 */
public interface Identity {
    static boolean check(String identity, IdentitySet[] identitySets) {
        for (IdentitySet item : identitySets) {
            if (identity.equals(item.toString())) return true;
        }
        return false;
    }
}
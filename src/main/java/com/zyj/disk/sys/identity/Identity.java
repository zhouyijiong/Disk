package com.zyj.disk.sys.identity;

/**
 * 身分类
 */
public interface Identity {
    default boolean check(IdentitySet[] identitySets) {
        for (IdentitySet item : identitySets) {
            if (this.equals(item.identity)) return true;
        }
        return false;
    }
}
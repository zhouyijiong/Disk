package com.zyj.disk.sys.identity;

/**
 * 身分类
 */
public interface Identity {
    default boolean check(IdentitySet[] identitySets) {
        for(IdentitySet item : identitySets){
            if(item.identity.equals(this)) return true;
        }
        return false;
    }

    static boolean check(String identity, IdentitySet[] identitySets) {
        for (IdentitySet item : identitySets) {
            if (identity.equals(item.toString())) return true;
        }
        return false;
    }
}
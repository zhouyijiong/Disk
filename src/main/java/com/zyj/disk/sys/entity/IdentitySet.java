package com.zyj.disk.sys.entity;

/**
 * 身份集(白名单模式)
 * //TODO 增加群体类身份机制(如科技部,财务部,营业部等)
 */
public enum IdentitySet {
    /**
     * 限制为游客身份
     */
    VISITOR(),

    /**
     * 限制为用户身份
     */
    USER();

    public boolean check(IdentitySet[] identitySets) {
        for (IdentitySet item : identitySets) {
            if (item.equals(this)) return true;
        }
        return false;
    }

    public boolean checkOnly(IdentitySet[] identitySets) {
        return identitySets.length == 1 && check(identitySets);
    }

    public static boolean check(String identity, IdentitySet[] identitySets) {
        for (IdentitySet item : identitySets) {
            if (identity.equals(item.toString())) return true;
        }
        return false;
    }
}
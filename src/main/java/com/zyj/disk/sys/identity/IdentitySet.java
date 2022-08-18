package com.zyj.disk.sys.identity;

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
    VISITOR(new VisitorIdentity()),

    /**
     * 限制为用户身份
     */
    USER(new UserIdentity());

    public final Identity identity;

    public boolean check(IdentitySet[] identitySets) {
        return false;
    }
}
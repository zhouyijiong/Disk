package com.zyj.disk.sys.identity;

import com.zyj.disk.sys.exception.Client;
import com.zyj.disk.sys.exception.GlobalException;
import lombok.AllArgsConstructor;

/**
 * @Author: ZYJ
 * @Date: 2022/4/25 16:46
 * @Remark: 身份集(白名单模式)
 */
@AllArgsConstructor
public enum IdentitySet{
    /** 限制为游客身份 */
    VISITOR(new Visitor()),

    /** 限制为用户身份 */
    USER(new User());

    public final Identity ident;

    /**
     * @Author: ZYJ
     * @Date: 2022/04/25
     * @Remark: 判断当前身份是否属于访问区间内,如不是则抛出验证失败
     */
    public static void check(IdentitySet[] targets,Object current){
        for(IdentitySet item : targets){
            if(item.ident.equals(current)) return;
        }
        throw new GlobalException(Client.VERIFY_ERROR);
    }

    /**
     * @Author: ZYJ
     * @Date: 2022/04/25
     * @Remark: 身份类
     * TODO 期望增加多对一的身份映射关系(如科技部,财务部,营业部等)
     */
    private abstract static class Identity{}

    /**
     * @Author: ZYJ
     * @Date: 2022/04/25
     * @Remark: 游客
     */
    private static final class Visitor extends Identity{}

    /**
     * @Author: ZYJ
     * @Date: 2022/04/25
     * @Remark: 用户
     */
    private static final class User extends Identity{}
}
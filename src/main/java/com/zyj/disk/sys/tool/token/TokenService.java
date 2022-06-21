package com.zyj.disk.sys.tool.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Author: ZYJ
 * @Date: 2022/6/16 13:25
 * @Remark:
 */
@Service
@AllArgsConstructor
public final class TokenService{
    private TokenMapper tokenMapper;

    public int save(TokenEntity token){
        return tokenMapper.insert(token);
    }
}
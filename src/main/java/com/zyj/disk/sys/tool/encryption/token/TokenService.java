package com.zyj.disk.sys.tool.encryption.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Author: ZYJ
 * @Date: 2022/6/16 13:25
 * @Remark: token service
 */
@Service
@AllArgsConstructor
public final class TokenService {
    private TokenMapper tokenMapper;

    public int save(TokenEntity token) {
        return 0;//tokenMapper.insert(token);
    }
}
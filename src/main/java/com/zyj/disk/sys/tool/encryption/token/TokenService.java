package com.zyj.disk.sys.tool.encryption.token;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Token Service
 */
@Service
@AllArgsConstructor
public final class TokenService {
    private TokenMapper tokenMapper;

    public int save(TokenEntity token) {
        return tokenMapper.insert(token);
    }
}
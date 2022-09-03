package com.zyj.disk.service.user;

import com.zyj.disk.entity.user.UserAttach;
import com.zyj.disk.mapper.user.UserAttachMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public final class UserAttachService {
    private final UserAttachMapper userAttachMapper;

    public int saveUserAttach(UserAttach userAttach) {
        return userAttachMapper.insert(userAttach);
    }
}
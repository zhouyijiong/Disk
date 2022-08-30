package com.zyj.disk.service.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.zyj.disk.mapper.user.UserAttachMapper;

@Service
@RequiredArgsConstructor
public final class UserAttachService {
    private final UserAttachMapper userattachMapper;
}
package com.zyj.disk.controller.user;

import com.zyj.disk.service.user.UserAttachService;
import com.zyj.disk.sys.entity.BaseController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userAttach")
@RequiredArgsConstructor
public class UserAttachController extends BaseController {
    private final UserAttachService userattachService;
}
package com.zyj.disk.entity.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public final class UserAttach {
    private Integer id;
    private Integer userId;
    private Integer vipTime;
    private Integer credibility;
    private Integer create_time;
    private Integer last_edit_time;

    public UserAttach(Integer id, Integer userId, Integer vipTime, Integer credibility, Integer create_time, Integer last_edit_time) {
        this.id = id;
        this.userId = userId;
        this.vipTime = vipTime;
        this.credibility = credibility;
        this.create_time = create_time;
        this.last_edit_time = last_edit_time;
    }
}
package com.zyj.disk.sys.hikari.mapper.explain;

import com.zyj.disk.sys.annotation.mapper.base.Update;
import com.zyj.disk.sys.hikari.mapper.match.Match;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.lang.annotation.Annotation;

/**
 * @Author: ZYJ
 * @Date: 2022/3/25 9:43
 * @Remark: update mapper
 */
@Component
public final class UpdateMapper extends Mapper{
    public UpdateMapper(DataSource dataSource){
        super(dataSource,UpdateMapper.class);
    }

    @Override
    Match init(Annotation annotation){
        return ((Update) annotation).mapperMatch().match;
    }
}
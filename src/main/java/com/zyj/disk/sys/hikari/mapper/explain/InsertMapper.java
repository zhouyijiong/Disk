package com.zyj.disk.sys.hikari.mapper.explain;

import com.zyj.disk.sys.annotation.mapper.base.Insert;
import com.zyj.disk.sys.hikari.mapper.match.Match;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.lang.annotation.Annotation;

/**
 * @Author: ZYJ
 * @Date: 2022/3/25 9:37
 * @Remark: insert mapper
 */
@Component
public final class InsertMapper extends Mapper{
    public InsertMapper(DataSource dataSource){
        super(dataSource,InsertMapper.class);
    }

    @Override
    Match init(Annotation annotation){
        return ( (Insert)annotation ).mapperMatch().match;
    }
}
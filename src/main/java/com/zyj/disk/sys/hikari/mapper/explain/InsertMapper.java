package com.zyj.disk.sys.hikari.mapper.explain;

import com.zyj.disk.sys.annotation.mapper.base.Insert;
import com.zyj.disk.sys.hikari.mapper.match.Match;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.lang.annotation.Annotation;

/** insert mapper */
@Component
public final class InsertMapper extends Mapper{
    public InsertMapper(DataSource dataSource){
        super(dataSource,InsertMapper.class);
    }

    @Override
    Match init(Annotation annotation){
        return ((Insert) annotation).mapperMatch().match;
    }
}
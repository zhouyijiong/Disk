package com.zyj.disk.sys.hikari.mapper.explain;

import com.zyj.disk.sys.annotation.mapper.base.Delete;
import com.zyj.disk.sys.hikari.mapper.match.Match;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.lang.annotation.Annotation;

/** delete mapper */
@Component
public final class DeleteMapper extends Mapper{
    public DeleteMapper(DataSource dataSource){
        super(dataSource,DeleteMapper.class);
    }

    @Override
    Match init(Annotation annotation){
        return ((Delete) annotation).mapperMatch().match;
    }
}
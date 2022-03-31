package com.zyj.disk.sys.hikari.mapper.explain;

import com.zyj.disk.sys.annotation.mapper.base.Delete;
import com.zyj.disk.sys.hikari.mapper.match.Match;
import org.springframework.stereotype.Component;
import javax.sql.DataSource;
import java.lang.annotation.Annotation;

/**
 * @Author: ZYJ
 * @Date: 2022/3/22 9:41
 * @Remark: delete mapper
 */
@Component
public final class DeleteMapper extends Mapper{
    public DeleteMapper(DataSource dataSource){
        super(dataSource,DeleteMapper.class);
    }

    @Override
    Match init(Annotation annotation){
        return ((Delete) annotation).mapperMatch().match;
    }
/*    if(check(joinPoint,annotation)) throw new GlobalException(User.MAPPER_CONFIG_ERROR);
        Class<? extends BaseEntity> clazz = delete.operate();
        StringBuilder sql = new StringBuilder();
        sql.append("delete from ").append(classTool.getRealName(clazz));
        switch(delete.mapperMatch()){
            case NO: return sql.toString();
            case PARAM:
                Object[] args = joinPoint.getArgs();
                List<Integer> probeResult = probe(delete.where());

                for(int num : probeResult){
                    switch(num){
                        case '#':

                            break;
                        case '$':

                            break;
                    }
                }
                break;
        }

        Object[] args = joinPoint.getArgs();
        String where = delete.where();
        Class<? extends BaseEntity> clazz = delete.operate();
        StringBuilder sql = new StringBuilder("delete from ");
        sql.append(classTool.getRealName(clazz));
        switch(args.length){
            case 0: break;
            case 1:
                sql.append(" where 1=1");
                BaseEntity entity = (BaseEntity)args[0];
                Field[] fields = clazz.getDeclaredFields();
                for(Field field : fields){
                    Object val = classTool.getFieldValue(field,entity);
                    if(val == null) continue;
                    sql.append(" AND ").append(field.getName()).append("=").append(val);
                }
                break;
            default:
                Parameter[] parameters = aopTool.getMethod(joinPoint).getParameters();
                for(int i=0;i<args.length;i++){
                    String key = parameters[i].getName();
                    Object val = args[i];
                    if(val != null) where = replace(where,key,val);
                }
                sql.append(" where ").append(where);
        }
        if(delete.print()) System.out.println(sql);
        return sql.toString();*/
}
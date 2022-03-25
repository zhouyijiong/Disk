package com.zyj.disk.sys.hikari.mapper.explain;

import com.zyj.disk.sys.annotation.mapper.Delete;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import java.lang.annotation.Annotation;

/**
 * @Author: ZYJ
 * @Date: 2022/3/22 9:41
 * @Remark: delete mapper
 */
@Component
public final class DeleteMapper implements Mapper{
    private Delete delete;

    @Override
    public boolean check(ProceedingJoinPoint joinPoint,Annotation annotation){
        if(!(annotation instanceof Delete)) return true;
        delete = (Delete) annotation;
        return delete.mapperMatch().MATCH.check(joinPoint,delete);
    }

    @Override
    public String explain(ProceedingJoinPoint joinPoint,Annotation annotation){
        return check(joinPoint,annotation) ? delete.mapperMatch().MATCH.explain(joinPoint,delete) : null;

//        if(check(joinPoint,annotation)) throw new GlobalException(User.MAPPER_CONFIG_ERROR);
//        Class<? extends BaseEntity> clazz = delete.operate();
//        StringBuilder sql = new StringBuilder();
//        sql.append("delete from ").append(classTool.getRealName(clazz));
//        switch(delete.mapperMatch()){
//            case NO: return sql.toString();
//            case PARAM:
//                Object[] args = joinPoint.getArgs();
//                List<Integer> probeResult = probe(delete.where());
//
//                for(int num : probeResult){
//                    switch(num){
//                        case '#':
//
//                            break;
//                        case '$':
//
//                            break;
//                    }
//                }
//                break;
//        }

//        Object[] args = joinPoint.getArgs();
//        String where = delete.where();
//        Class<? extends BaseEntity> clazz = delete.operate();
//        StringBuilder sql = new StringBuilder("delete from ");
//        sql.append(classTool.getRealName(clazz));
//        switch(args.length){
//            case 0: break;
//            case 1:
//                sql.append(" where 1=1");
//                BaseEntity entity = (BaseEntity)args[0];
//                Field[] fields = clazz.getDeclaredFields();
//                for(Field field : fields){
//                    Object val = classTool.getFieldValue(field,entity);
//                    if(val == null) continue;
//                    sql.append(" AND ").append(field.getName()).append("=").append(val);
//                }
//                break;
//            default:
//                Parameter[] parameters = aopTool.getMethod(joinPoint).getParameters();
//                for(int i=0;i<args.length;i++){
//                    String key = parameters[i].getName();
//                    Object val = args[i];
//                    if(val != null) where = replace(where,key,val);
//                }
//                sql.append(" where ").append(where);
//        }
//        if(delete.print()) System.out.println(sql);
//        return sql.toString();
    }
}
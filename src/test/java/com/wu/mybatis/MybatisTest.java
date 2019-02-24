package com.wu.mybatis;

import com.wu.mybatis.del.entity.TblUserDO;
import com.wu.mybatis.del.mapper.TblUserMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;


import java.io.InputStream;

public class MybatisTest {

    public SqlSessionFactory getSqlSessionFactory() throws Exception {
        String resource = "com/wu/mybatis/del/resource/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        return new SqlSessionFactoryBuilder().build(inputStream);
    }

    /**
     * 1、根据配置文件(全局、sql映射)初始化Configuration对象
     *
     * 2、创建一个DefaultSqlSession对象
     *      里面包含Configuration对象和Executor(根据全局配置文件中DefaultExecutorType创建出对应的Executor)
     *
     * 3、DefaultSqlSession.getMapper（）,拿到Mapper接口对应的MapperProxy
     *      MapperProxy里面有DefaultSqlSession
     *
     * 4、执行增删改查方法
     *   1)调用DefaultSqlSession的增删改查(Executor)
     *   2)会创建一个StatementHandler对象(同时也会创建出PreparedStatementHandler和ResultSetHandler)
     *   3)调用StatementHandler预编译参数及设置参数
     *   4)调用StatementHandler的增删改查方法
     *   5)ResultSetHandler封装结果
     *
     * 注意：
     *  四大对象，每一个创建的时候都有一个interceptorChain.pluginAll(Handler);
     * @throws Exception
     */
    @Test
    public void test() throws Exception {
        //1、获取SqlSessionFactory对象
        SqlSessionFactory sqlSessionFactory = getSqlSessionFactory();
        //2、获取SqlSession对象
        SqlSession sqlSession = sqlSessionFactory.openSession();
        //3、获取接口的代理对象(MapperProxy)
        TblUserMapper mapper = sqlSession.getMapper(TblUserMapper.class);
        TblUserDO userDO = mapper.selectTbl(3);
        System.out.println(userDO.toString());
    }
}

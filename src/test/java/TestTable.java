//import junit.framework.Assert;
//import org.apache.tomcat.jdbc.pool.DataSource;
//import org.hibernate.SQLQuery;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.hibernate.dialect.Dialect;
//import org.hibernate.dialect.MySQLDialect;
//import org.hibernate.mapping.Column;
//import org.hibernate.mapping.PrimaryKey;
//import org.hibernate.mapping.Table;
//import org.hibernate.tool.hbm2ddl.DatabaseMetadata;
//import org.hibernate.tool.hbm2ddl.SchemaUpdateScript;
//import org.hibernate.tool.hbm2ddl.TableMetadata;
//import org.hibernate.transform.Transformers;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.sql.SQLException;
//import java.sql.Types;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
///**
// * Created by serv on 14-5-8.
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath*:applicationContext*.xml"})
//@Transactional
//public class TestTable {
//
//    final static String tablename = "TEST_MY_TABLE111";
//
//    @Autowired
//    protected DataSource dataSource;
//
//    @Autowired
//    protected SessionFactory sessionFactory;
//
//    protected DatabaseMetadata databaseMetadata;
//
//    protected Dialect dialect ;
//
//    private String SQL = null;
//
//    private List<SchemaUpdateScript> schemaUpdateScripts = new ArrayList<SchemaUpdateScript>() ;
//
//    @Before
//    public void init() throws SQLException {
//        dialect = new MySQLDialect();
//        databaseMetadata = new DatabaseMetadata(dataSource.getConnection(),dialect);
//    }
//
//    public void executeSQL(){
//        if(SQL==null){
//            String[] scripts = SchemaUpdateScript.toStringArray(schemaUpdateScripts);
//            for(String sql:scripts){
//                System.out.println(sql);
//                executeSQL(sql);
//            }
//        }else{
//            System.out.println(SQL);
//            executeSQL(SQL);
//
//        }
//    }
//
//    private void executeSQL(String sql){
//        sessionFactory.getCurrentSession().createSQLQuery(sql).executeUpdate();
//    }
//
//    public void traceTableInfo(String tablename){
//        TableMetadata tableMetadata = databaseMetadata.getTableMetadata(tablename, null, null, true);
//        Assert.assertNotNull(tableMetadata);
//    }
//
//
//    public Table createTable(){
//        Column column1 = new Column("id");
//        column1.setSqlType(dialect.getTypeName(Types.INTEGER, 100l, 0, 0));
//        column1.setNullable(false);
//        column1.setUnique(true);
//        Column column2 = new Column("name");
//        column2.setLength(500);
//        column2.setSqlType(dialect.getTypeName(Types.NVARCHAR,500l,0,0));
//
//
//
//        Table table = new Table(tablename);
//
//        table.addColumn(column1);
//        table.addColumn(column2);
//
//        PrimaryKey primaryKey = new PrimaryKey();
//        primaryKey.addColumn(column1);
//        primaryKey.setName("id");
//        primaryKey.setTable(table);
//
//        table.setPrimaryKey(primaryKey);
//        return table;
//    }
//
//
//    @Test
//    public void testAddTable(){
//
//        dropTable();
//
//        Table table = createTable();
//
//        SQL = table.sqlCreateString(dialect, null, null, null);
//
//        executeSQL();
//
//        traceTableInfo(tablename);
//    }
//
//
//    @Test
//    public void dropTable(){
//        SQL = new Table(tablename).sqlDropString(dialect,null,null);
//        executeSQL();
//    }
//
//    @Test
//    public void testAlterTable(){
//
//        //如果有表. 就获取表信息, 如果没有表. 就获取空.
//        TableMetadata tableMetadata = databaseMetadata.getTableMetadata(tablename, null,null, false);
//
//        Table table = createTable();
//
//        Column column = new Column("MEMO_TEST");
//        column.setSqlType(dialect.getTypeName(Types.DATE));
//        Column column1 = new Column("title");
//        column1.setSqlType(dialect.getTypeName(Types.NVARCHAR,200,0,0));
//
//        table.addColumn(column);
//        table.addColumn(column1);
//
//        Iterator<String> iterator = table.sqlAlterStrings(dialect, null, tableMetadata, null, null);
//
//        while(iterator.hasNext()){
//
//            schemaUpdateScripts.add(new SchemaUpdateScript(iterator.next(), true));
//
//        }
//
//
//        executeSQL();
//
//
//    }
//
//    @Test
//    public void testList(){
//
////        ((SessionFactoryImpl)sessionFactory)
//
//        Session session = sessionFactory.getCurrentSession();
//        SQLQuery sqlQuery = session.createSQLQuery("select * from test_my_table111");
////        sqlQuery.addEntity("test_my_table111",TestTable.class);
//        sqlQuery.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
//        List list = sqlQuery.list();
//        System.out.println(list.toString());
//    }
//
//    @Test
//    @Rollback(value = false)
//    public void testInsert(){
//        Session session = sessionFactory.getCurrentSession();
////        Map<String,Object> map = new HashMap<String, Object>();
////        map.put("id",4);
////        map.put("name","名称");
////        map.put("MEMO_TEST",new Date());
////        map.put("title","的飞为我");
////        session.saveOrUpdate("test",map);
//
//        session.createSQLQuery("insert into test(id,name,MEMO_test,title) values (34,'djf',now(),'title地方')").executeUpdate();
//    }
//
//
//}

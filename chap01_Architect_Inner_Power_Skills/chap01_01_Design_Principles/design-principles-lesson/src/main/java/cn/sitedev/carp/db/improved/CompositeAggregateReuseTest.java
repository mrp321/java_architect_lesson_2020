package cn.sitedev.carp.db.improved;

public class CompositeAggregateReuseTest {
    public static void main(String[] args) {
        ProductDao productDao = new ProductDao();
        productDao.setDbConnection(new MySQLConnection());
        productDao.addProduct();
    }
}

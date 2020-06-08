package cn.sitedev.crud.daosupport;

import cn.sitedev.crud.bean.Employee;
import cn.sitedev.crud.dao.EmployeeMapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public class EmployeeDaoImpl extends BaseDao implements EmployeeMapper {
    /**
     * 暂时只实现了这一个方法
     *
     * @param empId
     * @return
     */
    @Override
    public Employee selectByPrimaryKey(Integer empId) {
        Employee emp = (Employee) this.selectOne("EmployeeMapper.selectByPrimaryKey", empId);
        return emp;
    }

    @Override
    public int deleteByPrimaryKey(Integer empId) {
        return 0;
    }

    @Override
    public int insert(Employee record) {
        return 0;
    }

    @Override
    public int updateBatch(List<Employee> list) {
        return 0;
    }

    @Override
    public int insertSelective(Employee record) {
        return 0;
    }

    @Override
    public int batchInsert(List<Employee> list) {
        return 0;
    }

    @Override
    public int updateByPrimaryKeySelective(Employee record) {
        return 0;
    }

    @Override
    public int updateByPrimaryKey(Employee record) {
        return 0;
    }

    @Override
    public void deleteByList(List<Integer> ids) {

    }

    @Override
    public long countByMap(HashMap<String, String> map) {
        return 0;
    }

    @Override
    public List<Employee> selectByMap(HashMap<String, Object> map) {
        return null;
    }
}

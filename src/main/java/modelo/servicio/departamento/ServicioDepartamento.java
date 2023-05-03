package modelo.servicio.departamento;

import modelo.Departamento;
import modelo.dao.departamento.DepartamentoNeoDatisDao;
import modelo.dao.departamento.IDepartamentoDao;
import modelo.exceptions.DuplicateInstanceException;

import java.util.List;

public class ServicioDepartamento implements IServicioDepartamento{

    private IDepartamentoDao departamentoDao;

    public ServicioDepartamento(){
        departamentoDao=new DepartamentoNeoDatisDao();
    }
    @Override
    public long create(Departamento dept) throws DuplicateInstanceException {
        return departamentoDao.create(dept);
    }

    @Override
    public boolean delete(Departamento dept) {
        return departamentoDao.delete(dept);
    }

    @Override
    public boolean update(Departamento dept) {
        return departamentoDao.update(dept);
    }

    @Override
    public List<Departamento> findAll() {
        return departamentoDao.findAll();
    }

    @Override
    public boolean exists(Integer deptno) {
        return departamentoDao.exists(deptno);
    }
}

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
        long nuevo=departamentoDao.create(dept);
        if(nuevo==-2){
            throw new DuplicateInstanceException("Ya existe un departamento con ese id. No se ha podido crear.",dept.getDeptno(),Departamento.class.getName());
        }

        return nuevo;
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

package modelo.servicio.departamento;

import modelo.Departamento;
import modelo.exceptions.DuplicateInstanceException;

import java.util.List;

public interface IServicioDepartamento {
    public long create(Departamento dept)throws DuplicateInstanceException;
    public boolean delete(Departamento dept);
    public boolean update(Departamento dept);
    public List<Departamento> findAll();
    public boolean exists(Integer deptno) ;


}

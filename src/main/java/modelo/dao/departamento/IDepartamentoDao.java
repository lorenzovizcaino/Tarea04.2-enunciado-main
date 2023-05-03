package modelo.dao.departamento;

import modelo.Departamento;
import modelo.dao.IGenericDao;

public interface IDepartamentoDao extends IGenericDao<Departamento> {
    boolean exists(Integer dept);
}

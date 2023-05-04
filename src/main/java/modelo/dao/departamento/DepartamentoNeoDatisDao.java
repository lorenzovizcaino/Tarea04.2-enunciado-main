package modelo.dao.departamento;

import modelo.Departamento;
import modelo.dao.AbstractGenericDao;
import modelo.exceptions.DuplicateInstanceException;
import modelo.exceptions.InstanceNotFoundException;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBRuntimeException;
import org.neodatis.odb.OID;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.oid.OIDFactory;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import util.ConnectionFactory;
import util.Utils;

import java.util.List;

public class DepartamentoNeoDatisDao extends AbstractGenericDao<Departamento> implements IDepartamentoDao {
    private ODB dataSource;

    public DepartamentoNeoDatisDao() {
        this.dataSource = ConnectionFactory.getConnection();
    }
    @Override
    public long create(Departamento entity) {
        OID oid = null;
        long oidlong = -1 ;
        try {
            if(exists(entity.getDeptno())){
                oidlong=-2;
            }else{
                oid = this.dataSource.store(entity);
                this.dataSource.commit();
                System.out.println("Creado un objeto "+ getEntityClass()+ " con oid: " + oid.getObjectId());
            }



        } catch (Exception ex) {

            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());
            this.dataSource.rollback();
            oid = null;
        }
        if (oid != null) {
            oidlong = oid.getObjectId();
        }
        return oidlong;
    }

    @Override
    public Departamento read(long id) throws InstanceNotFoundException {
        Departamento departamento = null;
        try {
            OID oid = OIDFactory.buildObjectOID(id);
            departamento = (Departamento) this.dataSource.getObjectFromId(oid);
        } catch (ODBRuntimeException ex) {

            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

            throw new InstanceNotFoundException(id, getEntityClass());
        } catch (Exception ex) {

            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());

        }
        return departamento;
    }

    @Override
    public boolean update(Departamento entity) {
        boolean exito = false;
        try {
            this.dataSource.store(entity);
            this.dataSource.commit();
            exito = true;
        } catch (Exception ex) {
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());
            this.dataSource.rollback();

        }
        return exito;
    }

    @Override
    public boolean delete(Departamento entity) {
        boolean exito = false;
        try {
            OID oid = this.dataSource.delete(entity);
            System.out.println("El oid del objeto eliminado es: " + oid.getObjectId());
            this.dataSource.commit();
            exito = true;
        } catch (Exception ex) {
            System.err.println("Ha ocurrido una excepción: " + ex.getMessage());
            this.dataSource.rollback();

        }
        return exito;
    }

    @Override
    public List<Departamento> findAll() {
        CriteriaQuery query = new CriteriaQuery(Departamento.class);
        IQuery iquery = query.orderByAsc("deptno");
        Objects<Departamento> departamentos = dataSource.getObjects(iquery);
        return Utils.toList(departamentos);
    }

    @Override
    public boolean exists(Integer dept) {
        CriteriaQuery query = new CriteriaQuery(Departamento.class, Where.equal("deptno", dept));
        Objects<Departamento> empleados = dataSource.getObjects(query);
        return (empleados.size()==1);
    }
}

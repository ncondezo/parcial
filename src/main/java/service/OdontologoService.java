package service;

import dao.IDao;
import dao.OdontologoDaoH2;
import model.Odontologo;

import java.util.List;
import java.util.Optional;

public class OdontologoService {

    private IDao<Odontologo> odontologoDao;

    public void guardarOdontologo(Odontologo e){
         odontologoDao.guardar(e);
    }
    public void eliminarOdontologo(long id){
        odontologoDao.eliminar(id);
    }
    public Optional<Odontologo> buscarOdontologo(long id){

        var odontologo=odontologoDao.buscar(id);

        if(odontologo.isPresent()){
            var newOdontologo=odontologo.get();
            return Optional.of(new Odontologo(
                    newOdontologo.getId(),
                    newOdontologo.getApellido(),
                    newOdontologo.getNombre(),
                    newOdontologo.getMatricula()
            ));


        }

        return Optional.empty();
    }
    public List<Odontologo> listar(){
        return odontologoDao.listar();
    }


    public void modificar(Odontologo o) {
        odontologoDao.modificar(o);

    }

    public IDao<Odontologo> getOdontologoDao() {
        return odontologoDao;
    }

    public void setOdontologoDao(IDao<Odontologo> odontologoDao) {
        this.odontologoDao = odontologoDao;
    }
}


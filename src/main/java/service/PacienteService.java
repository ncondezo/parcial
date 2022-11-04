package service;

import dao.IDao;
import dao.PacienteDao;
import model.Odontologo;
import model.Paciente;

import java.util.List;
import java.util.Optional;

public class PacienteService {

    private IDao<Paciente> pacienteDao;

    public void guardarOdontologo(Paciente p){
        pacienteDao.guardar(p);
    }
    public void eliminarPaciente(long id){
        pacienteDao.eliminar(id);
    }
    public Optional<Paciente> buscarPaciente(long id){

        var paciente=pacienteDao.buscar(id);

        if(paciente.isPresent()){
            var newPaciente=paciente.get();
            return Optional.of(new Paciente(
                    newPaciente.getId(),
                    newPaciente.getApellido(),
                    newPaciente.getNombre(),
                    newPaciente.getDni(),
                    newPaciente.getDomicilio(),
                    newPaciente.getAlta()
            ));


        }

        return Optional.empty();
    }
    public List<Paciente> listar(){
        return pacienteDao.listar();
    }


    public void modificar(Paciente p) {
        pacienteDao.modificar(p);

    }

    public IDao<Paciente> getPacienteDao() {
        return pacienteDao;
    }

    public void setPacienteDao(IDao<Paciente> pacienteDao) {
        this.pacienteDao = pacienteDao;
    }
}


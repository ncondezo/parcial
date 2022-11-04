package dao;

import model.Odontologo;
import model.Paciente;
import org.apache.log4j.Logger;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PacienteDao implements IDao<Paciente> {

    private static final Logger logger = Logger.getLogger(OdontologoDaoH2.class);

    private final static String SELECT = "SELECT * FROM PACIENTES";
    private final static String INSERT = "INSERT INTO PACIENTES(ID,APELLIDO ,NOMBRE,DNI, DIRECCION, ALTA) VALUES(?,?,?,?,?,?)";

    private final static String UPDATE = "UPDATE PACIENTES set ID = ?, APELLIDO = ?, NOMBRE = ?, DNI = ?, DIRECCION = ?, ALTA = ? where id = ?,";


    private final static String DB_JDBC_DRIVER = "org.h2.Driver";



    @Override
    public List<Paciente> listar() {

        ArrayList<Paciente> pacientes=new ArrayList();

        try(var connection= H2Connect.getConnection()){

            var statement=connection.prepareStatement("SELECT * FROM PACIENTES");

            var result=statement.executeQuery();

            while (result.next()){
                long idPaciente=result.getLong(1);
                String apellido=result.getString(2);
                String nombre= result.getString(3);
                int dni=result.getInt(4);
                String direccion=result.getString(5);
                LocalDate alta=result.getDate(6).toLocalDate();
                var paciente=new Paciente(idPaciente,apellido,nombre,dni,direccion,alta);
                pacientes.add(paciente);



            }statement.close();

        }
        catch(SQLException e){

        }

        return pacientes;

    }

    @Override
    public void guardar(Paciente paciente) {

        try(var connection= H2Connect.getConnection()){

            var statement=connection.prepareStatement(INSERT);
            statement.setLong(1,paciente.getId());
            statement.setString(2,paciente.getApellido());
            statement.setString(3,paciente.getNombre());
            statement.setInt(4,paciente.getDni());
            statement.setString(5,paciente.getDomicilio());
            statement.setDate(6,java.sql.Date.valueOf(paciente.getAlta()));
            statement.executeUpdate();
            logger.debug("Paciente guardado.");
            //statement.close();


        }



        catch(SQLException e){

        }

        //return odontologo;
    }

    @Override
    public void eliminar(long id) {

        try(var connection= H2Connect.getConnection()){

            var statement=connection.prepareStatement("DELETE FROM PACIENTES WHERE ID=?");
            statement.setLong(1,id);

            statement.executeUpdate();
            logger.debug("Paciente eliminado.");
            statement.close();

        }
        catch(SQLException e){
            logger.error(e);
            throw new RuntimeException(e);

        }

    }

    @Override
    public Optional<Paciente> buscar(long id) {

        try(var connection= H2Connect.getConnection()){

            var statement=connection.prepareStatement(SELECT);
            statement.setLong(1,id);

            var result=statement.executeQuery();

            while (result.next()){
                long idPaciente=result.getLong(1);
                String apellido=result.getString(2);
                String nombre= result.getString(3);
                int dni=result.getInt(4);
                String direccion=result.getString(5);
                LocalDate alta=(result.getDate(6).toLocalDate());

                logger.debug("Paciente encontrado");
                return Optional.of(new Paciente(idPaciente,apellido,nombre,dni,direccion,alta));


            }//statement.close();

        }
        catch(SQLException e){
            logger.error(e);
            //throw new RuntimeException(e);
        }
        logger.debug("Profesional no encontrado.");
        return Optional.empty();
    }


    @Override
    public void modificar (Paciente p) {
        try(var connection=H2Connect.getConnection()){
            var update=connection.prepareStatement(UPDATE);
            update.setLong(1,p.getId());
            update.setString(2,p.getApellido());
            update.setString(3, p.getNombre());
            update.setInt(4, p.getDni());
            update.setString(5,p.getDomicilio());
            update.setDate(6,java.sql.Date.valueOf(p.getAlta()));;
            update.executeUpdate();
        }catch (SQLException e){
            logger.error(e);
        }

    }
}


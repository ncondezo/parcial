package dao;

import model.Odontologo;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;

public class OdontologoDaoH2 implements IDao<Odontologo>{

    private static final Logger logger = Logger.getLogger(OdontologoDaoH2.class);

    private final static String SELECT_ID = "SELECT * FROM ODONTOLOGOS where id = ?";
    private final static String INSERT = "INSERT INTO ODONTOLOGOS(ID,APELLIDO ,NOMBRE,MATRICULA) VALUES(?,?,?,?)";
    private final static String UPDATE = "UPDATE ODONTOLOGOS set ID = ?, APELLIDO = ?, NOMBRE = ?, MATRICULA = ? where id = ?";

    private final static String DB_JDBC_DRIVER = "org.h2.Driver";
    @Override
    public List<Odontologo> listar() {

        ArrayList<Odontologo> odontologos=new ArrayList();

        try(var connection= H2Connect.getConnection()){

            var statement=connection.prepareStatement("SELECT * FROM ODONTOLOGOS");

            var result=statement.executeQuery();

            while (result.next()){
                long idOdontologo=result.getLong("id");
                String apellido=result.getString("apellido");
                String nombre= result.getString("nombre");
                String matricula=result.getString("matricula");
                var odontologo=new Odontologo(idOdontologo,apellido,nombre,matricula);
                odontologos.add(odontologo);

            }statement.close();

        }
        catch(SQLException e){

        }

        return odontologos;

    }

    @Override
    public void guardar(Odontologo odontologo) {

        try(var connection= H2Connect.getConnection()){

            var statement=connection.prepareStatement(INSERT);
            statement.setLong(1,odontologo.getId());
            statement.setString(2,odontologo.getApellido());
            statement.setString(3,odontologo.getNombre());
            statement.setString(4,odontologo.getMatricula());
            statement.execute();
            logger.debug("Odontólogo guardado.");
            //statement.close();


        }
        catch(SQLException e){

        }
        //return odontologo;
    }

    @Override
    public void eliminar(long id) {

        try(var connection= H2Connect.getConnection()){

            var statement=connection.prepareStatement("DELETE FROM ODONTOLOGOS WHERE ID=?");
            statement.setLong(1,id);

            statement.executeUpdate();
            logger.debug("Profesional eliminado.");
            statement.close();

        }
        catch(SQLException e){
            logger.error(e);
            throw new RuntimeException(e);

        }

    }

    @Override
    public Optional<Odontologo> buscar(long id) {

        try(var connection= H2Connect.getConnection()){

            var statement=connection.prepareStatement(SELECT_ID);
            statement.setLong(1,id);

            var result=statement.executeQuery();

            while (result.next()){
                connection.close();
                long idOdontologo=result.getLong(1);
                String apellido=result.getString(2);
                String nombre= result.getString(3);
                String matricula=result.getString(4);
                logger.debug("Odontólogo encontrado");
                return Optional.of(new Odontologo(idOdontologo,apellido,nombre,matricula));


            }

        }
        catch(SQLException e){
            logger.error(e);
            //throw new RuntimeException(e);
        }
        logger.debug("Profesional no encontrado.");
        return Optional.empty();

    }

    @Override
    public void modificar(Odontologo o) {
        try(var connection=H2Connect.getConnection()){
            var update=connection.prepareStatement(UPDATE);
            update.setLong(1,o.getId());
            update.setString(2,o.getApellido());
            update.setString(3, o.getNombre());
            update.setString(4, o.getMatricula());
            update.executeUpdate();
        }catch (SQLException e){
            logger.error(e);
        }

    }
}


import dao.OdontologoDaoH2;
import model.Odontologo;
import service.OdontologoService;

public class Test {

    public static void main(String[] args) {

        var odontologo=new Odontologo(1l,"A","Javier","345");
        var oService=new OdontologoService();
        oService.setOdontologoDao(new OdontologoDaoH2());
        oService.guardarOdontologo(odontologo);
        System.out.println(oService.buscarOdontologo(1l));
    }
}

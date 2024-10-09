package ejercicio1basesrelacionales1;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Clase main donde ejecutamos el programa
 * @author cristian
 * @version 1.0
 */
public class Main {

    public static void main(String[] args) {

        //instanciamos la clase con los metodos
        OperationsCrud oc = new OperationsCrud();

        //-----------------bloque de inserccion----------------------//
        
        //metemos todo en un try-catch
        try {
            //este bloque está diseñado para transformar los datos de tipo Date
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date di;
            java.util.Date di2;
            java.util.Date di3;
            di = format.parse("27/12/2020");
            di2 = format.parse("3/7/2020");
            di3 = format.parse("6/4/2020");
            java.sql.Date df = new java.sql.Date(di3.getTime());
            
            //llamamos al metodo que inserta productos en la base de datos
            oc.InsireProducto("p1", "parafusos", 3, df);
            oc.InsireProducto("p2", "cravos", 4, df);
             oc.InsireProducto("p3", "tachas", 6, df);
        }catch(ParseException e){
            System.out.println("Ups, ha ocurrido un error con la fecha");
        }

        
        
        //------------------------------Bloque de lectura de datos---------------//
        
        //llamamos a los metodos que listan productos 
        oc.listaProductos();
        oc.listaProductoPorCodigo("p1");
        
        //-----------------------------Bloque de actualizacion de datos-----------------//
        
        //llamamos al metodo que actualiza produtos
        oc.actualizaPre(8, "p2");
        
        //---------------------------Bloque de borrado-----------------------//
        
        //llamamos al metodo que elimina produtos
        oc.eliminaProduto("p3");

    }
}

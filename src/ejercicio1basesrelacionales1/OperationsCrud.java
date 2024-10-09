package ejercicio1basesrelacionales1;

import java.sql.*;

/**
 * Clase de las operaciones crud de la base de datos
 * @author cristian
 * @version 1.0
 */
public class OperationsCrud {

    /**
     * Metodo que inserta registros en nuestra base de datos
     * @param codigo el codigo del producto
     * @param descricion la descripcion del producto
     * @param prezo el precio del producto
     * @param datac la fecha de caducidad
     */
    public void InsireProducto(String codigo, String descricion, float prezo, Date datac) {

        //preparamos la consulta
        String consulta = "insert into produtos (codigo, descricion, prezo, datac) values(?, ?, ?, ?)";

        //lo metemos todo en un try-catch
        try {
            
            /**
             * Pasos para realizar la conexion:
             * 1. Creamos un objeto de tipo conexion que será igual a un metodo que nos devuelve una conexion a la base de datos
             * 2. La consulra tiene parametros, así que instanciamos el objeto de tipo PreparedStatemente
             * 3. llamamos a los metodos que set para insertar registros
             * 4. los numeros entre parentesis en los set son las posiciones de los interrogantes
             * 5. comprobamos la inserccion con un if
             */
            Connection con = EstablecerConexion.conectar();
            PreparedStatement pst = con.prepareStatement(consulta);

            pst.setString(1, codigo);
            pst.setString(2, descricion);
            pst.setFloat(3, prezo);
            pst.setDate(4, new java.sql.Date(datac.getTime()));

            int insercciones = pst.executeUpdate();

            if (insercciones > 0) {
                System.out.println("Insercciones hechas");
            } else {
                System.out.println("Ups, ha ocurrido un error a la hora de insertar el producto");
            }

        } catch (SQLException e) {
            System.out.println("Ups, no se ha podido establecer conexion a la base de datos");
            e.printStackTrace();
        }

    }

    /**
     * Metodo para listar los elementos de una base de datos
     */
    public void listaProductos() {

        //preparamos la consulta
        String consulta = "select * from produtos";
        try {
            /**
             * Pasos para realizar la conexion:
             * 1. Creamos un objeto de tipo conexion que será igual a un metodo que nos devuelve una conexion a la base de datos
             * 2. La consulta en ese caso, no tiene parametros, por lo tanto será de tipo Statement
             * 3. Instanciaimos un objeto de tipo ResultSet para recoger el resultado de la consulta que le pasamos por parametro al metodo executeQuery()
             * 4. Recogemos los valores en el bucle while con los metodos de la clase ResultSey
             * 5. imprimimos
             */
            Connection con = EstablecerConexion.conectar();
            Statement pst = con.createStatement();
            ResultSet rs = pst.executeQuery(consulta);

            //mientras haya consultas, leemos los elementos
            while (rs.next()) {

                String codigo = rs.getString("codigo");
                String descricion = rs.getString("descricion");
                float prezo = rs.getFloat("prezo");
                Date datac = rs.getDate("datac");
                System.out.println("Codigo: " + codigo + " descricion: " + descricion + " precio: " + prezo + " Fecha: " + datac + "\n");
            }

        } catch (SQLException e) {
            System.out.println("ups, ha ocurrido un error al leer el registro");
            e.printStackTrace();
        }

    }

    /**
     * metodo para leer un registro de la base de datos según el codigo dado
     * @param codigo del producto
     */
    public void listaProductoPorCodigo(String codigo) {

        //damos una consulta
        String consulta = "select * from produtos where codigo = ?";
        try {
            
            /**
             * Pasos para leer el regsitro:
             * 1. establecemos la conexion con la base de datos
             * 2. Preparamos la consulta de tipo PreparedStatement ya que tiene parametros
             * 3. introducimos el codigo con el setString
             * 4. recogemos el resultado en un resultSet
             */
            Connection con = EstablecerConexion.conectar();
            PreparedStatement st = con.prepareStatement(consulta);
            st.setString(1, codigo);
            ResultSet rs = st.executeQuery();

            //mientras haya elementos, leemos la consulta
            while (rs.next()) {

                String dameCodigo = rs.getString("codigo");
                String descricion = rs.getString("descricion");
                float prezo = rs.getFloat("prezo");
                Date datac = rs.getDate("datac");

                System.out.println("Codigo: " + dameCodigo + " descricion: " + descricion + " precio: " + prezo + " Fecha: " + datac + "\n");

            }

        } catch (SQLException e) {
            System.out.println("Ups, no se ha podido leer el registro");
            e.printStackTrace();
        }

    }

    /**
     * Metodo para actualizar los elementos de una base de datos
     * @param prezo el precio del producto
     * @param codigo el codigo del producto
     */
    public void actualizaPre(float prezo, String codigo) {
        
        //preparamos la consulta
        String consulta = "update produtos set prezo = ? where codigo = ?";
        
        try{
            
            /**
             * Pasos para actualizar el registro:
             * 1. Establecemos conexion con la base de datos
             * 2. preparamos el PreparedStatement con la consulta
             * 3. añadimos el nuevo precio segun el codigo
             * 4. comprobamos que se actualizo el producto
             */
            Connection con = EstablecerConexion.conectar();
            PreparedStatement pst = con.prepareStatement(consulta);
            pst.setFloat(1, prezo);
            pst.setString(2, codigo);
            
            int actualizacion = pst.executeUpdate();
            
            //si el resultado es mayor que 0, funciono la actualizacion
            if(actualizacion > 0){
                System.out.println("Actualizacion hecha con éxito");
            }
        }catch(SQLException e){
            System.out.println("ups, no se ha podido actualizar el producto");
        }

    }

    /**
     * Metodo para borrar un elemento de la base de datos
     * @param codigo del producto
     */
    public void eliminaProduto(String codigo) {

        //Preparamos la consulta
        String consulta = "delete from produtos where codigo = ?";
        
        try{
            /**
             * Pasos para realizar el borrado:
             * 1. Conexion a la base de datos
             * 2. Preparar el objeto de tipo PreparesStatement, ya que la consulta va con parametros
             * 3. Introducimos el codigo
             * 4. comprobamos si se elimino
             */
            Connection con = EstablecerConexion.conectar();
            PreparedStatement pst = con.prepareStatement(consulta);
            
            pst.setString(1, codigo);
            int eliminacion = pst.executeUpdate();
            
            if(eliminacion > 0){
                System.out.println("Borrado con éxito");
            }
        }catch(SQLException e){
            System.out.println("Ups, no se ha podido borrar el registro");
        }
        
    }

}

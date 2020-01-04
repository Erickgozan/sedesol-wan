package com.proyectosedesol.modelo;

import com.proyectosedesol.entidades.Estancia;
import com.proyectosedesol.entidades.EstanciaCd;
import com.proyectosedesol.entidades.EstanciaAm;
import com.proyectosedesol.entidades.EstanciaCr;
import com.proyectosedesol.entidades.RevisarAm;
import com.proyectosedesol.entidades.RevisarCd;
import com.proyectosedesol.entidades.RevisarCr;
import com.proyectosedesol.entidades.Usuarios;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

public class ModeloProcesos {

    private DataSource origenDatos;

    public ModeloProcesos(DataSource origenDatos) {
        this.origenDatos = origenDatos;
    }

    /*METODOS PARA LOS PROCESOS*/
    public Estancia getDatosEstacia(int id_estancia) throws SQLException {

        Connection conexion = null;
        PreparedStatement statement = null;
        Estancia datosEstancia = null;

        try {
            conexion = origenDatos.getConnection();

            String sql = "SELECT * FROM estancia WHERE id_estancia = ? ";
            statement = conexion.prepareStatement(sql);
            statement.setInt(1, id_estancia);
            ResultSet rs = statement.executeQuery();

            if (rs.next()) {

                String nombre_estancia = rs.getString("Nombre_estancia");
                String nombre_responseble = rs.getString("Nombre_responsable");
                String direccion = rs.getString("Direccion");
                String estado = rs.getString("Estado");
                String fecha_inicio = rs.getString("Fecha_inicio");
                Date fecha_fin = rs.getDate("Fecha_fin");
                String ruta_doc = rs.getString("Ruta_doc");
                String estatus_domicilio = rs.getString("estatus_domicilio");
                String estatus_responsable = rs.getString("estatus_responsable");
                /*Documentos cambio de domicilio*/
                String ruta_docD1 = rs.getString("ruta_docD1");
                String ruta_docD2 = rs.getString("ruta_docD2");
                String ruta_docD3 = rs.getString("ruta_docD3");
                String ruta_docD4 = rs.getString("ruta_docD4");
                String ruta_docD5 = rs.getString("ruta_docD5");
                String ruta_docD6 = rs.getString("ruta_docD6");
                String ruta_docR1 = rs.getString("ruta_docR1");
                String ruta_docR2 = rs.getString("ruta_docR2");
                String ruta_docR3 = rs.getString("ruta_docR3");

                datosEstancia = new Estancia(id_estancia, nombre_estancia, nombre_responseble, direccion, estado, fecha_inicio, fecha_fin, ruta_doc);
                /*Establecer el estatus de cambio de domicilio*/
                datosEstancia.setEstatus_domicilio(estatus_domicilio);
                /*Establecer la ruta de los documentos de cambio de domicilio*/
                datosEstancia.setRuta_docD1(ruta_docD1);
                datosEstancia.setRuta_docD2(ruta_docD2);
                datosEstancia.setRuta_docD3(ruta_docD3);
                datosEstancia.setRuta_docD4(ruta_docD4);
                datosEstancia.setRuta_docD5(ruta_docD5);
                datosEstancia.setRuta_docD6(ruta_docD6);
                /*Establecer el estatus de cambio de responsable*/
                datosEstancia.setEstatus_responsable(estatus_responsable);
                /*Establecer la ruta de los documentos de cambio de responsable*/
                datosEstancia.setRuta_docR1(ruta_docR1);
                datosEstancia.setRuta_docR2(ruta_docR2);
                datosEstancia.setRuta_docR3(ruta_docR3);
            }

        } catch (SQLException e) {
            System.out.println("Error en el metodo insertarProductos " + e.getMessage());
        } finally {
            conexion.close();
            statement.close();
        }

        return datosEstancia;
    }

    public RevisarAm getDatosRevisar(int id_revision) throws SQLException {

        RevisarAm revDoc = null;
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs;
        try {
            conexion = origenDatos.getConnection();
            String sql = "SELECT * FROM revisar_am WHERE id_revision=?";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id_revision);
            rs = ps.executeQuery();
            if (rs.next()) {

                String Nombre_estancia = rs.getString("r_nombre_estancia");
                String Nombre_responsable = rs.getString("r_nombre_responsable");
                String Direccion = rs.getString("r_direccion");
                String Estado = rs.getString("r_estado");
                String Fecha_inicio = rs.getString("r_fecha_inicio");
                String Fecha_fin = rs.getString("r_fecha_fin");
                String comentarios = rs.getString("comentarios");
                String estatus = rs.getString("estatus");
                String usuario_rev = rs.getString("usuario_rev");

                revDoc = new RevisarAm(id_revision, id_revision, Nombre_estancia, Nombre_responsable, Direccion, Estado, Fecha_inicio, Fecha_fin,comentarios,estatus, usuario_rev);
            }
        } catch (SQLException e) {

            System.out.println("Error en el metodo getDatosRevisar " + e.getLocalizedMessage());
        } finally {

            conexion.close();
            ps.close();
        }

        return revDoc;
    }

    public void actualizarDatosEstancia(Estancia datos) throws SQLException {

        Connection conexion = null;
        PreparedStatement pStatement = null;

        try {
            conexion = origenDatos.getConnection();
            String sql = "UPDATE estancia SET Fecha_inicio=?, Ruta_doc=? WHERE Id_estancia = ?";

            pStatement = conexion.prepareStatement(sql);

            pStatement.setString(1, datos.getFecha_inicio());
            pStatement.setString(2, datos.getRuta_doc());
            //WHERE ID...
            pStatement.setInt(3, datos.getId_estancia());

            pStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error en el metodo insertarProductos " + e.getMessage());
        } finally {
            conexion.close();
            pStatement.close();
        }
    }

    public void actualizarDatosRev(RevisarAm revision) throws SQLException {

        Connection conexion = null;
        PreparedStatement ps = null;
        try {
            conexion = origenDatos.getConnection();
            String sql = "UPDATE revisar_am SET r_nombre_estancia=?, r_nombre_responsable=?, r_direccion=?, r_estado=?, r_fecha_inicio=?, r_fecha_fin=?, comentarios=?, estatus=?, usuario_rev=? WHERE id_revision=?";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, revision.getR_nombre_estancia());
            ps.setString(2, revision.getR_nombre_responsable());
            ps.setString(3, revision.getR_direccion());
            ps.setString(4, revision.getR_estado());
            ps.setString(5, revision.getR_fecha_inicio());
            ps.setString(6, revision.getR_fecha_fin());
            ps.setString(7, revision.getComentarios());
            ps.setString(8, revision.getEstatus());
            ps.setString(9, revision.getUsuario_rev());

            ps.setInt(10, revision.getId_revision());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error en el metodo -> actualizarDatosRev " + e.getLocalizedMessage());
        } finally {
            conexion.close();
            ps.close();
        }
    }

    public List<Object> listarDatos(int id_rev_list, String estado) throws SQLException {

        List<Object> listado = new ArrayList<>();
        Object[] objetos = new Object[19];
        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;
        try {
            conexion = origenDatos.getConnection();
            if (estado.equals("OFICINAS CENTRALES")) {
                sql = "SELECT * FROM estancia INNER JOIN revisar_am ON estancia.Id_estancia = revisar_am.id_subir_doc WHERE revisar_am.id_subir_doc = ?";
                ps = conexion.prepareStatement(sql);
                ps.setInt(1, id_rev_list);
            } else {
                sql = "SELECT * FROM estancia INNER JOIN revisar_am ON estancia.Id_estancia = revisar_am.id_subir_doc WHERE revisar_am.id_subir_doc = ? AND estancia.Estado = ?";
                ps = conexion.prepareStatement(sql);
                ps.setInt(1, id_rev_list);
                ps.setString(2, estado);
            }
            rs = ps.executeQuery();
            if (rs.next()) {
                /*Estancia*/
                objetos[0] = rs.getInt("Id_estancia");
                objetos[1] = rs.getString("Nombre_estancia");
                objetos[2] = rs.getString("Nombre_responsable");
                objetos[3] = rs.getString("Direccion");
                objetos[4] = rs.getString("Estado");
                objetos[5] = rs.getString("Fecha_inicio");
                objetos[6] = rs.getDate("Fecha_fin");
                objetos[7] = rs.getString("Ruta_doc");
                /*RevisarAm*/
                objetos[8] = rs.getInt("id_revision");
                objetos[9] = rs.getInt("id_estancia");
                objetos[10] = rs.getString("r_nombre_estancia");
                objetos[11] = rs.getString("r_nombre_responsable");
                objetos[12] = rs.getString("r_direccion");
                objetos[13] = rs.getString("r_estado");
                objetos[14] = rs.getString("r_fecha_inicio");
                objetos[15] = rs.getString("r_fecha_fin");
                objetos[16] = rs.getString("comentarios");
                objetos[17] = rs.getString("estatus");
                objetos[18] = rs.getString("usuario_rev");

                Estancia datosDoc = new Estancia((int) objetos[0], (String) objetos[1], (String) objetos[2], (String) objetos[3], (String) objetos[4], (String) objetos[5], (Date) objetos[6], (String) objetos[7]);
                RevisarAm revDoc = new RevisarAm((int) objetos[8], (int) objetos[9], (String) objetos[10], (String) objetos[11], (String) objetos[12], (String) objetos[13], (String) objetos[14], (String) objetos[15],(String) objetos[16],(String) objetos[17] ,(String) objetos[18]);

                listado.add(datosDoc);
                listado.add(revDoc);

            }
        } catch (SQLException e) {

            System.out.println("Error en el metodo getDatosRevisar " + e.getLocalizedMessage());
        } finally {

            conexion.close();
            ps.close();
        }

        return listado;
    }

    public List<EstanciaAm> getListadoJoin(int pagina, int finaliza, String estado) throws SQLException {

        List<EstanciaAm> listDoc = new ArrayList<>();

        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs;

        String sql;
        try {
            conexion = origenDatos.getConnection();
            if (estado.equals("OFICINAS CENTRALES")) {
                sql = "SELECT * FROM estancia INNER JOIN revisar_am ON estancia.Id_estancia = revisar_am.id_subir_doc LIMIT " + pagina + ", " + finaliza + "";
                ps = conexion.prepareStatement(sql);
            } else {
                sql = "SELECT * FROM estancia INNER JOIN revisar_am ON estancia.Id_estancia = revisar_am.id_subir_doc  WHERE estancia.Estado = ? LIMIT " + pagina + ", " + finaliza + "";
                ps = conexion.prepareStatement(sql);
                ps.setString(1, estado);
            }

            rs = ps.executeQuery();
            while (rs.next()) {
                int idEstancia = rs.getInt("Id_estancia");
                String listaNombreEstancia = rs.getString("Nombre_estancia");
                String listaNombreResponsable = rs.getString("Nombre_responsable");
                String listaDireccion = rs.getString("Direccion");
                String listaEstado = rs.getString("Estado");
                String listaFechaInicio = rs.getString("Fecha_inicio");
                Date listaFechaFin = rs.getDate("Fecha_fin");
                String listaRutaDoc = rs.getString("Ruta_doc");
                int idRevision = rs.getInt("id_revision");
                int idSubirDoc = rs.getInt("id_revision");
                String listaRNombreEstancia = rs.getString("r_nombre_estancia");
                String listaRNombreResponsable = rs.getString("r_nombre_responsable");
                String listaRDireccion = rs.getString("r_direccion");
                String listaREstado = rs.getString("r_estado");
                String listaRFechaInicio = rs.getString("r_fecha_inicio");
                String listaRFechaFin = rs.getString("r_fecha_fin");
                String comentarios = rs.getString("comentarios");
                String estatus = rs.getString("estatus");
                String listaUsuarioRev = rs.getString("usuario_rev");

                EstanciaAm listado = new EstanciaAm(idEstancia, listaNombreEstancia, listaNombreResponsable, listaDireccion, listaEstado, listaFechaInicio, listaFechaFin, listaRutaDoc, idRevision, idSubirDoc, listaRNombreEstancia, listaRNombreResponsable, listaRDireccion, listaREstado, listaRFechaInicio, listaRFechaFin,comentarios,estatus, listaUsuarioRev);
                listDoc.add(listado);
            }
        } catch (SQLException e) {
            System.out.println("Error en el metodo join" + e.getLocalizedMessage());
        } finally {
            conexion.close();
            ps.close();
        }

        return listDoc;
    }

    public int getNumRegistros(String estado) throws SQLException {

        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs;
        int numRegistros = 0;
        String sql;
        try {
            conexion = origenDatos.getConnection();
            if (estado.equals("OFICINAS CENTRALES")) {
                sql = "SELECT * FROM estancia ";
                ps = conexion.prepareStatement(sql);
            } else {
                sql = "SELECT * FROM estancia WHERE Estado = ? ";
                ps = conexion.prepareStatement(sql);
                ps.setString(1, estado);
            }
            rs = ps.executeQuery();
            while (rs.next()) {

                numRegistros++;
            }
        } catch (SQLException e) {
            System.out.println("Error " + e.getLocalizedMessage());

        } finally {
            conexion.close();
            ps.close();
        }

        return numRegistros;
    }

    /*METODOS CAMBIO DE DOMICILIO*/
    public void insertarDatosCD(Estancia estancia) throws SQLException {

        Connection conexion = null;
        PreparedStatement ps = null;
        String sql;
        try {
            conexion = origenDatos.getConnection();
            sql = "UPDATE estancia SET direccion=?, estatus_domicilio=?,ruta_docD1=?,ruta_docD2=?,ruta_docD3=?,ruta_docD4=?,ruta_docD5=?,ruta_docD6=? WHERE id_estancia = ?";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, estancia.getDireccion());
            ps.setString(2, estancia.getEstatus_domicilio());
            ps.setString(3, estancia.getRuta_docD1());
            ps.setString(4, estancia.getRuta_docD2());
            ps.setString(5, estancia.getRuta_docD3());
            ps.setString(6, estancia.getRuta_docD4());
            ps.setString(7, estancia.getRuta_docD5());
            ps.setString(8, estancia.getRuta_docD6());

            ps.setInt(9, estancia.getId_estancia());
            ps.executeUpdate();

        } catch (SQLException e) {

            System.out.println("Error en el metodo insertarDatosCD " + e.getLocalizedMessage());
        } finally {

            conexion.close();
            ps.close();
        }
    }

    /*METODO PARA REVISAR CAMBIO DE DOMICILIO*/
    public void insertarDatosRevisar_cd(RevisarCd revisar) throws SQLException {

        Connection conexion = null;
        PreparedStatement ps = null;
        String sql;

        try {
            conexion = origenDatos.getConnection();
            sql = "INSERT INTO revisar_cd(id_estancia,r_docD1,r_docD2,r_docD3,r_docD4,r_docD5,r_docD6,comentarios,estatus,usuario_rev)VALUES(?,?,?,?,?,?,?,?,?,?)";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, revisar.getId_estancia());
            ps.setString(2, revisar.getR_docD1());
            ps.setString(3, revisar.getR_docD2());
            ps.setString(4, revisar.getR_docD3());
            ps.setString(5, revisar.getR_docD4());
            ps.setString(6, revisar.getR_docD5());
            ps.setString(7, revisar.getR_docD6());
            ps.setString(8, revisar.getComentarios());
            ps.setString(9, revisar.getEstatus());

            ps.setString(10, revisar.getUsuario_rev());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al instertar datos " + e.getLocalizedMessage());
        } finally {
            conexion.close();
            ps.close();
        }

    }

    /*METODO PARA ACTUALIZAR CAMBIO DE DOMICILIO*/
    public void actualizarDatosRevisar_cd(RevisarCd revisar) throws SQLException {

        Connection conexion = null;
        PreparedStatement ps = null;
        String sql;

        try {
            conexion = origenDatos.getConnection();
            sql = "UPDATE revisar_cd SET r_docD1=?, r_docD2=?,r_docD3=?,r_docD4=?,r_docD5=?,r_docD6=?,comentarios=?,estatus=?,usuario_rev=? WHERE id_estancia = ?";
            ps = conexion.prepareStatement(sql);

            ps.setString(1, revisar.getR_docD1());
            ps.setString(2, revisar.getR_docD2());
            ps.setString(3, revisar.getR_docD3());
            ps.setString(4, revisar.getR_docD4());
            ps.setString(5, revisar.getR_docD5());
            ps.setString(6, revisar.getR_docD6());
            ps.setString(7, revisar.getComentarios());
            ps.setString(8, revisar.getEstatus());
            ps.setString(9, revisar.getUsuario_rev());

            ps.setInt(10, revisar.getId_estancia());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al instertar datos " + e.getLocalizedMessage());
        } finally {
            conexion.close();
            ps.close();
        }

    }
        //Metodo para actualizar la revision de cambio de responsable
    public void actualizarDatosRevisar_cr(RevisarCr revisar) throws SQLException {

        Connection conexion = null;
        PreparedStatement ps = null;
        String sql;

        try {
            conexion = origenDatos.getConnection();
            sql = "UPDATE revisar_cr SET r_docR1=?, r_docR2=?,r_docR3=?,comentarios=?,estatus=?,usuario_rev=? WHERE id_estancia = ?";
            ps = conexion.prepareStatement(sql);

            ps.setString(1, revisar.getR_doc_R1());
            ps.setString(2, revisar.getR_doc_R2());
            ps.setString(3, revisar.getR_doc_R3());
            ps.setString(4, revisar.getComentarios());
            ps.setString(5, revisar.getEstatus());
            ps.setString(6, revisar.getUsuario_rev());

            ps.setInt(7, revisar.getId_estancia());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al instertar datos " + e.getLocalizedMessage());
        } finally {
            conexion.close();
            ps.close();
        }
    }

    /*METODO PARA CONSULTAR LOS DATOS DE LA REVISION DE CD*/
    public RevisarCd getDatosCd(int id_estancia) throws SQLException {

        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs;
        RevisarCd revisar = null;
        String sql;

        try {
            conexion = origenDatos.getConnection();
            sql = " SELECT * FROM revisar_cd WHERE id_estancia = ?";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id_estancia);
            rs = ps.executeQuery();
            if (rs.next()) {
                //int idestancia = rs.getInt("id_estancia");
                String r_docD1 = rs.getString("r_docD1");
                String r_docD2 = rs.getString("r_docD2");
                String r_docD3 = rs.getString("r_docD3");
                String r_docD4 = rs.getString("r_docD4");
                String r_docD5 = rs.getString("r_docD5");
                String r_docD6 = rs.getString("r_docD6");
                String estatus = rs.getString("estatus");
                String comentarios = rs.getString("comentarios");
                String usuario_rev = rs.getString("usuario_rev");

                revisar = new RevisarCd(id_estancia, r_docD1, r_docD2, r_docD3, r_docD4, r_docD5, r_docD6, comentarios, estatus, usuario_rev);
            }
        } catch (SQLException e) {
            System.out.println("Error en el metodo getDatosCd" + e.getLocalizedMessage());
        } finally {
            conexion.close();
            ps.close();
        }
        return revisar;
    }

    /*METODO PARA VER EL LISTADO(JOIN) DE CAMBIO DE DOMICILIO*/
    public List<EstanciaCd> getListadoCD(String estadoCd) throws SQLException {

        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;
        EstanciaCd estanciaCd;
        List<EstanciaCd> listado = new ArrayList<>();
        try {
            conexion = origenDatos.getConnection();
            if (estadoCd.equals("OFICINAS CENTRALES")) {
                sql = "SELECT * FROM estancia AS e INNER JOIN revisar_cd AS cd ON e.id_estancia = cd.id_estancia";
                ps = conexion.prepareStatement(sql);
                rs = ps.executeQuery();
            } else {
                sql = "SELECT * FROM estancia AS e INNER JOIN revisar_cd AS cd ON e.id_estancia = cd.id_estancia  WHERE e.Estado = ?";
                ps = conexion.prepareStatement(sql);
                ps.setString(1, estadoCd);
                rs = ps.executeQuery();
            }
            while (rs.next()) {
                /*Tabla estancia*/
                int idEstancia = rs.getInt("id_estancia");
                String nombreEstancia = rs.getString("nombre_estancia");
                String nombreResponsable = rs.getString("nombre_responsable");
                String direccion = rs.getString("direccion");
                String estado = rs.getString("estado");
                /*Tabla revisar_cd*/
                String rutaDocD1 = rs.getString("ruta_docD1");
                String rutaDocD2 = rs.getString("ruta_docD2");
                String rutaDocD3 = rs.getString("ruta_docD3");
                String rutaDocD4 = rs.getString("ruta_docD4");
                String rutaDocD5 = rs.getString("ruta_docD5");
                String rutaDocD6 = rs.getString("ruta_docD6");
                String rDocD1 = rs.getString("r_docD1");
                String rDocD2 = rs.getString("r_docD2");
                String rDocD3 = rs.getString("r_docD3");
                String rDocD4 = rs.getString("r_docD4");
                String rDocD5 = rs.getString("r_docD5");
                String rDocD6 = rs.getString("r_docD5");
                String comentarios = rs.getString("comentarios");
                String estatus = rs.getString("estatus");
                String usuario_rev = rs.getString("usuario_rev");
                /*Objetos de entidad estancia*/
                estanciaCd = new EstanciaCd();
                estanciaCd.setId_estancia(idEstancia);
                estanciaCd.setNombre_estancia(nombreEstancia);
                estanciaCd.setNombre_responsable(nombreResponsable);
                estanciaCd.setDireccion(direccion);
                estanciaCd.setEstado(estado);
                /*Objetos de la entidad RevisarCd*/
                estanciaCd.setRuta_docD1(rutaDocD1);
                estanciaCd.setRuta_docD2(rutaDocD2);
                estanciaCd.setRuta_docD3(rutaDocD3);
                estanciaCd.setRuta_docD4(rutaDocD4);
                estanciaCd.setRuta_docD5(rutaDocD5);
                estanciaCd.setRuta_docD6(rutaDocD6);
                estanciaCd.setR_docD1(rDocD1);
                estanciaCd.setR_docD2(rDocD2);
                estanciaCd.setR_docD3(rDocD3);
                estanciaCd.setR_docD4(rDocD4);
                estanciaCd.setR_docD5(rDocD5);
                estanciaCd.setR_docD6(rDocD6);
                estanciaCd.setComentarios(comentarios);
                estanciaCd.setEstatus(estatus);
                estanciaCd.setUsuario_rev(usuario_rev);

                listado.add(estanciaCd);
                //listado.add(revisarCd);
            }
        } catch (SQLException e) {
            System.out.println("Error en el metodo getListadoCd " + e.getLocalizedMessage());
        } finally {
            conexion.close();
            ps.close();
        }
        return listado;
    }

    public List<EstanciaCd> getEstanciaCD(String parametro, String estadoCd) throws SQLException {

        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;
        EstanciaCd estanciaCd;
        List<EstanciaCd> listado = new ArrayList<>();

        try {
            conexion = origenDatos.getConnection();
            if (estadoCd.equals("OFICINAS CENTRALES")) {
                sql = " SELECT * FROM estancia AS e INNER JOIN revisar_cd AS cd \n"
                        + " ON e.id_estancia = cd.id_estancia WHERE e.id_estancia = '" + parametro + "' || e.nombre_responsable LIKE '" + parametro + "%' || \n"
                        + " e.nombre_estancia LIKE '" + parametro + "%' || e.estado LIKE '" + parametro + "%' ";
                ps = conexion.prepareStatement(sql);
                rs = ps.executeQuery();
            } else {
                sql = " SELECT * FROM estancia AS e INNER JOIN revisar_cd AS cd \n"
                        + " ON e.id_estancia = cd.id_estancia WHERE e.estado = ? AND e.Id_estancia = ? ";
                ps = conexion.prepareStatement(sql);
                ps.setString(1, estadoCd);
                ps.setString(2, parametro);
                rs = ps.executeQuery();
            }
            while (rs.next()) {
                /*Tabla estancia*/
                int idEstancia = rs.getInt("id_estancia");
                String nombreEstancia = rs.getString("nombre_estancia");
                String nombreResponsable = rs.getString("nombre_responsable");
                String direccion = rs.getString("direccion");
                String estado = rs.getString("estado");
                /*Tabla revisar_cd*/
                String rutaDocD1 = rs.getString("ruta_docD1");
                String rutaDocD2 = rs.getString("ruta_docD2");
                String rutaDocD3 = rs.getString("ruta_docD3");
                String rutaDocD4 = rs.getString("ruta_docD4");
                String rutaDocD5 = rs.getString("ruta_docD5");
                String rutaDocD6 = rs.getString("ruta_docD6");
                String rDocD1 = rs.getString("r_docD1");
                String rDocD2 = rs.getString("r_docD2");
                String rDocD3 = rs.getString("r_docD3");
                String rDocD4 = rs.getString("r_docD4");
                String rDocD5 = rs.getString("r_docD5");
                String rDocD6 = rs.getString("r_docD5");
                String comentarios = rs.getString("comentarios");
                String usuario_rev = rs.getString("usuario_rev");
                /*Objetos de entidad estancia*/
                estanciaCd = new EstanciaCd();
                estanciaCd.setId_estancia(idEstancia);
                estanciaCd.setNombre_estancia(nombreEstancia);
                estanciaCd.setNombre_responsable(nombreResponsable);
                estanciaCd.setDireccion(direccion);
                estanciaCd.setEstado(estado);
                /*Objetos de la entidad RevisarCd*/
                estanciaCd.setRuta_docD1(rutaDocD1);
                estanciaCd.setRuta_docD2(rutaDocD2);
                estanciaCd.setRuta_docD3(rutaDocD3);
                estanciaCd.setRuta_docD4(rutaDocD4);
                estanciaCd.setRuta_docD5(rutaDocD5);
                estanciaCd.setRuta_docD6(rutaDocD6);
                estanciaCd.setR_docD1(rDocD1);
                estanciaCd.setR_docD2(rDocD2);
                estanciaCd.setR_docD3(rDocD3);
                estanciaCd.setR_docD4(rDocD4);
                estanciaCd.setR_docD5(rDocD5);
                estanciaCd.setR_docD6(rDocD6);
                estanciaCd.setComentarios(comentarios);
                estanciaCd.setUsuario_rev(usuario_rev);

                listado.add(estanciaCd);

            }
        } catch (SQLException e) {
            System.out.println("Error en el metodo getListadoCd " + e.getLocalizedMessage());
        } finally {
            conexion.close();
            ps.close();
        }
        return listado;
    }

    /*ELIMINAR LOS DOCUMENTOS CD*/
    public void eliminarCD(int id_estancia, String numDocumento) throws SQLException {

        Connection conexion = null;
        PreparedStatement ps = null;
        String sql;

        try {
            conexion = origenDatos.getConnection();
            if (numDocumento.equals("doc1")) {
                sql = "UPDATE estancia SET ruta_docD1 = NULL WHERE id_estancia = ?";
                ps = conexion.prepareStatement(sql);
                ps.setInt(1, id_estancia);
                ps.executeUpdate();
            } else if (numDocumento.equals("doc2")) {
                sql = "UPDATE estancia SET ruta_docD2 = NULL WHERE id_estancia = ?";
                ps = conexion.prepareStatement(sql);
                ps.setInt(1, id_estancia);
                ps.executeUpdate();
            } else if (numDocumento.equals("doc3")) {
                sql = "UPDATE estancia SET ruta_docD3 = NULL WHERE id_estancia = ?";
                ps = conexion.prepareStatement(sql);
                ps.setInt(1, id_estancia);
                ps.executeUpdate();
            } else if (numDocumento.equals("doc4")) {
                sql = "UPDATE estancia SET ruta_docD4 = NULL WHERE id_estancia = ?";
                ps = conexion.prepareStatement(sql);
                ps.setInt(1, id_estancia);
                ps.executeUpdate();
            } else if (numDocumento.equals("doc5")) {
                sql = "UPDATE estancia SET ruta_docD5 = NULL WHERE id_estancia = ?";
                ps = conexion.prepareStatement(sql);
                ps.setInt(1, id_estancia);
                ps.executeUpdate();
            } else if (numDocumento.equals("doc6")) {
                sql = "UPDATE estancia SET ruta_docD6 = NULL WHERE id_estancia = ?";
                ps = conexion.prepareStatement(sql);
                ps.setInt(1, id_estancia);
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar el CD " + e.getLocalizedMessage());
        } finally {
            conexion.close();
            ps.close();
        }

    }

    /*METODOS CAMBIO DE RESPONSABLE*/
    
    //Insertar datos 
    public void insertarDatosCR(Estancia estancia) throws SQLException {

        Connection conexion = null;
        PreparedStatement ps = null;
        String sql;
        try {
            conexion = origenDatos.getConnection();
            sql = "UPDATE estancia SET Nombre_responsable=?, estatus_responsable=?,ruta_docR1=?,ruta_docR2=?,ruta_docR3=? WHERE id_estancia = ?";
            ps = conexion.prepareStatement(sql);

            ps.setString(1, estancia.getNombre_responsable());
            ps.setString(2, estancia.getEstatus_responsable());
            ps.setString(3, estancia.getRuta_docR1());
            ps.setString(4, estancia.getRuta_docR2());
            ps.setString(5, estancia.getRuta_docR3());

            ps.setInt(6, estancia.getId_estancia());
            ps.executeUpdate();

        } catch (SQLException e) {

            System.out.println("Error en el metodo insertarDatosCD " + e.getLocalizedMessage());
        } finally {

            conexion.close();
            ps.close();
        }
    }
    //obtener los datos de la revision de cambio de responsable
    public RevisarCr getDatosCr(int id_estancia) throws SQLException {

        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs;
        RevisarCr revisar = null;
        String sql;

        try {
            conexion = origenDatos.getConnection();
            sql = " SELECT * FROM revisar_cr WHERE id_estancia = ?";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, id_estancia);
            rs = ps.executeQuery();
            if (rs.next()) {
                //int idestancia = rs.getInt("id_estancia");
                String r_docR1 = rs.getString("r_docR1");
                String r_docR2 = rs.getString("r_docR2");
                String r_docR3 = rs.getString("r_docR3");
                
                String estatus = rs.getString("estatus");
                String comentarios = rs.getString("comentarios");
                String usuario_rev = rs.getString("usuario_rev");

                revisar = new RevisarCr(id_estancia, r_docR1, r_docR2, r_docR3, comentarios, estatus, usuario_rev);
            }
        } catch (SQLException e) {
            System.out.println("Error en el metodo getDatosCd" + e.getLocalizedMessage());
        } finally {
            conexion.close();
            ps.close();
        }
        return revisar;
    }
    
     /*insertar datos en la revision de cambio de responsable*/
    public void insertarDatosRevisar_cr(RevisarCr revisar) throws SQLException {

        Connection conexion = null;
        PreparedStatement ps = null;
        String sql;

        try {
            conexion = origenDatos.getConnection();
            sql = "INSERT INTO revisar_cr(id_estancia,r_docR1,r_docR2,r_docR3,comentarios,estatus,usuario_rev)VALUES(?,?,?,?,?,?,?)";
            ps = conexion.prepareStatement(sql);
            ps.setInt(1, revisar.getId_estancia());
            ps.setString(2, revisar.getR_doc_R1());
            ps.setString(3, revisar.getR_doc_R2());
            ps.setString(4, revisar.getR_doc_R3());
            ps.setString(5, revisar.getComentarios());
            ps.setString(6, revisar.getEstatus());

            ps.setString(7, revisar.getUsuario_rev());

            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al instertar datos " + e.getLocalizedMessage());
        } finally {
            conexion.close();
            ps.close();
        }

    }
    public List<EstanciaCr> getListadoCR(String estadoCr) throws SQLException {

        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;
        EstanciaCr estanciaCr;
        List<EstanciaCr> listado = new ArrayList<>();
        try {
            conexion = origenDatos.getConnection();
            if (estadoCr.equals("OFICINAS CENTRALES")) {
                sql = "SELECT * FROM estancia AS e INNER JOIN revisar_cr AS cr ON e.id_estancia = cr.id_estancia";
                ps = conexion.prepareStatement(sql);
                rs = ps.executeQuery();
            } else {
                sql = "SELECT * FROM estancia AS e INNER JOIN revisar_cr AS cr ON e.id_estancia = cr.id_estancia  WHERE e.Estado = ?";
                ps = conexion.prepareStatement(sql);
                ps.setString(1, estadoCr);
                rs = ps.executeQuery();
            }
            while (rs.next()) {
                /*Tabla estancia*/
                int idEstancia = rs.getInt("id_estancia");
                String nombreEstancia = rs.getString("nombre_estancia");
                String nombreResponsable = rs.getString("nombre_responsable");
                String direccion = rs.getString("direccion");
                String estado = rs.getString("estado");
                /*Tabla revisar_cd*/
                String rutaDocR1 = rs.getString("ruta_docR1");
                String rutaDocR2 = rs.getString("ruta_docR2");
                String rutaDocR3 = rs.getString("ruta_docR3");
                String rDocR1 = rs.getString("r_docR1");
                String rDocR2 = rs.getString("r_docR2");
                String rDocR3 = rs.getString("r_docR3");
                String comentarios = rs.getString("comentarios");
                String estatus = rs.getString("estatus");
                String usuario_rev = rs.getString("usuario_rev");
                /*Objetos de entidad estancia*/
                estanciaCr = new EstanciaCr();
                estanciaCr.setId_estancia(idEstancia);
                estanciaCr.setNombre_estancia(nombreEstancia);
                estanciaCr.setNombre_responsable(nombreResponsable);
                estanciaCr.setDireccion(direccion);
                estanciaCr.setEstado(estado);
                /*Objetos de la entidad RevisarCd*/
                estanciaCr.setRuta_docR1(rutaDocR1);
                estanciaCr.setRuta_docR2(rutaDocR2);
                estanciaCr.setRuta_docR3(rutaDocR3);
                estanciaCr.setR_docR1(rDocR1);
                estanciaCr.setR_docR2(rDocR2);
                estanciaCr.setR_docR3(rDocR3);
                estanciaCr.setComentarios(comentarios);
                estanciaCr.setEstatus(estatus);
                estanciaCr.setUsuario_rev(usuario_rev);

                listado.add(estanciaCr);
            }
        } catch (SQLException e) {
            System.out.println("Error en el metodo getListadoCd " + e.getLocalizedMessage());
        } finally {
            conexion.close();
            ps.close();
        }
        return listado;
    }

    //Listar el cambio de responsable
     public List<EstanciaCr> getEstanciaCR(String parametro, String estadoCd) throws SQLException {

        Connection conexion = null;
        PreparedStatement ps = null;
        ResultSet rs;
        String sql;
        EstanciaCr estanciaCr;
        List<EstanciaCr> listado = new ArrayList<>();

        try {
            conexion = origenDatos.getConnection();
            if (estadoCd.equals("OFICINAS CENTRALES")) {
                sql = " SELECT * FROM estancia AS e INNER JOIN revisar_cr AS cr \n"
                        + " ON e.id_estancia = cr.id_estancia WHERE e.id_estancia = '" + parametro + "' || e.nombre_responsable LIKE '" + parametro + "%' || \n"
                        + " e.nombre_estancia LIKE '" + parametro + "%' || e.estado LIKE '" + parametro + "%' ";
                ps = conexion.prepareStatement(sql);
                rs = ps.executeQuery();
            } else {
                sql = "SELECT * FROM estancia AS e INNER JOIN revisar_cr AS cr \n"
                        + " ON e.id_estancia = cr.id_estancia WHERE e.estado = ? AND e.Id_estancia = ? ";
                ps = conexion.prepareStatement(sql);
                ps.setString(1, estadoCd);
                ps.setString(2, parametro);
                rs = ps.executeQuery();
            }
            while (rs.next()) {
                /*Tabla estancia*/
                int idEstancia = rs.getInt("id_estancia");
                String nombreEstancia = rs.getString("nombre_estancia");
                String nombreResponsable = rs.getString("nombre_responsable");
                String direccion = rs.getString("direccion");
                String estado = rs.getString("estado");
                /*Tabla revisar_cd*/
                String rutaDocR1 = rs.getString("ruta_docR1");
                String rutaDocR2 = rs.getString("ruta_docR2");
                String rutaDocR3 = rs.getString("ruta_docR3");

                String rDocR1 = rs.getString("r_docR1");
                String rDocR2 = rs.getString("r_docR2");
                String rDocR3 = rs.getString("r_docR3");

                String comentarios = rs.getString("comentarios");
                String usuario_rev = rs.getString("usuario_rev");
                /*Objetos de entidad estancia*/
                estanciaCr = new EstanciaCr();
                estanciaCr.setId_estancia(idEstancia);
                estanciaCr.setNombre_estancia(nombreEstancia);
                estanciaCr.setNombre_responsable(nombreResponsable);
                estanciaCr.setDireccion(direccion);
                estanciaCr.setEstado(estado);
                /*Objetos de la entidad RevisarCd*/
                estanciaCr.setRuta_docR1(rutaDocR1);
                estanciaCr.setRuta_docR2(rutaDocR2);
                estanciaCr.setRuta_docR3(rutaDocR3);
                estanciaCr.setR_docR1(rDocR1);
                estanciaCr.setR_docR2(rDocR2);
                estanciaCr.setR_docR3(rDocR3);
                estanciaCr.setComentarios(comentarios);
                estanciaCr.setUsuario_rev(usuario_rev);

                listado.add(estanciaCr);

            }
        } catch (SQLException e) {
            System.out.println("Error en el metodo getListadoCd " + e.getLocalizedMessage());
        } finally {
            conexion.close();
            ps.close();
        }
        return listado;
    }
     
     /*Eliminar dacumento CR*/
     public void eliminarCR(int id_estancia, String numDocumento) throws SQLException {

        Connection conexion = null;
        PreparedStatement ps = null;
        String sql;

        try {
            conexion = origenDatos.getConnection();
            if (numDocumento.equals("doc1")) {
                sql = "UPDATE estancia SET ruta_docR1 = NULL WHERE id_estancia = ?";
                ps = conexion.prepareStatement(sql);
                ps.setInt(1, id_estancia);
                ps.executeUpdate();
            } else if (numDocumento.equals("doc2")) {
                sql = "UPDATE estancia SET ruta_docR2 = NULL WHERE id_estancia = ?";
                ps = conexion.prepareStatement(sql);
                ps.setInt(1, id_estancia);
                ps.executeUpdate();
            } else if (numDocumento.equals("doc3")) {
                sql = "UPDATE estancia SET ruta_docR3 = NULL WHERE id_estancia = ?";
                ps = conexion.prepareStatement(sql);
                ps.setInt(1, id_estancia);
                ps.executeUpdate();
            }

        } catch (SQLException e) {
            System.out.println("Error al eliminar el CD " + e.getLocalizedMessage());
        } finally {
            conexion.close();
            ps.close();
        }

    }
     

    //******************************************************************************************************************   
    
     /*AGREGAR USUARIOS*/
     public void agregarUsuario(Usuarios usuario) throws SQLException{
         
         Connection conexion = null;
         PreparedStatement ps = null;
         String sql;
         
         try{
             
            conexion = origenDatos.getConnection();
            sql = "INSERT INTO usuarios(usuario, contrasenia, estado, privilegio) VALUES (?,?,?,?)";
            ps = conexion.prepareStatement(sql);
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getContrasenia());
            ps.setString(3, usuario.getEstado());
            ps.setString(4, usuario.getPrivilegio());
            
            ps.executeUpdate();
            
         }catch(SQLException e){
             System.out.println("Error en el metodo agregarUsuario "  + e.getLocalizedMessage() );
     }finally{
             conexion.close();
             ps.close();
         }
                 
     }
     //MOSTRAR USUARIOS
     public List<Usuarios> getUsuarios() throws SQLException{
         
         Connection conexion=null;
         PreparedStatement ps=null;
         ResultSet rs;
         String sql;
         Usuarios user;
         List<Usuarios> usuarios = new ArrayList<>();
         try{
             conexion = origenDatos.getConnection();
             sql = "SELECT * FROM usuarios";
             ps = conexion.prepareStatement(sql);
             rs = ps.executeQuery();
             
             while(rs.next()){
                 
                 String usuario = rs.getString("usuario");
                 String contraseña = rs.getString("contrasenia");
                 String estado = rs.getString("estado");
                 String privilegio = rs.getString("privilegio");
                 
                 user = new Usuarios(usuario, contraseña, estado, privilegio);
                 usuarios.add(user);
             }
         }catch(SQLException e){
             System.out.println("Error al mostrar los usuarios " + e.getLocalizedMessage());
         }finally{
             conexion.close();
             ps.close();
         }
         
         return usuarios;
         
     }
     
     //ELIMINAR USUARIOS
     
     public void eliminarUsuario(String usuario) throws SQLException{
            
         Connection conexion=null;
         PreparedStatement ps=null;
         String sql;
        // boolean eliminado = false; 
         try{
                conexion = origenDatos.getConnection();
                sql = "DELETE FROM usuarios WHERE usuario = ?";
                ps = conexion.prepareStatement(sql);
                ps.setString(1, usuario);
                
               ps.execute();
                
         }catch(SQLException e){
             System.out.println("Error en el metodo eliminar " + e.getLocalizedMessage());
         
         }finally{
             conexion.close();
             ps.close();
         }
               
     }
     
     
     
     
     /*METODOS PARA ACTUALIZAR LAS BASES DE DATOS*/
    public void insertarDatosDoc(Estancia datos) throws SQLException {

        Connection conexion = null;
        PreparedStatement pStatement = null;

        try {
            conexion = origenDatos.getConnection();
            String sql = "INSERT INTO estancia(Id_estancia,Nombre_estancia,Nombre_responsable,Direccion,Estado,Fecha_fin)"
                    + " VALUES(?,?,?,?,?,?)";
            pStatement = conexion.prepareStatement(sql);

            pStatement.setInt(1, datos.getId_estancia());
            pStatement.setString(2, datos.getNombre_estancia());
            pStatement.setString(3, datos.getNombre_responsable());
            pStatement.setString(4, datos.getDireccion());
            pStatement.setString(5, datos.getEstado());

            java.util.Date utilDate = datos.getFehca_fin();
            java.sql.Date sqlDateFechaFin = new java.sql.Date(utilDate.getTime());
            pStatement.setDate(6, sqlDateFechaFin);

            pStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error en el metodo insertarProductos " + e.getMessage());
        } finally {
            conexion.close();
            pStatement.close();
        }
    }

    public void actualizarDbDoc(Estancia datos) throws SQLException {

        Connection conexion = null;
        PreparedStatement pStatement = null;

        try {
            conexion = origenDatos.getConnection();
            String sql = "UPDATE estancia SET Nombre_estancia=?, Nombre_responsable=?, Direccion=?, Estado=? ,Fecha_fin=? WHERE Id_estancia = ?";

            pStatement = conexion.prepareStatement(sql);

            pStatement.setString(1, datos.getNombre_estancia());
            pStatement.setString(2, datos.getNombre_responsable());
            pStatement.setString(3, datos.getDireccion());
            pStatement.setString(4, datos.getEstado());

            java.util.Date utilDate = datos.getFehca_fin();
            java.sql.Date sqlDateFechaFin = new java.sql.Date(utilDate.getTime());
            pStatement.setDate(5, sqlDateFechaFin);

            //WHERE ID...
            pStatement.setInt(6, datos.getId_estancia());

            pStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error en el metodo insertarProductos " + e.getMessage());
        } finally {
            conexion.close();
            pStatement.close();
        }
    }

    public void insertarDatosRev(RevisarAm revision) throws SQLException {
        Connection conexion = null;
        PreparedStatement ps = null;

        try {
            conexion = origenDatos.getConnection();

            String sql = "INSERT INTO revisar_am(id_revision,id_estancia) VALUES (?,?)";

            ps = conexion.prepareStatement(sql);
            ps.setInt(1, revision.getId_revision());
            ps.setInt(2, revision.getId_subir_doc());

            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error en el metodo isertarDatosRev-> " + e.getLocalizedMessage());
        } finally {
            conexion.close();
            ps.close();
        }
    }

}

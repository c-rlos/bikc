/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.com.kubez.bikc.vo;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import mx.com.kubez.bikc.dto.ConstanteDTO;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javafx.event.Event;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.event.ValueChangeListener;
import javax.faces.model.SelectItem;
import mx.com.kubez.bikc.dto.ClienteDTO;
import mx.com.kubez.bikc.dao.PosDAO;
import mx.com.kubez.bikc.dao.ElementosPos;
import mx.com.kubez.bikc.dto.ProductoDTO;
import mx.com.kubez.bikc.dao.RegistrosPos;
import mx.com.kubez.bikc.delegate.PosDelegate;
import mx.com.kubez.bikc.dto.VentaDTO;
import mx.qubez.utilierias.conexion.Factory;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author c-rlos
 */
@ManagedBean(name = "Pos")
@ViewScoped
public class PosVO extends ElementosPos {

    public static final long serialVersionUID = 11233432428765L;

    private PosDelegate posDelegate;

    public List<SelectItem> documentos;
    public List<SelectItem> clientes;

    private List<VentaDTO> productosPorVender;
    private List<ProductoDTO> listaProductos;

    public String campoBusqueda;
    public String placeHolder;

    private double descuento;
    private double impuestos;
    private double totalVenta;

    public ProductoDTO selectedProduct;
    public VentaDTO selectedVenta;

    private List<String> precios;
    private List<ClienteDTO> listCliDefault;
    private List<ProductoDTO> filteredProducts;

    public static VentaDTO venta = new VentaDTO();

    private ProductoDTO producto = new ProductoDTO();

    public static List<ProductoDTO> listaDefault;
    private static boolean filtro = false;

    private StreamedContent file;

    public static boolean v_en_espera = false;

    public String fecha;
    public String hora;
    //public  ProductoDTO selectedRow;

    public PosVO() {
        Connection con = Factory.getConnection(ConstanteDTO.MAIN_CONTEXT_NAME_DB);

        posDelegate = new PosDelegate();
        inicializaValores(con);
        Factory.close(con);
    }

    private void inicializaValores(Connection con) {
        productosPorVender = new ArrayList<VentaDTO>();
        listaProductos = new ArrayList<ProductoDTO>();
        documentos = posDelegate.getListaDocumentos(con);
        clientes = posDelegate.getListaClientes(con);
        DateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
        DateFormat tf = new SimpleDateFormat("HH:mm:ss");
        fecha = df.format(new java.util.Date()).toUpperCase();
        hora = tf.format(new java.util.Date());

    }

    public void printSelectedRow(SelectEvent evt) {
        System.out.println(selectedVenta.getIdProducto() + " - " + selectedVenta.getDescripcion() + " - " + selectedVenta.getCantidad());

    }
    
    public void cierraDialogo(ActionEvent evt){
        RequestContext context = RequestContext.getCurrentInstance();
        String valor = (String)evt.getComponent().getAttributes().get("tipoDialogo");
        String[] h = valor.split(".");
        System.out.println("h: " + h);
        context.execute("PF('" + h + "').hide();");
    }

//    @PostConstruct
//    public void init() {
//        java.util.Date d = new java.util.Date();
//        Date fechaActual = new Date(d.getTime());
//        fecha = fechaActual + "";
//        hora = d.getHours() + ":" + d.getMinutes() + ":" + d.getSeconds();
//        precios = new ArrayList<String>();
//        precios.add("precio_uno");
//        precios.add("precio_dos");
//        precios.add("precio_tres");
//        Connection con = Factory.getConnection(ConstanteDTO.MAIN_CONTEXT_NAME_DB);
//        itemsDocumentos(con);
//        crearClientes(con);
//        crearTerminales(con);
//        itemsClientes();
//        itemsTerminal();
//        getProductos(con);
//        itemsVendedores();
//        ventasEnEspera(con);
//    }
    /* Modificaciones cjuarez */
//    public void buscaProducto(ActionEvent evt) {
//        listaProductos.clear();
//        Connection con = Factory.getConnection(ConstanteDTO.MAIN_CONTEXT_NAME_DB);
//        if (!campoBusqueda.equals("")) {
//            listaProductos.addAll(posDelegate.buscaProducto(campoBusqueda, con));
//            if (!listaProductos.isEmpty()) {
//                agregaProductoParaVenta(productosPorVender, listaProductos);
//                this.campoBusqueda = "";
//            } else {
//                this.campoBusqueda = "El producto no existe";
//            }
//        } else {
//            campoBusqueda = "Debe capturar un valor";
//        }
//        Factory.close(con);
//    }
    public void buscaProductoVenta() {
        listaProductos.clear();
        Connection con = Factory.getConnection(ConstanteDTO.MAIN_CONTEXT_NAME_DB);
        if (!campoBusqueda.equals("")) {
            listaProductos.addAll(posDelegate.buscaProducto(campoBusqueda, con));
            if (!listaProductos.isEmpty()) {
                agregaProductoParaVenta(productosPorVender, listaProductos, false);
                this.placeHolder = "";
                this.campoBusqueda = "";
            } else {
                this.placeHolder = "El producto no existe";
                this.campoBusqueda = "";
            }
        } else {
            placeHolder = "Debe capturar un valor";
            this.campoBusqueda = "";
        }
        Factory.close(con);
    }

    public void buscaProductoCatalogoProducto() {
        listaProductos.clear();
        Connection con = Factory.getConnection(ConstanteDTO.MAIN_CONTEXT_NAME_DB);
        if (!campoBusqueda.equals("")) {
            listaProductos.addAll(posDelegate.buscaProducto(campoBusqueda, con));
            if (!listaProductos.isEmpty()) {
                agregaProductoParaVenta(productosPorVender, listaProductos, true);
                this.placeHolder = "";
                this.campoBusqueda = "";
                this.campoBusqueda2 = "";
            } else {
                this.placeHolder = "El producto no existe";
                this.campoBusqueda = "";
                this.campoBusqueda2 = "";
            }
        } else {
            placeHolder = "Debe capturar un valor";
            this.campoBusqueda = "";
            this.campoBusqueda2 = "";
        }
        Factory.close(con);
    }

//    public void buscaProductos(ValueChangeEvent evt) {
//        Connection con = Factory.getConnection(ConstanteDTO.MAIN_CONTEXT_NAME_DB);
//        listaProductos.addAll(posDelegate.buscaProducto(campoBusqueda, con));
//        agregaProductoParaVenta(productosPorVender, listaProductos);
//
//        listaProductos.clear();
//        this.campoBusqueda = "";
//        Factory.close(con);
//    }
    public void seleccionaProductoParaVenta(SelectEvent event) {
        List<ProductoDTO> np = new ArrayList<ProductoDTO>();
        np.add(selectedProduct);

        agregaProductoParaVenta(productosPorVender, np, false);
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('w_buscar2').hide();");
        context.execute("PF('w_buscar').hide();");
        listaProductos.clear();
        selectedProduct = null;
    }

    public void agregaProductoParaVenta(List<VentaDTO> productosPorVender, List<ProductoDTO> listaProductos, boolean isBusquedaCatalogo) {
        boolean productoAgregado = false;
        if (!isBusquedaCatalogo) {

            if (productosPorVender.isEmpty()) {
                // Si la venta esta vacia
                if (listaProductos.size() > 1) {
                    // Muestra lista productos para elegir
                    RequestContext context = RequestContext.getCurrentInstance();
                    context.execute("PF('w_buscar2').show();");
                } else {
                    // Añade el único producto a la lista de ventas
                    ProductoDTO p = listaProductos.get(0);
                    VentaDTO v = new VentaDTO();
                    v.setIdProducto(p.getIdProducto());
                    v.setCantidad(1);
                    v.setDescripcion(p.getDescripcion() + " " + p.getMarca() + " " + p.getModelo());
                    v.setPrecioUnitario(p.getPrecioUno());
                    v.setDescuento(p.getDescuentoPromocion());
                    v.setTipoDescuento("");
                    v.setImporte(v.getPrecioUnitario() * v.getCantidad());
                    productosPorVender.add(v);
                }
            } else {
                // Si la venta NO esta vacia
                if (listaProductos.size() > 1) {
                    // Muestra lista productos para elegir
                    RequestContext context = RequestContext.getCurrentInstance();
                    context.execute("PF('w_buscar2').show();");
                } else {
                    // Añade producto a la lista de ventas
                    for (VentaDTO pv : productosPorVender) {
                        if (listaProductos.get(0).getIdProducto() == pv.getIdProducto()) {
                            pv.setCantidad(pv.getCantidad() + 1);
                            pv.setImporte(pv.getCantidad() * listaProductos.get(0).getPrecioUno());
                            productoAgregado = true;
                            break;
                        }
                    }
                    if (!productoAgregado) {
                        // Añade el único producto a la lista de ventas
                        ProductoDTO p = listaProductos.get(0);
                        VentaDTO v = new VentaDTO();
                        v.setIdProducto(p.getIdProducto());
                        v.setCantidad(1);
                        v.setDescripcion(p.getDescripcion() + " " + p.getMarca() + " " + p.getModelo());
                        v.setPrecioUnitario(p.getPrecioUno());
                        v.setDescuento(p.getDescuentoPromocion());
                        v.setTipoDescuento("");
                        v.setImporte(v.getPrecioUnitario() * v.getCantidad());
                        productosPorVender.add(v);
                    }
                }
            }
        }
        actualizaTotales();
    }

    public void actualizaTotales() {
        this.descuento = 0;
        this.impuestos = 0;
        this.totalVenta = 0;

        for (VentaDTO pv : productosPorVender) {
            this.totalVenta += pv.getImporte();
            this.descuento += pv.getDescuento();
        }
        this.totalVenta = this.totalVenta - this.descuento;
        this.impuestos = this.totalVenta * 0.16;
    }


    /*------------*/
    public void ventasEnEspera(Connection con) {
        new PosDAO().ventasSinCerrar(this, con);
    }

    public void generarTicket() throws FileNotFoundException, DocumentException {
        FileOutputStream archivo = new FileOutputStream("C:\\Users\\Usuario1\\hola2.pdf");

        Document documento = new Document();
        PdfWriter.getInstance(documento, archivo);
        documento.open();
        documento.add(new Paragraph("Hola Mundo!"));
        documento.add(new Paragraph("SoloInformaticaYAlgoMas.blogspot.com"));
        documento.close();
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/images/icons/buscar.png");
        this.file = new DefaultStreamedContent(stream, "image/png", "hola2.png");

    }

    public StreamedContent getFile() {
        InputStream stream = FacesContext.getCurrentInstance().getExternalContext().getResourceAsStream("/resources/images/hola2.pdf");
        this.file = new DefaultStreamedContent(stream, "application/pdf", "hola2.pdf");
        return file;
    }

    public void itemsVendedores() {
        this.vendedores = new ArrayList<SelectItem>();
        SelectItem sI = new SelectItem("Usuario");
        vendedores.add(sI);
        //SessionDTO session = new SessionDTO("pos");
        //session.validaSession();
        //SelectItem sI = new SelectItem(session.getUser().getPerfil().getNombre());
        //vendedores.add(sI);
    }

    //Metodo para que obtiene las terminales de la BD para llenar el selectedMenu terminal
    public void itemsTerminal() {
        //new PosDAO().obtenerTerminales(this, con);
        terminales = new ArrayList<SelectItem>();
        for (int i = 0; i < listaTerminales.size(); i++) {
            if (listaTerminales.get(i).getPredeterminada() == 1) {
                SelectItem sI = new SelectItem(listaTerminales.get(i).getNombre());
                terminales.add(0, sI);
            } else {
                SelectItem sI = new SelectItem(listaTerminales.get(i).getNombre());
                terminales.add(sI);
            }
        }
    }

    //Metodo para que obtiene los tipos de documentos de la BD para llenar el selectedMenu Documentos
//    public void itemsDocumentos(Connection con) {
//        new PosDAO().obtenerDocumentos(this, con);
//    }
    //Metodo para que llenar la lista de documentos de BD
    public void itemsClientes() {
        clientes = new ArrayList<SelectItem>();
        for (int i = 0; i < listCliDefault.size(); i++) {
            if (listCliDefault.get(i).getPredeterminado() == 1) {
                SelectItem sI = new SelectItem(listCliDefault.get(i).getNombre());
                clientes.add(0, sI);
            } else {
                SelectItem sI = new SelectItem(listCliDefault.get(i).getNombre());
                clientes.add(sI);
            }
        }
    }

    public void inicializaElementos(Connection con) {

    }

    public void buscar() {
        //Connection con = con = Factory.getConnection(ConstanteDTO.MAIN_CONTEXT_NAME_DB);
        ProductoDTO prod = null;
        new PosDAO().buscar(this);

    }

    public void getProductos(Connection con) {
        new PosDAO().obtenerProductos(this, con);
    }

    //Metodo para obtener todos los productos en una lista
    public List<ProductoDTO> getListaProductos() {
        return listaProductos;
    }

    public boolean verificaLista(ProductoDTO p) {
        for (int i = 0; i < listaVenta.size(); i++) {
            if (listaVenta.get(i).getBarcode().equals(p.getBarcode())) {
                return true;
            }
        }
        return false;
    }

    public void cobrar(ActionEvent evt) {
        System.out.println("Tecla presionada...." + v_en_espera);
        Connection con = Factory.getConnection(ConstanteDTO.MAIN_CONTEXT_NAME_DB);
        //if(v_en_espera == true){ 
        new RegistrosPos().ventasEnEspera(this, con);
        //}else 

        new RegistrosPos().registarCompra(this, con);
        new PosDAO().ventasSinCerrar(this, con);
        v_en_espera = false;
        this.descuento = 0;
        this.impuestos = 0;
        this.totalVenta = 0;
    }

    //Metodo para sumar el precio de la lista de productos y obtener el total a pagar
//    public void totalPagar(){
//        double total = 0;
//        double iva = 0;
//        double descuento = 0;
//        for(int i = 0;i<listaVenta.size();i++){
//            if(listaVenta.get(i).getCantidad() >= listaVenta.get(i).getAplicaPromocion()){
//                descuento = descuento + listaVenta.get(i).getDescuento();
//            }
//            iva = iva + listaVenta.get(i).getIva();
//            total = total + (listaVenta.get(i).importe * listaVenta.get(i).getCantidad() - descuento);
//        }
//        this.totalVenta = total + iva;
//        this.impuetos = iva;
//        this.descuento = descuento;
//    }
    /**
     * Get the value of listaVenta
     *
     * @return the value of listaVenta
     */
    public List<ProductoDTO> getListaVenta() {
        return listaVenta;
    }

    /**
     * Set the value of listaVenta
     *
     * @param listaVenta new value of listaVenta
     */
    public void setListaVenta(List<ProductoDTO> listaVenta) {
        this.listaVenta = listaVenta;
    }

//    /**
//     * Get the value of selectedRow
//     *
//     * @return the value of selectedRow
//     */
//    public ProductoDTO getSelectedRow() {
//        return selectedRow;
//    }
    public void asignarCampos(ProductoDTO p) {

    }

//    /**
//     * Set the value of selectedRow
//     *
//     * @param selectedRow new value of selectedRow
//     */
//    public void setSelectedRow(ProductoDTO selectedRow) {
//        this.selectedRow = selectedRow;
//    }
    /**
     * Set the value of listaProductos
     *
     * @param listaProductos new value of listaProductos
     */
    public void setListaProductos(List<ProductoDTO> listaProductos) {

        this.listaProductos = listaProductos;
    }

//    public void onRowDblSelect(SelectEvent event) {
//        System.out.println("Hola mundo - onRowSelect -"+selectedProduct.descripcion);
//        if(!verificaLista(selectedProduct)){
//            listaVenta.add(selectedProduct);
//        }
//        this.totalPagar();
//    }
    public void selectedEspera(SelectEvent event) {
        System.out.println("Hola mundo - select espera -" + this.selectEspera.getIdVenta());
    }

    //Metodo que obtiene el fila de la tabla clientes
    public void rowSelectCli(SelectEvent event) {
        System.out.println("Hola mundo - onRowSelect cliente -" + selectedClient.getNombre());
        System.out.println("Hola mundo - on menu cliente -" + this.cliente);
        for (int i = 0; i < clientes.size(); i++) {
            if (this.clientes.get(i).getLabel().equals(this.selectedClient.getNombre())) {
                String temp = this.clientes.get(0).getLabel();
                this.clientes.get(0).setLabel(selectedClient.getNombre());
                venta.setIdCliente(selectedClient.getIdCliente());
                this.clientes.get(i).setLabel(temp);
                //this.clientes.add(this.clientes.get(0));
                System.out.println("entro -" + temp);
            }
        }

    }

//    public void onRowSelect(SelectEvent event) {
//        if(!verificaLista(selectedProduct)){
//            listaVenta.add(selectedProduct);
//        }
//        this.totalPagar();
//    }
//    public void preciosVenta(){
//        this.campoPreVenta = selectedRow.getPrecioVenta();
//        this.campoPreMay1 = selectedRow.getPrecioMayoreo1();
//        this.campoPreMay2 = selectedRow.getPrecioMayoreo2();
//    }
//    public void cambiarCantidad() {
//        for (int i = 0; i < listaVenta.size(); i++) {
//            if (listaVenta.get(i).getDescripcion().equals(this.selectedProduct.getDescripcion())) {
//                if (listaVenta.get(i).getInventario() >= this.campoCantidad) {
//                    listaVenta.get(i).setCantidad(this.campoCantidad);
//                } else {
//                    //Esta cantidad de producto no esta disponible
//                }
//            }
//        }
//        //totalPagar();
//    }

    public void eliminar() {
        System.out.println("celiminar - ");
        for (int i = 0; i < listaVenta.size(); i++) {
            if (listaVenta.get(i).getDescripcion().equals(this.selectedProduct.getDescripcion())) {
                listaVenta.remove(i);
            }
        }
        //totalPagar();
    }

    public void cambiarPrecio() {
        System.out.println("cambiar precio - ");
        for (int i = 0; i < listaVenta.size(); i++) {
            if (listaVenta.get(i).getDescripcion().equals(selectedProduct.getDescripcion())) {
                System.out.println("cambiar precio - " + selectedProduct.getDescripcion());
                switch (selectedPrecio) {
                    case "precio_uno":
                        listaVenta.get(i).setCosto(this.campoPreVenta);
                        break;
                    case "precio_dos":
                        listaVenta.get(i).setCosto(this.campoPreMay1);
                        break;
                    case "precio_tres":
                        listaVenta.get(i).setCosto(this.campoPreMay2);
                        break;
                }
            }
        }
        //totalPagar();
    }

    public void camposCobrar() {
        //totalPagar();
        this.campoTotal = this.totalVenta;
        this.campoCambio = (this.campoRecibido - this.totalVenta);
        System.out.println("cobrar...");
    }

    public void cancelarVenta() {

        Connection con = Factory.getConnection(ConstanteDTO.MAIN_CONTEXT_NAME_DB);
        if (v_en_espera) {
            new RegistrosPos().cancelarEnEspera(this, con);
            //ventasEspera.clear();
            new PosDAO().ventasSinCerrar(this, con);
        }
        listaVenta.clear();
        v_en_espera = false;
        this.descuento = 0;
        this.impuestos = 0;
        this.totalVenta = 0;
    }

    /**
     * Get the value of filteredProducts
     *
     * @return the value of filteredProducts
     */
    public List<ProductoDTO> getFilteredProducts() {
        System.out.println("get filtros");
        return filteredProducts;
    }

    public void filtrar() {
        listaProductos = null;
        listaProductos = listaDefault;
        try {
            int barcode = Integer.parseInt(campoBusqueda2);
            System.out.println("barcode-- " + barcode);
            for (int i = listaProductos.size() - 1; i > 0; i--) {
                if (String.valueOf(listaProductos.get(i).getBarcode()).startsWith(campoBusqueda2)); else {
                    listaProductos.remove(i);
                }
            }
            if (String.valueOf(listaProductos.get(0).getBarcode()).startsWith(barcode + "")); else {
                listaProductos.remove(0);
            }
        } catch (NumberFormatException ex) {
            for (int i = listaProductos.size() - 1; i > 0; i--) {
                if (listaProductos.get(i).getDescripcion().toLowerCase().startsWith(campoBusqueda2.toLowerCase())) {

                } else {
                    listaProductos.remove(i);
                }
            }
            if (listaProductos.get(0).getDescripcion().toLowerCase().startsWith(campoBusqueda2.toLowerCase())) {

            } else {
                listaProductos.remove(0);
            }
        }

        System.out.println("filtro-----------" + campoBusqueda2 + "--");
    }

    public void filtrarClientes() {
        listaClientes = null;
        listaClientes = listCliDefault;
        try {
            int id_cliente = Integer.parseInt(busquedaClientes);
            for (int i = listaClientes.size() - 1; i > 0; i--) {
                if (String.valueOf(listaClientes.get(i).getIdCliente()).startsWith(busquedaClientes)) {
                } else {
                    System.out.println("eliminado..." + listaClientes.get(i).getIdCliente() + "--" + listaClientes.size());
                    listaClientes.remove(i);
                }
            }
            if (String.valueOf(listaClientes.get(0).getIdCliente()).startsWith(id_cliente + "")) {

            } else {
                listaClientes.remove(0);
            }
        } catch (NumberFormatException ex) {
            for (int i = listaClientes.size() - 1; i > 0; i--) {
                if (listaClientes.get(i).getNombre().toLowerCase().startsWith(busquedaClientes.toLowerCase())) {

                } else {
                    System.out.println("eliminado..." + listaClientes.get(i).getNombre() + "--" + listaClientes.size());
                    listaProductos.remove(i);
                }
            }
            if (listaClientes.get(0).getNombre().toLowerCase().startsWith(busquedaClientes.toLowerCase())) {

            } else {
                listaClientes.remove(0);
            }
        }
    }

    /**
     * Set the value of filteredProducts
     *
     * @param filteredProducts new value of filteredProducts
     */
    public void setFilteredProducts(List<ProductoDTO> filteredProducts) {
        this.filteredProducts = filteredProducts;
    }

    /**
     * Get the value of precios
     *
     * @return the value of precios
     */
    public List<String> getPrecios() {
        return precios;
    }

    /**
     * Set the value of precios
     *
     * @param precios new value of precios
     */
    public void setPrecios(List<String> precios) {
        this.precios = precios;
    }

    public void agregar() {
        if (!verificaLista(selectedProduct)) {
            listaVenta.add(selectedProduct);
        }
        //this.totalPagar();
    }

    public void crearTerminales(Connection con) {
        new PosDAO().obtenerTerminales(this, con);
    }

    public void cambiarVendedor() {
        Connection con = Factory.getConnection(ConstanteDTO.MAIN_CONTEXT_NAME_DB);
        errorUsuario = new PosDAO().validaVendedor(this, con);

    }

    public void esperar(ActionEvent evt) {
        System.out.println("Tecla presionada.... esperar");
        Connection con = Factory.getConnection(ConstanteDTO.MAIN_CONTEXT_NAME_DB);
        v_en_espera = false;
        new RegistrosPos().regisVentaEnEspera(this, con);
        new PosDAO().ventasSinCerrar(this, con);
        //v_en_espera = true;
        this.descuento = 0;
        this.impuestos = 0;
        this.totalVenta = 0;
    }

    public void continuar(VentaDTO v, boolean en_espera) {
        v_en_espera = true;
        listaVenta.clear();
        venta = v;
        Connection con = Factory.getConnection(ConstanteDTO.MAIN_CONTEXT_NAME_DB);
        new PosDAO().prodEnEspera(this, con, v);
        System.out.println("continuar-...");
        for (int i = 0; i < listaVenta.size(); i++) {
            listaVenta.get(i).setEstatus(0);
        }
        //v_en_espera = true;
    }

    public void cambiarCliente(String cliente) {
        clientes = new ArrayList<SelectItem>();
        for (int i = 0; i < listCliDefault.size(); i++) {
            if (listCliDefault.get(i).getNombre().equals(cliente)) {
                venta.setIdCliente(listCliDefault.get(i).getIdCliente());
                System.out.println("cambiado --" + cliente);
            }
        }
    }

    public void cambiarTerminal(String terminal) {
        terminales = new ArrayList<SelectItem>();
        for (int i = 0; i < listaTerminales.size(); i++) {
            if (listaTerminales.get(i).getNombre().equals(terminal)) {
                venta.setIdTermninal(listaTerminales.get(i).getId_terminal());
                venta.setIdAlmacen(listaTerminales.get(i).getId_almacen());
                venta.setIdTienda(listaTerminales.get(i).getId_tienda());
                System.out.println("cambiado --" + terminal);
            }
        }
    }

    public String getCliente() {
        System.out.println("get cliente");
        return cliente;
    }

    public void setCliente(String cliente) {
        //cambiarCliente(cliente);
        System.out.println("set cliente --" + cliente);
        this.cliente = cliente;
    }

    public String getTerminal() {
        System.out.println("get terminal");
        return terminal;
    }

    public void setTerminal(String terminal) {
        //cambiarCliente(cliente);
        //cambiarTerminal(terminal);
        System.out.println("set terminal --" + terminal);
        this.terminal = terminal;
    }

    public List<ClienteDTO> getListCliDefault() {
        return listCliDefault;
    }

    public void setListCliDefault(List<ClienteDTO> listCliDefault) {
        this.listCliDefault = listCliDefault;
    }

    public static List<ProductoDTO> getListaDefault() {
        return listaDefault;
    }

    public static void setListaDefault(List<ProductoDTO> listaDefault) {
        PosVO.listaDefault = listaDefault;
    }

    public List<VentaDTO> getProductosPorVender() {
        return productosPorVender;
    }

    public void setProductosPorVender(List<VentaDTO> productosPorVender) {
        this.productosPorVender = productosPorVender;
    }

    public String getCampoBusqueda() {
        return campoBusqueda;
    }

    public void setCampoBusqueda(String campoBusqueda) {
        this.campoBusqueda = campoBusqueda;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getImpuestos() {
        return impuestos;
    }

    public void setImpuetos(double impuestos) {
        this.impuestos = impuestos;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public ProductoDTO getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(ProductoDTO selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public String getPlaceHolder() {
        return placeHolder;
    }

    public void setPlaceHolder(String placeHolder) {
        this.placeHolder = placeHolder;
    }

    public List<SelectItem> getDocumentos() {
        return documentos;
    }

    public void setDocumentos(List<SelectItem> documentos) {
        this.documentos = documentos;
    }

    public List<SelectItem> getClientes() {
        return clientes;
    }

    public void setClientes(List<SelectItem> clientes) {
        this.clientes = clientes;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public VentaDTO getSelectedVenta() {
        return selectedVenta;
    }

    public void setSelectedVenta(VentaDTO selectedVenta) {
        this.selectedVenta = selectedVenta;
    }

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import logica.Books;

/**
 *
 * @author iamramiroo
 */
@Named(value = "controladorBooks")
@SessionScoped
public class ControladorBooks implements Serializable {

    /*private List<Productos> listaProductos;
    Productos product = new Productos();*/
    private List<Books> listaLibros;
    Books selectedBook = new Books();
    int filterFrom;
    int filterTo;
    
    /**
     * Creates a new instance of ControladorBooks
     */
    public ControladorBooks() {
        loadBooks();
    }

    public void loadBooks() {

        listaLibros = null;

        EntityManager em = selectedBook.getEntityManager();
        TypedQuery<Books> consultaLibros = em.createNamedQuery("Books.findAll", Books.class);
        listaLibros = consultaLibros.getResultList();
    }
    
    public void filterByYears() {

        listaLibros = null;

        EntityManager em = selectedBook.getEntityManager();
        TypedQuery<Books> consultaLibros = em.createNamedQuery("Books.findByYears", Books.class);
        consultaLibros.setParameter("min", filterFrom);
        consultaLibros.setParameter("max", filterTo);
        listaLibros = consultaLibros.getResultList();
        
        setFilterFrom(0);
        setFilterTo(0);
    }
    
    public void filterByPrice() {

        listaLibros = null;

        EntityManager em = selectedBook.getEntityManager();
        TypedQuery<Books> consultaLibros = em.createNamedQuery("Books.findByPrices", Books.class);
        consultaLibros.setParameter("min", filterFrom);
        consultaLibros.setParameter("max", filterTo);
        listaLibros = consultaLibros.getResultList();
        
        setFilterFrom(0);
        setFilterTo(0);
    }

    public void createBook() {

        try {

            if (selectedBook.getId() == null) {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookstorePU");
                EntityManager em = emf.createEntityManager();
                //EntityManager em = selectedBook.getEntityManager();
                em.getTransaction().begin();
                selectedBook.setState(true);
                em.persist(selectedBook);
                em.getTransaction().commit();
            } else {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookstorePU");
                EntityManager em = emf.createEntityManager();
                //EntityManager em = selectedBook.getEntityManager();
                em.getTransaction().begin();

                em.merge(selectedBook);
                em.getTransaction().commit();
            }

            selectedBook = new Books();
            loadBooks();

        } catch (Exception e) {
            System.out.println("Error:> " + e.getMessage());
        }
    }

    public void disableBook() {

        try {

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookstorePU");
            EntityManager em = emf.createEntityManager();
            //EntityManager em = selectedBook.getEntityManager();
            em.getTransaction().begin();
            selectedBook.setState(false);
            em.merge(selectedBook);
            em.getTransaction().commit();

            selectedBook = new Books();
            loadBooks();

        } catch (Exception e) {
            System.out.println("Error:> " + e.getMessage());
        }
    }

    public String obtenerNombre() {
        /*if(selectedBook == null){
            return "Crear";
        }
        return selectedBook.getId() == null ? "Editar" : "Crear";*/

        return selectedBook.getId() == null ? "Crear" : "Editar";
    }

    public void editBook(Books book) {
        this.selectedBook = book;
    }

    public List<Books> getListaLibros() {
        return listaLibros;
    }

    public void setListaLibros(List<Books> listaLibros) {
        this.listaLibros = listaLibros;
    }

    public Books getSelectedBook() {
        return selectedBook;
    }

    public void setSelectedBook(Books selectedBook) {
        this.selectedBook = selectedBook;
    }

    public int getFilterFrom() {
        return filterFrom;
    }

    public void setFilterFrom(int filterFrom) {
        this.filterFrom = filterFrom;
    }

    public int getFilterTo() {
        return filterTo;
    }

    public void setFilterTo(int filterTo) {
        this.filterTo = filterTo;
    }

}

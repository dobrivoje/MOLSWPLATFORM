package db.retail.interfaces;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.List;

/**
 *
 * @author Dobri
 * @param <P> Custom class
 * @param <T> POJO class representing search criteria
 */
public interface IAdvancedSearchController<P, T> {

    List<P> getAll();

    List<P> get(T criteria);

    P getByID(T criteria);

}

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
 * @param <Criteria> POJO class representing search criteria
 */
public interface IAdvancedSearchController<P, Criteria> {

    /**
     * For all P, generate all P's lists of details
     *
     * @param initSubList
     * @return
     */
    List<P> getAll(boolean initSubList);

    List<P> get(Criteria criteria);

    P getByID(Criteria criteria);

}

package db.retail.interfaces;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dobri
 * @param <Type> POJO class
 */
public interface ICRUDController<Type> {

    void add(Type newItem) throws Exception;

    void update(Type item) throws Exception;

}

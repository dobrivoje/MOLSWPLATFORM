/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.HSE.interfaces;

import db.HSE.DBHandler_HSE;
import java.util.List;

/**
 *
 * @author Dobri
 * @param <T>
 */
public abstract class Controller<T> {

    protected static DBHandler_HSE DBH;

    public abstract List<T> getAll();

    public abstract T getByID(Long ID);

    public abstract void addNew(T t) throws Exception;

    public abstract void updateExisting(T t) throws Exception;

    public abstract void delete(T t);

    public Controller(DBHandler_HSE DBH) {
        Controller.DBH = DBH;
    }

}

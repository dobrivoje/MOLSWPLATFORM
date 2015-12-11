/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.HSE.controllers;

import db.HSE.DBHandler;
import java.util.List;

/**
 *
 * @author dprtenjak
 * @param <T>
 */
public abstract class Controller<T> {

    protected static DBHandler DBH;

    public abstract List<T> getAll();

    public abstract T getByID(Long ID);

    public abstract void addNew(T t) throws Exception;

    public abstract void updateExisting(T t) throws Exception;

    public abstract void delete(T t);

    public Controller(DBHandler DBH) {
        Controller.DBH = DBH;
    }

}

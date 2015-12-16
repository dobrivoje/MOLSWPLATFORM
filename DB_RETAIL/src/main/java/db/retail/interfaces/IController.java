package db.retail.interfaces;

import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Dobri
 * @param <Criteria> Criteria class
 * @param <Type> POJO class we work with
 */
public interface IController<Type, Criteria> extends IAdvancedSearchController<Type, Criteria>, ICRUDController<Type> {

    @Override
    public Type getByID(Criteria criteria);

    @Override
    public List<Type> get(Criteria criteria);

    @Override
    public List<Type> getAll();

    @Override
    public void update(Type item) throws Exception;

    @Override
    public void add(Type newItem) throws Exception;

}

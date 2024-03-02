/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;

/**
 *
 * @author THevY
 */
public interface DAOInterface<T> {
    public ArrayList<T> selectAll();
    
    public T selectById(T t);
    
    public int insert(T t);
    
    public int insertAll(ArrayList<T> arr);
    
    public int delete (T t);
    
    public int deleteAll (ArrayList<T> arr);
    
    public int update (T t);
}

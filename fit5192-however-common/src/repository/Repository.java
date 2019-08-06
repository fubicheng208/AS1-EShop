/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.List;
import repository.entities.Product;
import repository.entities.User;

/**
 * 在这里添加数据持久化的各种方法
 * @author fubic
 */
public interface Repository {
    public boolean login(String username, String pw) throws Exception;
    public boolean register(User user) throws Exception;
    public boolean editInfo(User user) throws Exception;
    public List<Product> search() throws Exception;
    public List<Product> searchByCategory(String category) throws Exception;
}

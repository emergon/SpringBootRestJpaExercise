/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emergon.dao;

import emergon.entity.Author;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthorRepo extends JpaRepository<Author, Integer> {
    @Query("Select a FROM Author a WHERE a.name LIKE CONCAT('%',:name,'%')")
    public List<Author> findByName(@Param("name") String name);
    
}

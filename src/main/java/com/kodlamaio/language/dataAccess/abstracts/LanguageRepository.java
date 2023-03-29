package com.kodlamaio.language.dataAccess.abstracts;


import com.kodlamaio.language.entity.concretes.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
@Repository
public interface LanguageRepository extends JpaRepository<Language,Integer>
{
    boolean existsBySlug(String slug);
    Language findById(int id);
    @Modifying
    @Query("update Language l set l.name = :name,l.slug = :slug  where l.id = :id")
    void updateName(@Param(value = "id") int id, @Param(value = "name") String name,@Param(value = "slug") String slug);
}

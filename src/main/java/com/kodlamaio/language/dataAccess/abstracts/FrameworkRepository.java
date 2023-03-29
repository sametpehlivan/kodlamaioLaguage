package com.kodlamaio.language.dataAccess.abstracts;

import com.kodlamaio.language.entity.concretes.Framework;
import com.kodlamaio.language.entity.concretes.Language;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FrameworkRepository extends JpaRepository<Framework,Integer>
{
    boolean existsBySlug(String slug);
    Framework findById(int id);
    @Modifying
    @Query("update Framework f set f.name = :name,f.slug = :slug  where f.id = :id")
    void updateName(@Param(value = "id") int id, @Param(value = "name") String name, @Param(value = "slug") String slug);
    @Query("select case when count(f) > 0 then true else false end from Framework f where f.language.id = :language_id and f.slug = :slug")
    boolean isFrameworkExist(@Param("language_id") int language_id,@Param("slug") String slug);
    @Modifying
    @Query("update Framework f set f.language = :language where f.id = :id ")
    void changeLanguage(@Param("language")Language language);
}

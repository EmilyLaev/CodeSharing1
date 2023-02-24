package platform.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import platform.entries.CodeSnippet;

//interface extends 'CrudRepository'
// provides CRUD operations for codesnippet entities
@Repository
public interface SnippetRepository extends CrudRepository<CodeSnippet, String> {
}
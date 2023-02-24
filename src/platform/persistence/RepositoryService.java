package platform.persistence;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import platform.entries.CodeSnippet;

import java.util.Optional;

@org.springframework.stereotype.Service
//This class defines a service for interacting with a SnippetRepository object.
public class RepositoryService {
    private final SnippetRepository snippetRepository;

    //Constructs a new RepositoryService with the given SnippetRepository
    @Autowired
    public RepositoryService(SnippetRepository snippetRepository) {
        this.snippetRepository = snippetRepository;
    }

    // Finds a CodeSnippet with the given ID.
    //@param id the ID of the CodeSnippet to find
    //@return an Optional containing the CodeSnippet, or empty if not found
    public Optional<CodeSnippet> findByID(@NotNull String id) {
        return snippetRepository.findById(id);
    }

    //Deletes the given CodeSnippet.
    //@param snippet the CodeSnippet to delete
    public void delete(@NotNull CodeSnippet snippet) {
        snippetRepository.delete(snippet);
    }

    //Saves the given CodeSnippet.
    public void save(@NotNull CodeSnippet snippet) {
        snippetRepository.save(snippet);
    }

    //Returns all CodeSnippets in the repository
    public Iterable<CodeSnippet> findAll() {
        return snippetRepository.findAll();
    }
}
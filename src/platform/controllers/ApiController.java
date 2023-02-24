package platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import platform.entries.CodeSnippet;
import platform.persistence.RepositoryService;

import java.util.Map;
import java.util.Optional;

@Controller
public class ApiController {
    private final RepositoryService service;


    //Handles GET requests to retrieve a code snippet by UUID
    @Autowired
    public ApiController(RepositoryService service) {
        this.service = service;
    }

    @GetMapping(value = "/api/code/{uuid}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public CodeSnippet snippetByUUID(@PathVariable String uuid) {
        Optional<CodeSnippet> snippetOptional = service.findByID(uuid);
        if (snippetOptional.isPresent()) {
            CodeSnippet snippet = snippetOptional.get();
            if (snippet.checkIsAvailable()) {
                snippet.view();
                service.save(snippet);
                return snippet;
            } else {
                //If the snippet is not available, delete it from the repository
                service.delete(snippet);
            }
        }
        //If the snippet is not found, return a 404 error
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    //Handles GET requests to retrieve all code snippets in the repository
    @GetMapping(value = "/api/code/all")
    @ResponseBody
    public Iterable<CodeSnippet> allSnippets() {
        return service.findAll();
    }

    // Handles POST requests to add a new code snippet to the repository
    @PostMapping(value = "/api/code/new", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addSnippet(@RequestBody Map<String, Object> map) {
        CodeSnippet snippet = ApiUtils.parseSnippetFromJSON(map);
        service.save(snippet);
        // Returns the UUID of the new code snippet
        return snippet.getSnippetUUID();
    }
}

package platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import platform.entries.CodeSnippet;

@Controller
public class HTMLController {

    private final ApiController apiController;

    @Autowired
    public HTMLController(ApiController apiController) {
        this.apiController = apiController;
    }

    // Generates a view to display a single code snippet by UUID
    @GetMapping(value = "/code/{uuid}")
    private String pageWithSnippetByUUID(@PathVariable String uuid, Model model) {
        CodeSnippet snippet = apiController.snippetByUUID(uuid);
        model.addAttribute("snippet", snippet);
        // Returns the name of the view to render, which displays the code snippet
        return "viewCodeSnippet";
    }

    // Generates a view to submit a new code snippet
    @GetMapping(value = "/code/new")
    private String submitCodeSnippet() {
        // Returns the name of the view to render, which displays the form for submitting a new code snippet
        return "submitCodeSnippet";
    }

    // Generates a view to display all code snippets in the repository
    @GetMapping(value = "/code/all")
    private String viewLatestCodeSnippets(Model model) {
        // Returns the name of the view to render, which displays all code snippets
        model.addAttribute("snippets", apiController.allSnippets());
        // Returns the name of the view to render, which displays all code snippets
        return "viewCodeSnippets";
    }
}

package tn.esprit.tpprojet.Controllers;

import lombok.AllArgsConstructor;
import lombok.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.tpprojet.Entities.Projet;
import tn.esprit.tpprojet.Services.IProjetServices;
import tn.esprit.tpprojet.Services.ProjetServiceImpl;

import java.util.List;

/**
 * CONTROLLER EXPLANATION:
 * =====================================================================

 * WHAT IS A CONTROLLER?
 * ---------------------
 * A controller is the presentation layer component that handles HTTP requests and responses.
 * It acts as the entry point for client applications (web browsers, mobile apps, Postman, etc.)

 * ROLE IN MVC ARCHITECTURE:
 * -------------------------
 * Client (Browser/App) → Controller → Service → Repository → Database
 *        ↑                                                |
 *        └──────────────── Response ─────────────────────┘

 * WHAT IS A BEAN IN THIS CONTEXT?
 * --------------------------------
 * - @RestController itself is a bean (stereotype annotation)
 * - Spring creates a singleton instance of this controller at startup
 * - All dependencies (@Autowired) are injected as beans
 * - The controller bean lives in Spring IoC container and handles all HTTP requests

 * OBJECTIVES OF A CONTROLLER:
 * ---------------------------
 * 1. Receive HTTP requests (GET, POST, PUT, DELETE)
 * 2. Extract data from requests (@RequestBody, @PathVariable, @RequestParam)
 * 3. Delegate business logic to Service layer
 * 4. Return HTTP responses (JSON/XML data or status codes)
 * 5. Handle HTTP-specific concerns (status codes, headers, content negotiation)

 * REST ARCHITECTURE:
 * -----------------
 * REST = Representational State Transfer
 * - Uses HTTP methods as operations (GET, POST, PUT, DELETE)
 * - Stateless communication
 * - Returns data in JSON/XML format
 * - Uses URLs to identify resources
 */

@RestController  // Combines @Controller + @ResponseBody
// @Controller: marks this as a Spring MVC controller
// @ResponseBody: automatically converts return objects to JSON/XML
// All methods return data directly (not view names)
// This class becomes a Spring Bean automatically

@AllArgsConstructor  // Lombok: generates constructor with all arguments (for dependency injection)
@RequestMapping ("/Projet")  // Base URL for all endpoints in this controller
// All URLs will start with: http://localhost:8089/tpProjet/Projet/...

public class ProjetRestController {

    /**
     * DEPENDENCY INJECTION IN CONTROLLER:
     * We inject the Service interface (not implementation)
     * This follows Dependency Inversion Principle (DIP)
     * The controller depends on abstraction (interface), not concrete implementation
     */
    @Autowired  // Injects the Service bean (ProjetServiceImpl)
    private IProjetServices projetServices;

    // Note: @AllArgsConstructor would generate a constructor that Spring could use
    // for constructor injection (better practice than @Autowired on field)

    /**
     * ENDPOINT: POST /Projet/addProjet
     * ---------------------------------
     * HTTP Method: POST
     * URL: http://localhost:8089/tpProjet/Projet/addProjet

     // @PostMapping: Maps HTTP POST requests to this method
     * Used for creating new resources (C in CRUD - Create)

     // @RequestBody: Extracts the JSON from HTTP request body and converts it to Projet object
     * Example JSON in request body:
     * {
     *     "sujet": "New Project",
     *     "projetDetail": { ... }
     * }

     Response: Returns the created Projet (with generated ID) as JSON
     * HTTP Status: 200 OK (default) - should be 201 Created ideally
     */
    @PostMapping("/addProjet")  // Specific endpoint for POST requests
    Projet addProjet(@RequestBody Projet projet){  // @RequestBody binds JSON body to Java object
        return projetServices.ajouterProjet(projet);  // Delegate to service layer
    }

    /**
     * ENDPOINT: GET /Projet/getProjet
     * --------------------------------
     * HTTP Method: GET
     * URL: http://localhost:8089/tpProjet/Projet/getProjet

     // @GetMapping: Maps HTTP GET requests to this method
     * Used for retrieving all resources (R in CRUD - Read All)

     // No @RequestBody needed for GET requests

     * Response: Returns List of all Projets as JSON array
     * Example: [{ "id": 1, "sujet": "Project A" }, { "id": 2, "sujet": "Project B" }]
     */
    @GetMapping("/getProjet")
    List<Projet> getProjet(){
        return projetServices.afficherProjet();
    }

    /**
     * ENDPOINT: GET /Projet/getProjetById/{idP}
     * -----------------------------------------
     * HTTP Method: GET
     URL: http://localhost:8089/tpProjet/Projet/getProjetById/5

     // @GetMapping: Maps HTTP GET requests with path variable
     * Used for retrieving a specific resource by ID (R in CRUD - Read One)

     // @PathVariable: Extracts value from URL path
     * {idP} in URL matches @PathVariable("idP")

     * Path Variable vs Request Parameter:
     * - Path Variable: /getProjetById/5  (part of URL path)
     * - Request Param: /getProjet?id=5   (query parameter)

     * Response: Returns single Projet object as JSON
     */
    @GetMapping("/getProjetById/{idP}")
    Projet getProjetById(@PathVariable("idP") long idProjet){  // @PathVariable extracts from URL
        return projetServices.afficherProjetSelonID(idProjet);
    }

    /**
     * ENDPOINT: PUT /Projet/updateProjet
     * ----------------------------------
     * HTTP Method: PUT
     * URL: http://localhost:8089/tpProjet/Projet/updateProjet

     // @PutMapping: Maps HTTP PUT requests to this method
     * Used for updating existing resources (U in CRUD - Update)

     // @RequestBody: Contains the full updated object (must include ID)

     * PUT vs POST:
     * - PUT: Full update (replace entire resource)
     * - POST: Create new resource
     * - PATCH: Partial update (not shown here)

     * Response: Returns updated Projet as JSON
     */
    @PutMapping("/updateProjet")
    Projet updateProjet(@RequestBody Projet projet){
        return projetServices.modifierProjet(projet);
    }

    /**
     * ENDPOINT: DELETE /Projet/deleteProjet/{idP}
     * -------------------------------------------
     * HTTP Method: DELETE
     * URL: http://localhost:8089/tpProjet/Projet/deleteProjet/5

     // @DeleteMapping: Maps HTTP DELETE requests to this method
     * Used for deleting resources (D in CRUD - Delete)

     // @PathVariable: Extracts ID from URL to know which resource to delete

     * Response: void (no content) - HTTP 200 OK or 204 No Content
     */
    @DeleteMapping("/deleteProjet/{idP}")
    void deleteProjet(@PathVariable("idP") long idProjet)
    {
        projetServices.supprimerProjet(idProjet);
    }
}

// ======================================================================
// HTTP METHODS & ANNOTATIONS SUMMARY:
// ======================================================================
/*
 *
 * | HTTP Method | Annotation     | CRUD Operation | Id in URL | Request Body |
 * |-------------|----------------|----------------|-----------|--------------|
 * | POST        | @PostMapping   | Create         | No        | Yes (new)    |
 * | GET (all)   | @GetMapping    | Read All       | No        | No           |
 * | GET (one)   | @GetMapping    | Read One       | Yes       | No           |
 * | PUT         | @PutMapping    | Update         | Sometimes | Yes (full)   |
 * | DELETE      | @DeleteMapping | Delete         | Yes       | No           |
 *
 * ======================================================================
 * SPRING BEANS IN THIS CONTROLLER:
 * ======================================================================
 *
 * 1. @RestController → ProjetRestController bean
 * 2. @Autowired IProjetServices → ProjetServiceImpl bean (injected)
 * 3. ProjetRepository bean (used by service, injected there)
 *
 * All are managed by Spring IoC container - single instances (singleton scope)
 *
 * ======================================================================
 * COMPLETE REQUEST FLOW:
 * ======================================================================
 *
 * 1. Client sends HTTP request: GET http://localhost:8089/tpProjet/Projet/getProjetById/5
 *
 * 2. DispatcherServlet (Front Controller) intercepts request
 *
 * 3. Handler Mapping finds matching controller method:
 *    - Base path: /Projet
 *    - Sub path: /getProjetById/{idP}
 *    - Method: GET
 *    - Matches: getProjetById()
 *
 * 4. Spring extracts {idP} = 5 and converts to long idProjet
 *
 * 5. Controller method is called with idProjet = 5
 *
 * 6. Controller calls Service → Service calls Repository → Repository queries DB
 *
 * 7. Result returns through layers back to controller
 *
 * 8. @ResponseBody (from @RestController) converts Projet object to JSON
 *
 * 9. HTTP response sent back to client with JSON data
 *
 * ======================================================================
 * BEST PRACTICES & IMPROVEMENTS:
 * ======================================================================
 *
 * 1. HTTP Status Codes:
 *    - Use @ResponseStatus(HttpStatus.CREATED) for POST
 *    - Use @ResponseStatus(HttpStatus.NO_CONTENT) for DELETE
 *
 * 2. Error Handling:
 *    - Add @ExceptionHandler methods
 *    - Return proper error responses with appropriate status codes
 *
 * 3. Validation:
 *    - Add @Valid annotation with validation constraints
 *    - Example: @PostMapping("/addProjet") Projet addProjet(@Valid @RequestBody Projet projet)
 *
 * 4. Logging:
 *    - Add logging to track requests
 *
 * 5. API Documentation:
 *    - Consider Swagger/OpenAPI for documentation
 */